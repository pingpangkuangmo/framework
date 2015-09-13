package com.demo.dubbo.client;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.demo.dubbo.service.HelloService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-context.xml")
public class HelloServiceTest {

	@Autowired
    private HelloService helloService;

    @Test
    public void helloTest() {
        String result = helloService.hello("World");
        Assert.assertEquals("Hello World", result);
    }
}
