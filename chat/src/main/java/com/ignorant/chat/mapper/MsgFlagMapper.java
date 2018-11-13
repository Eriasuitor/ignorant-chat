package com.ignorant.chat.mapper;

import com.ignorant.pojo.MsgFlag;
import com.ignorant.utils.MyMapper;

public interface MsgFlagMapper extends MyMapper<MsgFlag> {
	public void updatePeak(String userId, Long peak);

	public void updateCurrent(String userId, Long current);
}