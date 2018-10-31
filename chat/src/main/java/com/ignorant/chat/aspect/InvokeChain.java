package com.ignorant.chat.aspect;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

//@Aspect
//@Component
public class InvokeChain {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Before("execution(* com.ignorant.chat.*.*.*(..))")
	public void projectMonitor(JoinPoint joinPoint) {
		logger.info(String.format("invoke -> %s -> %s", joinPoint.getSignature().getName(),
				ReflectionToStringBuilder.reflectionToString(joinPoint.getArgs(), ToStringStyle.MULTI_LINE_STYLE)));
	}
}
