package com.ignorant.chat.wcs;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.ignorant.chat.entity.Msg;
import com.ignorant.chat.entity.SocketData;
import com.ignorant.chat.entity.WcsData;
import com.ignorant.chat.enums.ContentType;
import com.ignorant.chat.utils.JsonUtils;
import com.ignorant.chat.websocket.WebSocketManager;
import com.ignorant.pojo.User;

@Component
public class WcsService {

	@Autowired
	private WcsClient wsClient;
	
	private static ConcurrentMap<String, List<User>> user2Contact = new ConcurrentHashMap<String,  List<User>>();

	public void loginWcs(String userId) {
		wsClient.loginWc(userId);
	}

	public static void handleWcsData(Object data) {
		WcsData wcsData = JsonUtils.jsonToPojo((String) data, WcsData.class);
		WebSocketManager.send(wcsData.getUserId(), new SocketData(ContentType.wcsMsg, JsonUtils.objectToJson(wcsData)));
	}

	public void sendMsg(Msg msg) {
		wsClient.sendMsg(msg.getUserId(), msg.getTo(), msg.getContent());
	}
	
	public void getContactList(String userId) {
		RestTemplate restTemplate = new RestTemplate();
		@SuppressWarnings("unchecked")
		List<User> contactList = restTemplate.getForObject("http://localhost:8082/contact?userId=" + userId, List.class);
		user2Contact.put(userId, contactList);
	}
}
