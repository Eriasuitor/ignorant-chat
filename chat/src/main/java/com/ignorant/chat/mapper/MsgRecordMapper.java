package com.ignorant.chat.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ignorant.pojo.MsgRecord;
import com.ignorant.utils.MyMapper;

public interface MsgRecordMapper extends MyMapper<MsgRecord> {
	public Long addMsg(MsgRecord msgRecord);

	public List<MsgRecord> queryMsg(@Param("userId") String userId, @Param("from") String from,
			@Param("limitId") Long limitId, @Param("limit") int limit);
}