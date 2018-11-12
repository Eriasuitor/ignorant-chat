package com.ignorant.chat.wcs.entity;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ignorant.chat.Service.UserService;

@Component
@Scope("prototype")
public class SendSuccess implements WcsSocketContent {

	private String syncId;

	@Autowired
	private UserService userService;

	@Override
	public void start(String userId, WcsSocketContent data) {
		// TODO Auto-generated method stub
		SendSuccess sendSuccess = (SendSuccess) data;
		userService.sync(userId, Stream.of(sendSuccess.getSyncId()).collect(Collectors.toList()));
	}

	public String getSyncId() {
		return syncId;
	}

	public void setSyncId(String syncId) {
		this.syncId = syncId;
	}

}
