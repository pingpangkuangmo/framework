package com.demo.test.dao;

import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.demo.dao.UserDao;
import com.demo.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = "classpath:spring/spring-context.xml")
public class UserDaoTest {

	@Autowired
	private UserDao userDao;
	
	@Test
	public void testSaveUser() throws SQLException{
		User user=new User();
		user.setName("wp");
		user.setAge(12);
		userDao.save(user);
	}
}
