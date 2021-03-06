package com.ignorant.chat.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.ignorant.chat.service.WebSocketService;

public class MyHandler implements WebSocketHandler {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private WebSocketService webSocketService;

	public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
		logger.info(String.format("%s has connected with ip: %s", webSocketSession.getPrincipal().getName(),
				webSocketSession.getRemoteAddress()) + WebSocketManager.getUserAmountOfOnline());
		WebSocketManager.addUser(webSocketSession.getPrincipal().getName(), webSocketSession);
	}

	public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage)
			throws Exception {
		logger.info(String.format("data received user: %s, message: %s", webSocketSession.getPrincipal().getName(),
				webSocketMessage.getPayload()));
		webSocketService.operate(webSocketSession.getPrincipal().getName(), webSocketMessage.getPayload());

	}

	public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus arg1) throws Exception {
		WebSocketManager.removeUser(webSocketSession.getPrincipal().getName());
		logger.info(String.format("%s has disconnected with ip: %s", webSocketSession.getPrincipal().getName(),
				webSocketSession.getRemoteAddress()) + WebSocketManager.getUserAmountOfOnline());
	}

	public void handleTransportError(WebSocketSession webSocketSession, Throwable e) throws Exception {
		e.printStackTrace();
		logger.error(String.format("%s, user: %s, ip: %s", e.getMessage(), webSocketSession.getPrincipal().getName(),
				webSocketSession.getRemoteAddress()));
	}

	public boolean supportsPartialMessages() {
		// TODO Auto-generated method stub
		return false;
	}
}