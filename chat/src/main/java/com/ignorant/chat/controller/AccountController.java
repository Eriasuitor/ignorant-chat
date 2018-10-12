package com.ignorant.chat.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ignorant.chat.entity.GeneralResponse;

@RestController
public class AccountController {

//	private Logger logger = LoggerFactory.getLogger(getClass());

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(String username, String password) throws Exception {
		return "Hello Human1";
	}

	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	public GeneralResponse login() {
		return new GeneralResponse("登陆成功");
	}

	@RequestMapping(value = "/sessions", method = RequestMethod.GET)
	public Object sessions(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		map.put("sessionId", request.getSession().getId());
		map.put("message", request.getSession().getAttribute("map"));
		return map;
	}
}
