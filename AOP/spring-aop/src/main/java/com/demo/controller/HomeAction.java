package com.demo.controller;


import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.interceptor.ExposeInvocationInterceptor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import org.springframework.web.SpringServletContainerInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HomeAction {
	
	Servlet servlet;
	ExposeInvocationInterceptor exposeInvocationInterceptor;
	
	SpringServletContainerInitializer SpringServletContainerInitializer;
	DataSource dataSource;

	JdbcTemplate JdbcTemplate;
	TransactionInterceptor TransactionInterceptor;
	Statement statement;
	
	com.mysql.jdbc.Statement ss;
	
	PlatformTransactionManager p;
	
	XAResource XAResource;
	UserTransaction UserTransaction;
	TransactionManager TransactionManager;
	Xid xid;
	
	Double d;
	List list;
	
	ScheduledThreadPoolExecutor ScheduledThreadPoolExecutor;
	HttpServletRequest HttpServletRequest;
	HttpServletResponse HttpServletRequestaaa;
	ProxyFactory proxyFactory;
	//JdkDynamicAopProxy jdkDynamicAopProxy;
	//ObjenesisCglibAopProxy objenesisCglibAopProxy;
	ArrayList<String> array;
	//FinalizerThread finalizerThread;
	
	@Transactional
	@RequestMapping("/")
	public ModelAndView home(){
		return new ModelAndView("index");
	}
	
}
