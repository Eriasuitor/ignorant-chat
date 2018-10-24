package com.ignorant.chat.Service;

import java.awt.geom.NoninvertibleTransformException;
import java.io.NotActiveException;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import com.ignorant.chat.entity.Msg;
import com.ignorant.chat.entity.StatusChange;
import com.ignorant.mapper.UserFriendMapper;
import com.ignorant.mapper.UserMapper;
import com.ignorant.pojo.User;

@Component
public class UserService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserFriendMapper userFriendMapper;

	public void changeAvatar(String userId, String url, Date date) {

	}

	public void changeSignature(String userId, String content, Date date) {

	}

	public void sendMsg(Msg msg) {

	}

	public void changeStatus(StatusChange statusChange) {

	}

	public List<User> getFriendList(String userId) {
		return userMapper.getFriendList(userId);
	}

	public User getUserInfo(String userId) {
		return userMapper.getUserInfo(userId);
	}

	public User addFriend(String userId, String friendId) throws NotFoundException, NotActiveException, NoninvertibleTransformException {
		User friend = getUserInfo(friendId);
		if (friend == null) {
			throw new NotFoundException(String.format("user %s is not found", friendId));
		}
		User user = getUserInfo(userId);
		if (user == null) {
			throw new NotActiveException(String.format("acount %s is invalid", userId));
		}
		if (isFriend(userId, friendId)) {
			throw new NoninvertibleTransformException(String.format("user %S was friend of user %s yet", friendId, userId));
		}
		userFriendMapper.addFriend(userId, friendId);
		return friend;
	}

	public boolean isFriend(String userId, String friendId) {
		return userFriendMapper.isFriend(userId, friendId);
	}
	
	public List<User> queryUserListByUserId(String userIdPrefix){
		return userMapper.queryUserListByUserId(userIdPrefix);
	}
}
