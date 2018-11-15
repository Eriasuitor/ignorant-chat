package com.ignorant.chat.wcs.entity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ignorant.chat.entity.Msg;
import com.ignorant.chat.entity.SocketData;
import com.ignorant.chat.enums.ContentType;
import com.ignorant.chat.enums.MsgType;
import com.ignorant.chat.pojo.User;
import com.ignorant.chat.service.WcsService;
import com.ignorant.chat.service.WebSocketService;

@Component
@Scope("prototype")
public class WcsMsg implements WcsSocketContent {

	@Autowired
	private WebSocketService websocketService;

	private List<WcsMsgContent> msgList;

	public List<WcsMsgContent> getMsgList() {
		return msgList;
	}

	public void setMsgList(List<WcsMsgContent> msgList) {
		this.msgList = msgList;
	}

	@Override
	public void start(String userId, WcsSocketContent data) {
		// TODO Auto-generated method stub
		WcsMsg wcsMsg = (WcsMsg) data;
		User user = WcsService.user2WcInfo.get(userId);
		System.out.println(userId);
		System.out.println(user);
		if (user == null)
			return;
		for (WcsMsgContent wmc : wcsMsg.getMsgList()) {
			Msg msg = new Msg(wmc.getFromUserName(), wmc.getToUserName(), MsgType.text, wmc.getContent(),
					Long.valueOf(wmc.getMsgId()), wmc.getCreateTime());
			msg.setUserId(userId);
			if (msg.getFrom().equals(user.getUserId()))
				msg.setFrom(userId);
			else if (msg.getTo().equals(user.getUserId()))
				msg.setTo(userId);
			websocketService.send(userId, new SocketData(ContentType.msg, msg));
		}
	}
}
