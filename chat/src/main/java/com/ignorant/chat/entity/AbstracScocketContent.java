package com.ignorant.chat.entity;

public abstract class AbstracScocketContent implements SocketContent {
	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
