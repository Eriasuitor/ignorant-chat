package com.ignorant.chat.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ignorant.chat.pojo.UserFriend;
import com.ignorant.chat.utils.MyMapper;

public interface UserFriendMapper extends MyMapper<UserFriend> {
	public boolean isFriend(@Param("userId") String userId, @Param("friendId") String friendId);

	public void addFriend(@Param("userId") String userId, @Param("friendId") String friendId);

	public void addFriendByBunch(@Param("userId") String userId, @Param("friendIdList") List<String> friendIdList);
}