package com.ignorant.chat.controller;

import java.awt.geom.NoninvertibleTransformException;
import java.io.NotActiveException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ignorant.chat.entity.GeneralResponse;
import com.ignorant.chat.entity.Msg;
import com.ignorant.chat.pojo.User;
import com.ignorant.chat.service.UserInfoService;
import com.ignorant.chat.service.UserService;
import com.ignorant.chat.service.WcsService;

import tk.mybatis.spring.annotation.MapperScan;

@RestController
@MapperScan("com.ignorant.chat.mapper")
@CrossOrigin
@ResponseBody
public class UserController {

	@Autowired
	private UserInfoService uis;

	@Autowired
	private UserService userService;

	@Autowired
	private WcsService wcsService;

	private Logger logger = LoggerFactory.getLogger(getClass());

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public Map<String, Object> index(String username, String password) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("msg", "兹此证明，你已登录 ");
		result.put("loginfo", uis.getAuthentication());
		return result;
	}

	@RequestMapping(value = "/authentication", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "登录后方能访问您所需资源")
	public GeneralResponse authenticationRequired() {
		return new GeneralResponse("需要认证");
	}

	@GetMapping("/me")
	public User getUserInfo(Authentication user) {
		return userService.getUserInfo(user.getName());
	}

	@GetMapping("/user/{userId}")
	public User getUserInfo(Authentication user, @PathVariable String userId) {
		return userService.getUserInfo(userId);
	}

	@GetMapping("/user/friend")
	public List<User> getUserFriendList(Authentication user) {
		return userService.getFriendList(user.getName());
	}

	@PostMapping("/user/friend")
	public Map<String, Object> addFriend(Authentication user, @RequestBody String friendId,
			HttpServletResponse response) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result.put("data", userService.addFriend(user.getName(), friendId));
			result.put("msg", "success");
		} catch (NotActiveException e) {
			response.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
			result.put("msg", e.getMessage());
			logger.error("Add friend with no user info {user: {}, friend: {}}", user.getName(), friendId);
		} catch (NotFoundException e) {
			response.setStatus(HttpStatus.NOT_FOUND.value());
			result.put("msg", e.getMessage());
			logger.error("Add friend with nobody {user: {}, friend: {}}", user.getName(), friendId);
		} catch (NoninvertibleTransformException e) {
			response.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
			result.put("msg", e.getMessage());
			logger.error("Add friend when has been {user: {}, friend: {}}", user.getName(), friendId);
		}
		return result;
	}

	@GetMapping("/user")
	public List<User> queryUser(Authentication user, @RequestParam String q) {
		return userService.queryUserListByUserId(user.getName(), q);
	}

	@GetMapping("/user/friend/{friendId}/message")
	public List<Msg> queryMessage(Authentication user, @PathVariable String friendId, Long anchor) {
		return userService.queryMsg(user.getName(), friendId, anchor);
	}

	@PostMapping("/wcs")
	public void loginWcs(Authentication user, HttpServletResponse response) {
		logger.debug("user prepare to login wcs {user: {}}", user.getName());
		wcsService.loginWcs(user.getName());
	}

//	@GetMapping("/user/friend/message")
//	public List<Msg> queryMessageBunch(Authentication user, List<String> friendIdList, Long anchor) {
//		return userService.queryMsg(user.getName(), friendIdList, anchor);
//	}
}
