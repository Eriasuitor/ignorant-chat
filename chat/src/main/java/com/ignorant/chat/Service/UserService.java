package com.ignorant.chat.Service;

import java.awt.geom.NoninvertibleTransformException;
import java.io.NotActiveException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ignorant.chat.entity.InfoChange;
import com.ignorant.chat.entity.Msg;
import com.ignorant.chat.entity.SocketData;
import com.ignorant.chat.entity.StatusChange;
import com.ignorant.chat.enums.ContentType;
import com.ignorant.chat.mapper.AccountMapper;
import com.ignorant.chat.mapper.MsgFlagMapper;
import com.ignorant.chat.mapper.MsgRecordMapper;
import com.ignorant.chat.mapper.UserFriendMapper;
import com.ignorant.chat.mapper.UserMapper;
import com.ignorant.chat.utils.JsonUtils;
import com.ignorant.chat.wcs.WcsService;
import com.ignorant.chat.websocket.WebSocketManager;
import com.ignorant.pojo.MsgRecord;
import com.ignorant.pojo.User;

@Component
public class UserService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private MsgRecordMapper msgRecordMapper;

	@Autowired
	private UserFriendMapper userFriendMapper;

	@Autowired
	private MsgFlagMapper msgFlagMapper;

	@Autowired
	private AccountMapper accountMapper;

	@Autowired
	private WcsService wcsService;

	public void changeAvatar(InfoChange infochange) {
		userMapper.changeAvatar(infochange.getFrom(), infochange.getContent(), infochange.getDate());
	}

	public void changeSignature(InfoChange infochange) {
		userMapper.changeAvatar(infochange.getFrom(), infochange.getContent(), infochange.getDate());
	}

	public void changeStatus(StatusChange statusChange) {
		userMapper.changeStatus(statusChange.getFrom(), statusChange.getStatus(), statusChange.getDate());
	}

	public void sendMsg(Msg msg) {
		if (msg.getUserId().startsWith("@")) {
			wcsService.sendMsg(msg);
			return;
		}
		MsgRecord msgRecord = new MsgRecord(msg.getTo(), msg.getFrom(), msg.getType(), msg.getContent(), msg.getDate(),
				msg.getDate(), msg.getFrom(), msg.getFrom());
		System.out.println(ReflectionToStringBuilder.reflectionToString(msgRecord));
		Long msgId = msgRecordMapper.addMsg(msgRecord);
		msg.setMsgId(msgId);
		SocketData socketDate = new SocketData(ContentType.msg, JsonUtils.objectToJson(msg));
		WebSocketManager.send(msg.getFrom(), socketDate);
		WebSocketManager.send(msg.getTo(), socketDate);
	}

	public List<Msg> queryMsg(String userId, String friendId, Long anchor) {
		if (anchor == null)
			anchor = Long.MAX_VALUE;
		List<MsgRecord> msgRecordList = msgRecordMapper.queryMsg(userId, friendId, anchor, 30);
		List<Msg> result = new ArrayList<Msg>();
		msgRecordList.forEach(item -> {
			result.add(new Msg(item.getFrom(), item.getUserId(), item.getType(), item.getContent(), item.getId(),
					item.getLastEditData()));
		});
		result.sort(new Comparator<Msg>() {

			@Override
			public int compare(Msg o1, Msg o2) {
				// TODO Auto-generated method stub
				return (int) (o2.getDate().getTime() - o1.getDate().getTime());
			}
		});
		return result;
	}

	public List<User> getFriendList(String userId) {
		return userMapper.getFriendList(userId);
	}

	public User getUserInfo(String userId) {
		return userMapper.getUserInfo(userId);
	}

	public User addFriend(String userId, String friendId)
			throws NotFoundException, NotActiveException, NoninvertibleTransformException {
		User friend = getUserInfo(friendId);
		if (friend == null) {
			throw new NotFoundException(String.format("user %s is not found", friendId));
		}
		User user = getUserInfo(userId);
		if (user == null) {
			throw new NotActiveException(String.format("acount %s is invalid", userId));
		}
		if (isFriend(userId, friendId)) {
			throw new NoninvertibleTransformException(
					String.format("user %S was friend of user %s yet", friendId, userId));
		}
		userFriendMapper.addFriend(userId, friendId);
		return friend;
	}

	public boolean isFriend(String userId, String friendId) {
		return userFriendMapper.isFriend(userId, friendId);
	}

	public List<User> queryUserListByUserId(String userIdPrefix) {
		return userMapper.queryUserListByUserId(userIdPrefix);
	}

	public void updatePeak(String userId, Long peak) {
		msgFlagMapper.updatePeak(userId, peak);
	}

	public void updateCurrent(String userId, Long current) {
		msgFlagMapper.updateCurrent(userId, current);
	}
	
	public void addUser() {
		
	}
	
	public void addFriend(String userId, List<String> friendIdList)
			throws NotFoundException, NotActiveException, NoninvertibleTransformException {
		friendIdList.forEach(friendId -> {
			User friend = getUserInfo(friendId);
			if (friend == null) {
				throw new NotFoundException(String.format("user %s is not found", friendId));
			}
			User user = getUserInfo(userId);
			if (user == null) {
				throw new NotActiveException(String.format("acount %s is invalid", userId));
			}
			if (isFriend(userId, friendId)) {
				throw new NoninvertibleTransformException(
						String.format("user %S was friend of user %s yet", friendId, userId));
			}
		});
		userFriendMapper.addFriendByBunch(userId, friendIdList);
	}
}
