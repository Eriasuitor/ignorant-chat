package com.ignorant.chat.entity;

import java.util.List;

import com.ignorant.chat.enums.ContentType;

public class SocketData {
	private ContentType type;
	private List<Long> synId;
	private String content;

	public SocketData() {
	}

	public SocketData(ContentType type, String content) {
		this.type = type;
		this.content = content;
	}

	public ContentType getType() {
		return type;
	}

	public void setType(ContentType type) {
		this.type = type;
	}

	public List<Long> getSynId() {
		return synId;
	}

	public void setSynId(List<Long> synId) {
		this.synId = synId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
