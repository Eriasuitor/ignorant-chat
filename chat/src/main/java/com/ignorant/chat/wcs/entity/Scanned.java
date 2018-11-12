package com.ignorant.chat.wcs.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ignorant.chat.entity.SocketData;
import com.ignorant.chat.entity.WcsNotification;
import com.ignorant.chat.enums.ContentType;
import com.ignorant.chat.enums.WcsMsgType;
import com.ignorant.chat.websocket.WebSocketService;

@Component
@Scope("prototype")
public class Scanned implements WcsSocketContent {

	private WebSocketService webSocketService;

	@Override
	public void start(String userId, WcsSocketContent data) {
		// TODO Auto-generated method stub
		webSocketService.send(userId,
				new SocketData(ContentType.wcsNotification, new WcsNotification(WcsMsgType.scaned, null)));
	}
}
