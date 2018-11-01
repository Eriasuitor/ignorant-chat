package com.ignorant.chat.wcs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;


@Component
public class WcsHandler implements WebSocketHandler {

	private Logger logger = LoggerFactory.getLogger(getClass());

	public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
		logger.info("wcs client established");
	}

	public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage)
			throws Exception {
		logger.info(String.format("wcs data received %s", webSocketMessage.getPayload()));
		WcsService.handleWcsData(webSocketMessage.getPayload());
	}

	public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus arg1) throws Exception {
		logger.error(String.format("wcs client disconnected with code: %s", arg1.getCode()));
	}

	public void handleTransportError(WebSocketSession webSocketSession, Throwable e) throws Exception {
		e.printStackTrace();
		logger.error(String.format("%s, ip: %s", e.getMessage(), webSocketSession.getRemoteAddress()));
	}

	public boolean supportsPartialMessages() {
		// TODO Auto-generated method stub
		return true;
	}
}