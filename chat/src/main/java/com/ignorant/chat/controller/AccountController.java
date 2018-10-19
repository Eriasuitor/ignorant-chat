package com.ignorant.chat.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ignorant.chat.entity.GeneralResponse;
import com.ignorant.chat.netty.ChannelManager;

@RestController
public class AccountController {

//	private Logger logger = LoggerFactory.getLogger(getClass());

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> index(String username, String password) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("msg", "兹此证明，你已登录 " + ChannelManager.channelGroup.size());
		return result;
	}
//
//	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
//	public GeneralResponse login() {
//		return new GeneralResponse("登陆成功");
//	}

	@RequestMapping(value = "/authentication", method = RequestMethod.GET)
	public GeneralResponse authenticationRequired(HttpServletResponse response) {
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		return new GeneralResponse("需要认证");
	}

	@GetMapping("/me")
	public Object getCurrentUser(Authentication user, HttpServletRequest request) {
		return user;
	}
}
