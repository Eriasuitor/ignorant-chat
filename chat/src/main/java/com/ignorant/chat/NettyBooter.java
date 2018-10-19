package com.ignorant.chat;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.ignorant.chat.netty.WSServer;

//@Component
public class NettyBooter implements ApplicationListener<ContextRefreshedEvent> {

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		System.out.println(2);
		System.out.println(event.getApplicationContext().getParent());
//		if (event.getApplicationContext().getParent() == null) {
		try {
			System.out.println(1);
			WSServer.getInstance().start();
		} catch (Exception e) {
			e.printStackTrace();
		}
//		}
	}
}
