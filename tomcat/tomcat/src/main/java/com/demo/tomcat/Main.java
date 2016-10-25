package com.demo.tomcat;

import java.util.Hashtable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

import javax.servlet.ServletContextListener;

import org.apache.catalina.Engine;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.ApplicationContext;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardServer;
import org.apache.catalina.core.StandardService;
import org.apache.catalina.loader.WebappClassLoaderBase;
import org.apache.catalina.startup.Catalina;
import org.apache.catalina.startup.CatalinaProperties;
import org.apache.coyote.AbstractProtocol;
import org.apache.tomcat.util.net.AbstractEndpoint.Acceptor;
import org.apache.tomcat.util.net.NioEndpoint;
import org.apache.tomcat.util.net.NioEndpoint.Poller;

public class Main {

	StandardServer standardServer;
	
	StandardService standardService;
	
	Connector connector;
	
	StandardContext standardContext;
	
	ThreadLocal<String> threadLocal;
	
	Poller poller;
	
	org.apache.catalina.startup.Bootstrap st;
	
	NioEndpoint nioEndpoint;
	Executors executors2;
	
	Catalina catalina;
	CatalinaProperties catalinaProperties;
	Engine engine;
	Acceptor acceptor;
	
	//旧版本7.0
	
	Executor executor;
	ThreadPoolExecutor threadPoolExecutor;
	FutureTask<String> futureTask;
	Executors executors;
	ScheduledExecutorService scheduledExecutorService;
	ForkJoinPool forkJoinPool;
	
	AtomicLong atomicLong;
	LockSupport lockSupport;
	AbstractQueuedSynchronizer aqs;
	
	Hashtable asd;
	Lock lock;
	
	ServletContextListener  dssdl;
	WebappClassLoaderBase sds;
	AbstractProtocol<?> sdfdsfd;
	StandardService standardService2;
	StandardServer standardServer2;
	
}
