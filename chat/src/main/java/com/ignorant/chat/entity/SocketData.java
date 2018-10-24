package com.ignorant.chat.entity;

import com.ignorant.chat.enums.ContentType;

public class SocketData {
	private ContentType type;
	private String content;

	public SocketData() {
		super();
	}

	public ContentType getType() {
		return type;
	}

	public void setType(ContentType type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
