package com.ignorant.chat.wcs.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ignorant.chat.entity.SocketData;
import com.ignorant.chat.entity.WcsInit;
import com.ignorant.chat.enums.ContentType;
import com.ignorant.chat.pojo.User;
import com.ignorant.chat.service.WcsService;
import com.ignorant.chat.service.WebSocketService;

@Component
@Scope("prototype")
public class Init extends Contact implements WcsSocketContent {

	@Autowired
	private WebSocketService websocketService;

	@Override
	public void start(String userId, WcsSocketContent data) {
		// TODO Auto-generated method stub
		Init contact = (Init) data;
		User user = new User();
		user.setUserId(contact.getUserName());
		user.setNickName(contact.getNickName());
		user.setAvatar(contact.getHeadImgUrl());
		user.setAvatar_small(contact.getHeadImgUrl());
		user.setGender(contact.getSex());
		user.setSignature(contact.getSignature());
		WcsService.user2WcInfo.put(userId, user);
		websocketService.send(userId, new SocketData(ContentType.wcsNotification, new WcsInit(user)));
	}

}
