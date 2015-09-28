package com.demo.dubbo.server.start;

import java.io.IOException;

import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.NamespaceHandler;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.dubbo.common.bytecode.Wrapper;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.alibaba.dubbo.config.spring.ReferenceBean;
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
	ReferenceBean<?> reference;
	ReferenceConfig<?> referenceConfig;
	Protocol protocol;
	Transporter transporter;
	Wrapper wrapper;
	ProxyFactory proxyFactory;
	Directory<?> directory;
	RegistryFactory registryFactory;
	ExtensionLoader<?>  extensionLoader;

	public static void main(String[] args) throws IOException{
		@SuppressWarnings("resource")
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"spring-context.xml"});  
        context.start();  
        System.out.println("按任意键退出");  
        System.in.read();  
	}
}
