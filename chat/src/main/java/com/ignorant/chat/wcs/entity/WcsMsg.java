package com.ignorant.chat.wcs.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class WcsMsg implements WcsSocketContent {

	@Override
	public void start(String userId, WcsSocketContent data) {
		// TODO Auto-generated method stub
		
	}

}
