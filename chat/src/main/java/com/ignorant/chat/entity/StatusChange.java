package com.ignorant.chat.entity;

import java.util.Date;

import com.ignorant.chat.Service.UserService;
import com.ignorant.chat.enums.UserStatus;

public class StatusChange implements SocketContent {
	private String from;
	private UserStatus status;
	private Date date;

	public StatusChange() {
		super();
	}

	public void start() {
		UserService userService = new UserService();
		userService.changeStatus(this);
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
