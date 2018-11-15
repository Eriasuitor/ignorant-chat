package com.ignorant.chat.service;

import java.awt.geom.NoninvertibleTransformException;
import java.io.NotActiveException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.apache.ibatis.javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ignorant.chat.entity.InfoChange;
import com.ignorant.chat.entity.Msg;
import com.ignorant.chat.entity.SocketData;
import com.ignorant.chat.entity.StatusChange;
import com.ignorant.chat.enums.ContentType;
import com.ignorant.chat.mapper.MsgFlagMapper;
import com.ignorant.chat.mapper.MsgRecordMapper;
import com.ignorant.chat.mapper.UserFriendMapper;
import com.ignorant.chat.mapper.UserMapper;
import com.ignorant.chat.pojo.MsgRecord;
import com.ignorant.chat.pojo.User;
import com.ignorant.chat.websocket.WebSocketManager;

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
	private WebSocketService webSocketService;

	@Autowired
	private WcsService wcsService;

	Logger logger = LoggerFactory.getLogger(getClass());

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
		if (getUserInfo(msg.getTo()) == null) {
			wcsService.sendMsg(msg.getFrom(), msg.getTo(), msg.getContent(), msg.getSyncIdList());
			return;
		}
		MsgRecord msgRecord = new MsgRecord(msg.getTo(), msg.getFrom(), msg.getType(), msg.getContent(), msg.getDate(),
				msg.getDate(), msg.getFrom(), msg.getFrom());
		msgRecordMapper.addMsg(msgRecord);
		msg.setMsgId(msgRecord.getId());
		msgRecordMapper.addMsg(msgRecord);
		SocketData socketDate = new SocketData(ContentType.msg, msg);
		if (msg.getFrom().equals(msg.getUserId()))
			WebSocketManager.send(msg.getTo(), socketDate);
		else if (msg.getTo().equals(msg.getUserId()))
			WebSocketManager.send(msg.getFrom(), socketDate);
		else {
			throw new RuntimeException("neither to nor from is not match to userId");
		}
		this.sync(msg.getUserId(), msg.getSyncIdList());
	}

	public void sync(String userId, List<String> syncList) {
		webSocketService.send(userId, new SocketData(ContentType.sync, null, syncList));
	}

	public List<Msg> queryMsg(String userId, String friendId, Long anchor) {
		if (anchor == null)
			anchor = Long.MAX_VALUE;
		List<MsgRecord> msgRecordList = msgRecordMapper.queryMsg(userId, friendId, anchor, 20);
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

	/**
	 * Get ic friend list + wcs init contact list(not contact list)
	 */
	public List<User> getFriendList(String userId) {
		List<User> wcInitFriendList = wcsService.getInitContact(userId);
		for (int i = 0; i < wcInitFriendList.size(); i++) {
			for (int j = 0; j < i; j++) {
				if (wcInitFriendList.get(i).getUserId().equals(wcInitFriendList.get(j).getUserId())) {
					wcInitFriendList.remove(i--);
				}
			}
		}
		List<User> friendList = userMapper.getFriendList(userId);
		friendList.forEach(f -> f.setMsgRecord(this.queryMsg(userId, f.getUserId(), null)));
		wcInitFriendList.forEach(f -> f.setMsgRecord(new ArrayList<>()));
		List<User> result = new ArrayList<User>();
		int i = Math.min(wcInitFriendList.size(), friendList.size()), j = -1;
		while (++j < i) {
			result.add(wcInitFriendList.get(j));
			result.add(friendList.get(j));
		}
		if (wcInitFriendList.size() > friendList.size()) {
			result.addAll(wcInitFriendList.subList(i, wcInitFriendList.size() - 1));
		} else if (wcInitFriendList.size() < friendList.size()) {
			result.addAll(friendList.subList(i, friendList.size() - 1));
		}
		return result;
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
		logger.info("add friend success {userId: {}, friendId: {}}", userId, friendId);
		return friend;
	}

	public boolean isFriend(String userId, String friendId) {
		return userFriendMapper.isFriend(userId, friendId);
	}

	public List<User> queryUserListByUserId(String userId, String userIdPrefix) {
		char[] charArray = userIdPrefix.toCharArray();
		boolean isValid = true;
		for (int i = 0; i < charArray.length; i++) {
			if (charArray[i] < ' ' || charArray[i] > '~') {
				isValid = false;
				break;
			}
		}
		List<User> wcInitFriendList = wcsService.queryContact(userId, userIdPrefix);
		if (!isValid)
			return wcInitFriendList;
		List<User> friendList = userMapper.queryUserListByUserId(userIdPrefix);
		System.out.println(friendList.size());
		List<User> result = new ArrayList<User>();
		int i = Math.min(wcInitFriendList.size(), friendList.size()), j = -1;
		while (++j < i) {
			result.add(wcInitFriendList.get(j));
			result.add(friendList.get(j));
		}
		if (wcInitFriendList.size() > friendList.size()) {
			result.addAll(wcInitFriendList.subList(i, wcInitFriendList.size()));
		} else if (wcInitFriendList.size() < friendList.size()) {
			result.addAll(friendList.subList(i, friendList.size()));
		}
		return result;
	}

	public void updatePeak(String userId, Long peak) {
		msgFlagMapper.updatePeak(userId, peak);
	}

	public void updateCurrent(String userId, Long current) {
		msgFlagMapper.updateCurrent(userId, current);
	}

	public void addFriend(String userId, List<String> friendIdList)
			throws NotFoundException, NotActiveException, NoninvertibleTransformException {
		for (String friendId : friendIdList) {
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
		}
		userFriendMapper.addFriendByBunch(userId, friendIdList);
	}

}
