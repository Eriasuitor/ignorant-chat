package com.ignorant.chat.mapper;

import com.ignorant.chat.pojo.MsgFlag;
import com.ignorant.chat.utils.MyMapper;

public interface MsgFlagMapper extends MyMapper<MsgFlag> {
	public void updatePeak(String userId, Long peak);

	public void updateCurrent(String userId, Long current);
}