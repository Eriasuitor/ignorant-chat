package com.ignorant.chat.websocket;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.github.jknack.handlebars.Handlebars.SafeString;
import com.ignorant.chat.entity.SocketData;
import com.ignorant.chat.utils.JsonUtils;

public class WebSocketManager {
	public static Logger logger = LoggerFactory.getLogger(WebSocketManager.class);
	public static ConcurrentMap<String, WebSocketSession> username2Socket = new ConcurrentHashMap<String, WebSocketSession>();
	public static ConcurrentMap<WebSocketSession, String> socket2Username = new ConcurrentHashMap<WebSocketSession, String>();

	public static boolean isOnline(String userId) {
		return username2Socket.containsKey(userId);
	}

	public static void addUser(String userId, WebSocketSession webSocketSession) {
		username2Socket.put(userId, webSocketSession);
	}

	public static WebSocketSession removeUser(String userId) {
		return username2Socket.remove(userId);
	}

	public static boolean send(String userId, SocketData socketData) {
		WebSocketSession webSocketSession = getSocket(userId);
		if (webSocketSession == null)
			return false;
		CharSequence charSequence = new SafeString(JsonUtils.objectToJson(socketData));
		try {
			webSocketSession.sendMessage(new TextMessage(charSequence));
		} catch (IOException e) {
			logger.error(e.getMessage());
			return false;
		}
		return true;
	}

	public static int send(List<String> userIdList, SocketData socketData) {
		int success = 0;
		for (String userId : userIdList) {
			if (send(userId, socketData))
				success++;
		}
		return success;
	}

	public static int getUserAmountOfOnline() {
		return username2Socket.size();
	}

	public static WebSocketSession getSocket(String userId) {
		return username2Socket.get(userId);
	}

}
