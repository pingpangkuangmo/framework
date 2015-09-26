package com.demo.dao;

import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import org.springframework.transaction.interceptor.TransactionProxyFactoryBean;

import com.demo.entity.User;


@Repository
public class AopUserDao implements InitializingBean{

	@Autowired
	private UserDao userDao;
	
	private UserDao proxyUserDao;

	@Resource(name="transactionManager")
	private PlatformTransactionManager transactionManager;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		ProxyFactory proxyFactory = new ProxyFactory();
		proxyFactory.setTarget(userDao);
		
		TransactionInterceptor transactionInterceptor=new TransactionInterceptor();
		transactionInterceptor.setTransactionManager(transactionManager);
		Properties properties=new Properties();
		properties.setProperty("*","PROPAGATION_REQUIRED");
		transactionInterceptor.setTransactionAttributes(properties);
		
		proxyFactory.addAdvice(transactionInterceptor);
		//proxyFactory.addAdvisor(advisor);
		proxyUserDao=(UserDao) proxyFactory.getProxy();
	}
	
	public void save(User user){
		proxyUserDao.save(user);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void testAop(){
		
	}
	
	public UserDao getAOPUserDao1(){
		TransactionProxyFactoryBean transactionProxyFactory=new TransactionProxyFactoryBean();
		transactionProxyFactory.setTransactionManager(transactionManager);
		transactionProxyFactory.setTarget(userDao);
		//transactionProxyFactory.setTransactionAttributeSource();
		return null;
	}

	
}
