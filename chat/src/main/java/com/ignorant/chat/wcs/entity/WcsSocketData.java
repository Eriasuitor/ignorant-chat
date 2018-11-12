package com.ignorant.chat.wcs.entity;

import com.ignorant.chat.enums.WcsMsgType;

public class WcsSocketData {
	private String userId;
	private WcsMsgType type;
	private WcsSocketContent data;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public WcsMsgType getType() {
		return type;
	}

	public void setType(WcsMsgType type) {
		this.type = type;
	}

	public WcsSocketContent getData() {
		return data;
	}

	public void setData(WcsSocketContent data) {
		this.data = data;
	}

}
