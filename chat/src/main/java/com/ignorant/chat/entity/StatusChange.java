package com.ignorant.chat.entity;

import java.util.Date;

import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ignorant.chat.Service.UserService;
import com.ignorant.chat.enums.UserStatus;

@Component
@Scope("prototype")
public class StatusChange extends AbstracScocketContent {
	@Autowired
	@Transient
	private UserService userService;

	private String from;
	private UserStatus status;
	private Date date;

	public StatusChange() {
		super();
	}

	public void start(AbstracScocketContent content) {
		setFrom(getUserId());
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
