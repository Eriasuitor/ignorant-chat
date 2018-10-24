package com.ignorant.mapper;

import java.util.List;

import com.ignorant.pojo.User;
import com.ignorant.utils.MyMapper;

public interface UserMapper extends MyMapper<User> {
	public List<User> getFriendList(String userId);
	
	public User getUserInfo(String userId);
	
	public List<User> queryUserListByUserId(String userIdPrefix);
}