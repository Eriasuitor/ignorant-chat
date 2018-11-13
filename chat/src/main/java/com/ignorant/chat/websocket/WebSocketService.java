package com.ignorant.chat.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
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
			if (!(data instanceof String))
				return;
			JSONObject jsonObject = JSONObject.parseObject((String) data);
			JSONObject jsonObejctContent = jsonObject.getJSONObject("content");
			String type = jsonObject.getString("type");
			AbstracScocketContent content = (AbstracScocketContent) jsonObejctContent
					.toJavaObject(Class.forName("com.ignorant.chat.entity." + type.substring(0, 1).toUpperCase()
							+ type.substring(1, type.length())));
			AbstracScocketContent abstracSocketContent = (AbstracScocketContent) context
					.getBean(jsonObject.getString("type"));

//			SocketData socketData = JSON.parseObject((String) data, SocketData.class);
////					JsonUtils.jsonToPojo((String) data, SocketData.class);
//			abstracSocketContent.setUserId(userId);
			content.setUserId(userId);
			content.setSyncIdList(jsonObject.getJSONArray("syncId").toJavaList(String.class));
			abstracSocketContent.start(content);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(String.format("格式化文件出错,源：%s，错误：%s", JsonUtils.objectToJson(data), e.getMessage()));
			e.printStackTrace();
		}
	}

	public boolean send(String userId, SocketData socketData) {
		return WebSocketManager.send(userId, socketData);
	}
}