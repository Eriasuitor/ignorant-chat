package com.ignorant.chat.entity;

import java.util.Date;

import com.ignorant.chat.enums.UserInfoChangeType;

public class InfoChange {
	private String from;
	private UserInfoChangeType type;
	private String content;
	private Date date;

	public InfoChange() {
		super();
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public UserInfoChangeType getType() {
		return type;
	}

	public void setType(UserInfoChangeType type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
