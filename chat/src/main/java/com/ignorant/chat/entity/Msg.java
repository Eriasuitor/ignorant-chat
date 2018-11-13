package com.ignorant.chat.entity;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ignorant.chat.Service.UserService;
import com.ignorant.chat.enums.MsgType;
import com.ignorant.chat.utils.JsonUtils;

@Component
@Scope("prototype")
public class Msg extends AbstracScocketContent {

	@Autowired
	private UserService userService;

	private String from;
	private String to;
	private MsgType type;
	private String content;
	private Long msgId;
	private Date date;

	public Msg() {
		super();
	}

	public Msg(String from, String to, MsgType type, String content, Long msgId, Date date) {
		super();
		this.from = from;
		this.to = to;
		this.type = type;
		this.content = content;
		this.msgId = msgId;
		this.date = date;
	}

	public void start(AbstracScocketContent content) {
		Msg msg = (Msg) content;
		setFrom(msg.getUserId());
		setTo(msg.getTo());
		setUserId(msg.getUserId());
		setType(msg.getType());
		setContent(msg.getContent());
		setSyncIdList(msg.getSyncIdList());
		setDate(new Date());
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

	public Long getMsgId() {
		return msgId;
	}

	public void setMsgId(Long msgId) {
		this.msgId = msgId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
