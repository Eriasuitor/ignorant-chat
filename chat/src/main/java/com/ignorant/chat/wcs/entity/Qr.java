package com.ignorant.chat.wcs.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ignorant.chat.entity.SocketData;
import com.ignorant.chat.entity.WcsNotification;
import com.ignorant.chat.enums.ContentType;
import com.ignorant.chat.enums.WcsMsgType;
import com.ignorant.chat.service.WebSocketService;

@Component
@Scope("prototype")
public class Qr implements WcsSocketContent {

	private String url;

	@Autowired
	private WebSocketService webSocketService;

	@Override
	public void start(String userId, WcsSocketContent data) {
		// TODO Auto-generated method stub
		Qr qr = (Qr) data;
		webSocketService.send(userId,
				new SocketData(ContentType.wcsNotification, new WcsNotification(WcsMsgType.qr, qr.getUrl())));
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
