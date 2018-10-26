package com.ignorant.chat.mapper;

import java.util.Date;
import java.util.List;

import com.ignorant.chat.enums.UserStatus;
import com.ignorant.pojo.User;
import com.ignorant.utils.MyMapper;

public interface UserMapper extends MyMapper<User> {
	public List<User> getFriendList(String userId);

	public User getUserInfo(String userId);

	public List<User> queryUserListByUserId(String userIdPrefix);

	public void changeSignature(String userId, String signature, Date date);

	public void changeStatus(String userId, UserStatus status, Date date);

	public void changeAvatar(String userId, String avatar, Date date);
}
