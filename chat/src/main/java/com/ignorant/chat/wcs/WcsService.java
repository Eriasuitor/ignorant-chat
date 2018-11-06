package com.ignorant.chat.wcs;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ignorant.chat.entity.Msg;
import com.ignorant.chat.entity.SocketData;
import com.ignorant.chat.entity.WcsData;
import com.ignorant.chat.enums.ContentType;
import com.ignorant.chat.utils.JsonUtils;
import com.ignorant.chat.websocket.WebSocketManager;

@Component
public class WcsService {

	@Autowired
	private WcsClient wsClient;

	public void loginWcs(String userId) {
		wsClient.loginWc(userId);
	}

	public static void handleWcsData(Object data) {
		WcsData wcsData = JsonUtils.jsonToPojo((String) data, WcsData.class);
		WebSocketManager.send(wcsData.getUserId(), new SocketData(ContentType.wcsMsg, JsonUtils.objectToJson(wcsData)));
	}

	public void sendMsg(Msg msg) {
		wsClient.sendMsg(msg.getTo(), msg.getContent());
	}

}
