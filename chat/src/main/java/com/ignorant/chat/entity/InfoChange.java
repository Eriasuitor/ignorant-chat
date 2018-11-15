package com.ignorant.chat.entity;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ignorant.chat.enums.UserInfoChangeType;
import com.ignorant.chat.service.UserService;

@Component
@Scope("prototype")
public class InfoChange extends AbstracScocketContent {
	private String from;
	private UserInfoChangeType type;
	private String content;
	private Date date;

	public InfoChange() {
		super();
	}

	public void start(AbstracScocketContent content) {
		UserService userService = new UserService();
		setFrom(getUserId());
		switch (type) {
		case AVARTAR:
			userService.changeAvatar(this);
			break;
		case SIGNATURE:
			userService.changeSignature(this);
			break;
		}
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
