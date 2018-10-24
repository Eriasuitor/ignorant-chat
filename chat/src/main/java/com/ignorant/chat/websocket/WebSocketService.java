package com.ignorant.chat.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ignorant.chat.entity.InfoChange;
import com.ignorant.chat.entity.Msg;
import com.ignorant.chat.entity.SocketData;
import com.ignorant.chat.utils.JsonUtils;

public class WebSocketService {
	Logger logger = LoggerFactory.getLogger(getClass());

	public void getData(String userId, String data) {
		SocketData socketData = JsonUtils.jsonToPojo(data, SocketData.class);
		switch (socketData.getType()) {
		case INFOCHANGE:
			JsonUtils.jsonToPojo(socketData.getContent(), InfoChange.class);
			break;
		case MSG:
			JsonUtils.jsonToPojo(socketData.getContent(), Msg.class);
			break;
		case STATUSCHANGE:
			JsonUtils.jsonToPojo(socketData.getContent(), InfoChange.class);
			break;
		case PING:
			logger.info(String.format("user %s ping", userId));
			break;
		}
	}
}
