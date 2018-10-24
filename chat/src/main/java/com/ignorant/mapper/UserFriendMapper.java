package com.ignorant.mapper;

import com.ignorant.pojo.UserFriend;
import com.ignorant.utils.MyMapper;

public interface UserFriendMapper extends MyMapper<UserFriend> {
	public boolean isFriend(String userId, String friendId);

	public void addFriend(String userId, String friendId);
}