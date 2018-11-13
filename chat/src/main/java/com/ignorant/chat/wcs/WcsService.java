package com.ignorant.chat.wcs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.ignorant.chat.entity.Msg;
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

//	@Autowired
//	private RestTemplate restTemplate;

	public static ConcurrentMap<String, User> user2WcInfo = new ConcurrentHashMap<String, User>();

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
		return wsClient.sendMsg(msg.getUserId(), msg.getTo(), msg.getContent(),
				msg.getSyncIdList().get(msg.getSyncIdList().size() - 1));
	}

	public static void main(String[] args) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Contact[]> responseEntity = restTemplate
				.getForEntity("http://localhost:8082/contact/init?userId=Lory.Y.Jiang", Contact[].class);
		System.out.println(responseEntity.getBody()[0].getHeadImgUrl());
		int i = 0;
		System.out.println(new Integer(i).toString());
	}

	@SuppressWarnings("unchecked")
	public List<User> queryContact(String userId, String q) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			List<Map<String, Object>> response = restTemplate
					.getForObject(String.format("http://localhost:8082/contact?userId=%s?q=%s", userId, q), List.class);
			List<User> result = new ArrayList<>();
			response = response.subList(0, Math.min(3000, response.size()));
			for (Map<String, Object> m : response) {
				User user = new User();
				user.setUserId(m.get("UserName").toString());
				user.setNickName(m.get("NickName").toString());
				user.setAvatar("https://wx2.qq.com" + m.get("HeadImgUrl").toString());
				user.setAvatar_small("https://wx2.qq.com" + m.get("HeadImgUrl").toString());
				user.setGender(m.get("Sex").toString());
				user.setSignature(m.get("Signature").toString());
				user.setType("wc");
				result.add(user);
			}
			return result;
		} catch (Exception e) {
			logger.error(String.format("query contact failed: %s", e.getMessage()));
			return new ArrayList<>();
		}
	}

	@SuppressWarnings("unchecked")
	public List<User> getInitContact(String userId) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			List<Map<String, Object>> response = restTemplate
					.getForObject("http://localhost:8082/contact/init?userId=" + userId, List.class);
			List<User> result = new ArrayList<>();
			System.out.println(response.size());
			response = response.subList(0, Math.min(30, response.size()));
			for (Map<String, Object> m : response) {
				User user = new User();
				user.setUserId(m.get("UserName").toString());
				user.setNickName(m.get("NickName").toString());
				user.setAvatar("https://wx2.qq.com" + m.get("HeadImgUrl").toString());
				user.setAvatar_small("https://wx2.qq.com" + m.get("HeadImgUrl").toString());
				user.setGender(m.get("Sex").toString());
				user.setSignature(m.get("Signature").toString());
				user.setType("wc");
				result.add(user);
			}
			System.out.println(result.size());
			return result;
		} catch (Exception e) {
			logger.error(String.format("get init contact failed: %s", e.getMessage()));
			return new ArrayList<>();
		}

	}
}
