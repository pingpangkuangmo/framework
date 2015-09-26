package com.demo.dubbo.server.start;

import java.io.IOException;

import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.NamespaceHandler;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.dubbo.common.bytecode.Wrapper;
import com.alibaba.dubbo.config.ServiceConfig;
import com.alibaba.dubbo.config.spring.ServiceBean;
import com.alibaba.dubbo.registry.RegistryFactory;
import com.alibaba.dubbo.remoting.Transporter;
import com.alibaba.dubbo.rpc.Protocol;
import com.alibaba.dubbo.rpc.ProxyFactory;
import com.alibaba.dubbo.rpc.cluster.Directory;

public class StartServer {
	
	NamespaceHandler namespaceHandler;
	BeanDefinitionParser beanDefinitionParser;
	ServiceBean<?> serviceBean;
	ServiceConfig<?> serviceConfig;
	Protocol protocol;
	Transporter transporter;
	Wrapper wrapper;
	ProxyFactory proxyFactory;
	Directory<?> directory;
	RegistryFactory registryFactory;

	public static void main(String[] args) throws IOException{
		@SuppressWarnings("resource")
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"spring-context.xml"});  
        context.start();  
        System.out.println("按任意键退出");  
        System.in.read();  
	}
}
