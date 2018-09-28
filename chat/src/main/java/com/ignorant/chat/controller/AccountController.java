package com.ignorant.chat.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
	@RequestMapping("/login")
	public String login() {
		return "get out!";
	}
}
