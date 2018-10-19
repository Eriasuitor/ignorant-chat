package com.ignorant.chat.netty;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.stereotype.Component;

//@org.aspectj.lang.annotation.Aspect
//@Component
public class Aspect {

	@Around("execution(* com.ignorant.chat.websocket.*.*(..))")
	public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {

		System.out.println("time aspect start");

		Object[] args = pjp.getArgs();
		for (Object arg : args) {
			System.out.println("arg is " + arg);
		}

		long start = new Date().getTime();

		Object object = pjp.proceed();

		System.out.println("time aspect 耗时:" + (new Date().getTime() - start));

		System.out.println("time aspect end");

		return object;
	}
}
