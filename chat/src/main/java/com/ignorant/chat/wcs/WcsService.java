package com.ignorant.chat.wcs;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.ignorant.chat.entity.Msg;
import com.ignorant.chat.entity.SocketData;
import com.ignorant.chat.enums.ContentType;
import com.ignorant.chat.utils.JsonUtils;
import com.ignorant.chat.wcs.entity.Contact;
import com.ignorant.chat.wcs.entity.WcsSocketContent;
import com.ignorant.chat.websocket.WebSocketService;
import com.ignorant.pojo.User;

@Component
public class WcsService {

	@PostConstruct
	public void init() {
		wsClient.init();
	}

	@Autowired
	private WcsClient wsClient;

	@Autowired
	private WebSocketService webSocketService;

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ApplicationContext context;

	private static ConcurrentMap<String, List<User>> user2Contact = new ConcurrentHashMap<String, List<User>>();

	public void loginWcs(String userId) {
		wsClient.loginWc(userId);
	}

	public void handleWcsData(Object data) {
		try {
			JSONObject jsonObject = JSONObject.parseObject((String) data);
			JSONObject jsonObejctContent = jsonObject.getJSONObject("data");
			String type = jsonObject.getString("type");
			if (StringUtils.equals(type, "msg"))
				type = "wcsMsg";
			System.out.println("com.ignorant.chat.wcs.entity." + type.substring(0, 1).toUpperCase()
					+ type.substring(1, type.length()));
			System.out.println(jsonObejctContent);
			WcsSocketContent content = null;
			if (jsonObejctContent != null)
				content = (WcsSocketContent) jsonObejctContent
						.toJavaObject(Class.forName("com.ignorant.chat.wcs.entity." + type.substring(0, 1).toUpperCase()
								+ type.substring(1, type.length())));
			WcsSocketContent abstracSocketData = (WcsSocketContent) context.getBean(type);
			abstracSocketData.start(jsonObject.getString("userId"), content);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("格式化文件出错" + JsonUtils.objectToJson(data));
			e.printStackTrace();
		}

//		WebSocketManager.send(wcsData.getUserId(), new SocketData(ContentType.wcsMsg, JsonUtils.objectToJson(wcsData)));
	}

	public boolean sendMsg(Msg msg) {
		List<User> userList = WcsService.user2Contact.get(msg.getUserId());
		if (userList == null || userList.stream().noneMatch(c -> c.getUserId().equals(msg.getTo())))
			return false;
		return wsClient.sendMsg(msg.getUserId(), msg.getTo(), msg.getContent(),
				msg.getSyncIdList().get(msg.getSyncIdList().size() - 1));
	}

	public List<User> queryContact(String userId, String q) {
		RestTemplate restTemplate = new RestTemplate();
		@SuppressWarnings("unchecked")
		List<Contact> contactList = restTemplate
				.getForObject(String.format("http://localhost:8082/contact?userId=%s?q=%s", userId, q), List.class);
		contactList = contactList.subList(0, 3000);
		return contactList.stream().map(c -> {
			User user = new User();
			user.setUserId(c.getUserName());
			user.setNickName(c.getNickName());
			user.setAvatar(c.getHeadImgUrl());
			user.setAvatar_small(c.getHeadImgUrl());
			user.setGender(c.getSex());
			user.setSignature(c.getSignature());
//			user.setStatus(c.getStatus());
			user.setType("wc");
			return user;
		}).collect(Collectors.toList());
	}

	public List<User> getInitContact(String userId) {
		RestTemplate restTemplate = new RestTemplate();
		@SuppressWarnings("unchecked")
		List<Contact> contactList = restTemplate.getForObject("http://localhost:8082/contact/init?userId=" + userId,
				List.class);
		return contactList.stream().map(c -> {
			User user = new User();
			user.setUserId(c.getUserName());
			user.setNickName(c.getNickName());
			user.setAvatar(c.getHeadImgUrl());
			user.setAvatar_small(c.getHeadImgUrl());
			user.setGender(c.getSex());
			user.setSignature(c.getSignature());
//			user.setStatus(c.getStatus());
			user.setType("wc");
			return user;
		}).collect(Collectors.toList());
	}
}
