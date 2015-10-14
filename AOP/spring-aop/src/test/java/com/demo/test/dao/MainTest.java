package com.demo.test.dao;

import java.lang.reflect.InvocationHandler;

import org.aopalliance.aop.Advice;
import org.aspectj.lang.annotation.Before;
import org.springframework.aop.BeforeAdvice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.framework.adapter.MethodBeforeAdviceInterceptor;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;

public class MainTest {
	
	ProxyFactory proxyFactory;
	ProxyFactoryBean proxyFactoryBean;
	Pointcut pointcut;
	Advice advice;
	BeanNameAutoProxyCreator beanNameAutoProxyCreator;
	InvocationHandler h;
	BeforeAdvice beforeAdvice;
	MethodBeforeAdviceInterceptor methodBeforeAdviceInterceptor;

	@Before(value = "")
	public void test(){
		
	}
}
