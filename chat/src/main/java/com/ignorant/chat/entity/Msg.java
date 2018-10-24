package com.ignorant.chat.entity;

import java.util.Date;

import com.ignorant.chat.Service.UserService;

enum MsgType {
	IMG, TEXT, FILE, MP3, MP4
}

public class Msg implements SocketContent {
	private String from;
	private String to;
	private MsgType type;
	private String content;
	private int msgId;
	private Date date;

	public Msg() {
		super();
	}

	public void start() {
		UserService userService = new UserService();
		userService.sendMsg(this);
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public MsgType getType() {
		return type;
	}

	public void setType(MsgType type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getMsgId() {
		return msgId;
	}

	public void setMsgId(int msgId) {
		this.msgId = msgId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
