package com.ignorant.chat.service;

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
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ignorant.chat.pojo.User;
import com.ignorant.chat.wcs.WcsClient;
import com.ignorant.chat.wcs.entity.WcsSocketContent;

@Component
public class WcsService {

	@PostConstruct
	public void init() {
		wsClient.init();
	}

	@Autowired
	private WcsClient wsClient;

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
			if (!(data instanceof String)) {
				logger.debug("ignore data from wcs { data: {}}", data);
				return;
			}
			logger.debug("receive data from wcs {data: {}}", JSONObject.toJSONString(data));
			JSONObject jsonObject = JSONObject.parseObject((String) data);
			JSONObject jsonObejctContent = jsonObject.getJSONObject("data");
			String type = jsonObject.getString("type");
			if (StringUtils.equals(type, "msg"))
				type = "wcsMsg";
			WcsSocketContent content = null;
			if (jsonObejctContent != null)
				content = (WcsSocketContent) jsonObejctContent
						.toJavaObject(Class.forName("com.ignorant.chat.wcs.entity." + type.substring(0, 1).toUpperCase()
								+ type.substring(1, type.length())));
			WcsSocketContent abstracSocketData = (WcsSocketContent) context.getBean(type);
			abstracSocketData.start(jsonObject.getString("userId"), content);
		} catch (Exception e) {
			// TODO: handle exception
			logger.debug("format failed when receive data from wcs {data: {}, error: {}}",
					JSONObject.toJSONString(data), e.getMessage());
		}
	}

	public boolean sendMsg(String userId, String to, String content, List<String> syncList) {
		logger.debug("prepare to send msg to wcs {userId: {}, to: {}, content: {}, syncList: {}}", userId, to, content,
				JSONArray.toJSONString(syncList));
		return wsClient.sendMsg(userId, to, content, syncList.get(syncList.size() - 1));
	}

	@SuppressWarnings("unchecked")
	public List<User> queryContact(String userId, String q) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			List<Map<String, Object>> response = restTemplate.getForObject(
					String.format("http://120.78.93.110:8082/contact?userId=%s&q=%s", userId, q), List.class);
			List<User> result = new ArrayList<>();
			response = response.subList(0, Math.min(3000, response.size()));
			for (Map<String, Object> m : response) {
				User user = new User();
				user.setUserId(m.get("UserName").toString());
				user.setNickName(m.get("NickName").toString());
				user.setAvatar(m.get("HeadImgUrl").toString());
				user.setAvatar_small(m.get("HeadImgUrl").toString());
				user.setGender(m.get("Sex").toString());
				user.setSignature(m.get("Signature").toString());
				user.setType("wc");
				result.add(user);
			}
			return result;
		} catch (Exception e) {
			logger.error("query contact failed {error: {}}", e.getMessage());
			return new ArrayList<>();
		}
	}

	@SuppressWarnings("unchecked")
	public List<User> getInitContact(String userId) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			List<Map<String, Object>> response = restTemplate
					.getForObject("http://120.78.93.110:8082/contact/init?userId=" + userId, List.class);
			List<User> result = new ArrayList<>();
			response = response.subList(0, Math.min(30, response.size()));
			for (Map<String, Object> m : response) {
				User user = new User();
				user.setUserId(m.get("UserName").toString());
				user.setNickName(m.get("NickName").toString());
				user.setAvatar(m.get("HeadImgUrl").toString());
				user.setAvatar_small(m.get("HeadImgUrl").toString());
				user.setGender(m.get("Sex").toString());
				user.setSignature(m.get("Signature").toString());
				user.setType("wc");
				result.add(user);
			}
			return result;
		} catch (Exception e) {
			logger.error("get init contact failed {error: {}}", e.getMessage());
			return new ArrayList<>();
		}
	}
}
