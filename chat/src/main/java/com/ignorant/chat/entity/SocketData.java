package com.ignorant.chat.entity;

import java.util.List;

import com.ignorant.chat.enums.ContentType;

public class SocketData {
	private ContentType type;
	private List<String> syncIdList;
	private AbstracScocketContent content;

	public SocketData() {
	}

	public SocketData(ContentType type, AbstracScocketContent content, List<String> syncIdList) {
		this.type = type;
		this.content = content;
		this.syncIdList = syncIdList;
	}

	public SocketData(ContentType type, AbstracScocketContent content) {
		this(type, content, null);
	}

	public ContentType getType() {
		return type;
	}

	public void setType(ContentType type) {
		this.type = type;
	}

	public List<String> getSyncIdList() {
		return syncIdList;
	}

	public void setSyncIdList(List<String> synIdList) {
		this.syncIdList = synIdList;
	}

	public AbstracScocketContent getContent() {
		return content;
	}

	public void setContent(AbstracScocketContent content) {
		this.content = content;
	}

}
