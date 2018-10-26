package com.ignorant.chat.mapper;

import java.util.List;

import com.ignorant.pojo.MsgRecord;
import com.ignorant.utils.MyMapper;

public interface MsgRecordMapper extends MyMapper<MsgRecord> {
	public Long addMsg(MsgRecord msgRecord);

	public List<MsgRecord> queryMsg(String userId, String from, Long limitId, int limit);
}