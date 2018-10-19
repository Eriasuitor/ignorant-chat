package com.ignorant.chat.netty;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class ChannelManager {
	public static Logger logger = LoggerFactory.getLogger(ChannelManager.class);
	public static ConcurrentMap<String, Channel> username2Channel = new ConcurrentHashMap<String, Channel>();
	public static ConcurrentMap<Channel, String> channel2Username = new ConcurrentHashMap<Channel, String>();
	public static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

	public static boolean userIsOnline(String username) {
		return username2Channel.containsKey(username);
	}

	public static boolean channelWasAuthenticated(Channel channel) {
		return channel2Username.containsKey(channel);
	}

	public static void addChannel(Channel channel) {
		channelGroup.add(channel);
		logger.info("New websocket client {} connected", channel.id().asLongText());
	}

	public static void removeChannel(Channel channel) {
		channelGroup.remove(channel);
		logger.info("Websocket client {} disconnected", channel.id().asLongText());
	}

	public static void addUser(String username, Channel channel) {
		username2Channel.put(username, channel);
		channel2Username.put(channel, username);
		logger.info("User {} online now {}", username, channel.id().asLongText());
	}

	public static void removeUser(String username) {
		Channel channel = username2Channel.get(username);
		removeUser(username, channel);
		removeChannel(channel);
	}

	public static void removeUser(Channel channel) {
		String username = channel2Username.get(channel);
		removeUser(username, channel);
	}

	public static void removeUser(String username, Channel channel) {
		if (channel != null) {
			channel2Username.remove(channel);
			channelGroup.remove(channel);
		}
		if (username != null)
			username2Channel.remove(username);
		logger.info("User {} offline now {}", username, channel.id().asLongText());
	}
}
