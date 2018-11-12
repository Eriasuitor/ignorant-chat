package com.ignorant.chat.entity;

import java.util.Date;
import java.util.List;

public abstract class AbstracScocketContent {
	private String userId;
	private Date date;
	private List<String> syncIdList;

	public void start(AbstracScocketContent content) {
		throw new RuntimeException(String.format("Msg type %s was not support to handle", this.getClass().getName()));
	};

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<String> getSyncIdList() {
		return syncIdList;
	}

	public void setSyncIdList(List<String> syncId) {
		this.syncIdList = syncId;
	}

}
