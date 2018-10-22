package com.ignorant.chat.test;

import java.util.Collection;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;

public class RoleBasedVoter implements AccessDecisionVoter<Object> {

	public boolean supports(ConfigAttribute attribute) {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> attributes) {
		// TODO Auto-generated method stub
		return 0;
	}

}
