package com.ignorant.chat.wcs.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class ContactList implements WcsSocketContent {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void start(String userId, WcsSocketContent data) {
		// TODO Auto-generated method stub
		logger.debug(String.format("user %s contact list update", userId));
	}

}
