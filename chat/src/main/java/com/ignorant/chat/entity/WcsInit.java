package com.ignorant.chat.entity;

import com.ignorant.chat.enums.WcsMsgType;
import com.ignorant.chat.pojo.User;

public class WcsInit extends AbstracScocketContent {
	private final WcsMsgType type = WcsMsgType.init;
	private User content;

	public WcsInit(User user) {
		super();
		this.content = user;
	}

	public WcsMsgType getType() {
		return type;
	}

	public User getContent() {
		return content;
	}

	public void setContent(User content) {
		this.content = content;
	}
}
