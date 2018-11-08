package com.ignorant.chat.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.ignorant.chat.entity.AbstracScocketContent;
import com.ignorant.chat.entity.SocketData;
import com.ignorant.chat.utils.JsonUtils;

@Component
public class WebSocketService {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ApplicationContext context;

	public void operate(String userId, Object data) {
		try {
			SocketData socketData = JsonUtils.jsonToPojo((String) data, SocketData.class);
			AbstracScocketContent abstracSocketContent = (AbstracScocketContent) context.getBean(socketData.getType().name());
			abstracSocketContent.setUserId(userId);
			System.out.println("com.ignorant.chat.entity." + socketData.getType().name());
			abstracSocketContent.start(socketData.getContent());
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("格式化文件出错" + JsonUtils.objectToJson(data));
		}
	}
}