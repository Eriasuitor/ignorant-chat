package com.ignorant.chat.entity;

import com.ignorant.chat.enums.ContentType;

public class SocketContent {
	private ContentType type;
	private String content;

	public SocketContent() {
		super();
	}

	public ContentType getType() {
		return type;
	}

	public void setType(ContentType type) {
		this.type = type;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
