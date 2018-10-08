package com.ignorant.chat.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ignorant.chat.domain.UserEntity;

@RestController
public class AccountController {
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(String username, String password) throws Exception {
		return "Hello Human1";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(String username, String password) throws Exception {
		// 添加用户认证信息
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
		// 进行验证，这里可以捕获异常，然后返回对应信息
		try {
			subject.login(usernamePasswordToken);
		} catch (Exception e) {
			System.out.println(e);
		}
		return usernamePasswordToken.toString();
	}

	@RequestMapping(value = "/index2", method = RequestMethod.GET)
	public String index2() {
		Subject subject = SecurityUtils.getSubject();
		UserEntity principal = (UserEntity) subject.getPrincipal();
		System.out.println(principal.getName());
		System.out.println(principal.getPassword());
		System.out.println(principal.getCredentialsSalt());
		return principal.getName();
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		try {
			subject.logout();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "logout";
	}
}
