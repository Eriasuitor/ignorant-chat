package com.ignorant.chat.controller;

import java.awt.geom.NoninvertibleTransformException;
import java.io.NotActiveException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ignorant.chat.Service.UserInfoService;
import com.ignorant.chat.Service.UserService;
import com.ignorant.chat.entity.GeneralResponse;
import com.ignorant.chat.entity.Msg;
import com.ignorant.chat.mapper.AccountMapper;
import com.ignorant.pojo.User;

import tk.mybatis.spring.annotation.MapperScan;

@RestController
@MapperScan("com.ignorant.chat.mapper")
@CrossOrigin
public class AccountController {

//	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private AccountMapper am;

	@Autowired
	private UserInfoService uis;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> index(String username, String password) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("msg", "兹此证明，你已登录 ");
//		result.put("info", am.queryAccount("Lory.Y.Jiang"));
		result.put("loginfo", uis.getAuthentication());
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

	@GetMapping("/user/me")
	public User getUserInfo(Authentication user) {
		return userService.getUserInfo(user.getName());
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
			e.printStackTrace();
			result.put("msg", e.getMessage());
		} catch (NotFoundException e) {
			response.setStatus(HttpStatus.NOT_FOUND.value());
			e.printStackTrace();
			result.put("msg", e.getMessage());
		} catch (NoninvertibleTransformException e) {
			response.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
			e.printStackTrace();
			result.put("msg", e.getMessage());
		}
		return result;
	}

	@GetMapping("/user")
	public List<User> queryUser(Authentication user, @RequestParam String q) {
		return userService.queryUserListByUserId(q);
	}

	@GetMapping("/user/friend/message")
	public List<Msg> queryMessage(Authentication user, String friendId, Long anchor) {
		return userService.queryMsg(user.getName(), friendId, anchor);
	}
}
