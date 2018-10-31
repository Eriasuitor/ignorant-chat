package com.ignorant.chat.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Ping extends AbstracScocketContent {
	Logger logger = LoggerFactory.getLogger(getClass());

	public void start(String content) {
		// TODO Auto-generated method stub
		logger.info(String.format("user %s ping", getUserId()));
	}

}
