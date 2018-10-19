package com.ignorant.chat.entity;

import java.util.Date;

import com.ignorant.chat.enums.UserStatus;

public class StatusChange {
	private String from;
	private UserStatus status;
	private Date date;

	public StatusChange() {
		super();
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
