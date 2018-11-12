package com.ignorant.chat.wcs;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SuccessCallback;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

import com.github.jknack.handlebars.Handlebars.SafeString;

@Component
public class WcsClient {

	@Autowired
	private WcsHandler wcsHandler;

	private Logger logger = LoggerFactory.getLogger(WcsClient.class);

	private WebSocketSession webSocketSession;

	public void init() {
		try {
			webSocketSession.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.debug("websocket session close before");
		}
		System.out.println(111);
		System.out.println(wcsHandler);
		WebSocketClient client = new StandardWebSocketClient();
		ListenableFuture<WebSocketSession> a = client.doHandshake(wcsHandler, "ws://localhost:8081");
		a.addCallback(new SuccessCallback<Object>() {
			public void onSuccess(Object result) {
				webSocketSession = (WebSocketSession) result;
				System.out.println(0);
				System.out.println(webSocketSession);
				logger.info("connect to wcs success.");
//				Thread.currentThread().suspend();
			}

		}, new FailureCallback() {
			public void onFailure(Throwable ex) {
				// TODO Auto-generated method stub
				ex.printStackTrace();
			}
		});
	}

	public void loginWc(String userId) {
		CharSequence charSequence = new SafeString(String.format("{\"type\": \"new\", \"userId\": \"%s\"}", userId));
		try {
			webSocketSession.sendMessage(new TextMessage(charSequence));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean sendMsg(String userId, String toUserId, String content, String syncId) {
		CharSequence charSequence = new SafeString(String.format(
				"{\"type\": \"msg\", \"userId\": \"%s\", \"content\": \"%s\", \"to\": \"%s\", \"syncId\": \"%s\" }",
				userId, content, toUserId, syncId));
		try {
			webSocketSession.sendMessage(new TextMessage(charSequence));
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
