package com.demo.test.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.demo.dao.UserDao;
import com.demo.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = {"classpath:spring/spring-jdbc.xml","classpath:spring/spring-context.xml"})
public class UserDaoTest {

	@Autowired
	private UserDao userDao;
	
	@Test
	public void testSaveUser(){
		User user=new User();
		user.setName("lg3");
		user.setAge(12);
		userDao.save(user);
	}
}
