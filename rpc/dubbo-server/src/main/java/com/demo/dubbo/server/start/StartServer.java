package com.demo.dubbo.server.start;

import java.io.IOException;

import org.springframework.beans.factory.xml.NamespaceHandler;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.dubbo.config.spring.ServiceBean;

public class StartServer {
	
	NamespaceHandler namespaceHandler;
	ServiceBean<?> serviceBean;

	public static void main(String[] args) throws IOException{
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"spring-context.xml"});  
        context.start();  
        System.out.println("按任意键退出");  
        System.in.read();  
	}
}
