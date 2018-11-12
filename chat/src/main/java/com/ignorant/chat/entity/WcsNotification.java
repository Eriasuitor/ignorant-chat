package com.ignorant.chat.entity;

import com.ignorant.chat.enums.WcsMsgType;

public class WcsNotification extends AbstracScocketContent {
	private WcsMsgType type;
	private String content;

	public WcsNotification(WcsMsgType type, String content) {
		super();
		this.type = type;
		this.content = content;
	}

	public WcsMsgType getType() {
		return type;
	}

	public void setType(WcsMsgType type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
