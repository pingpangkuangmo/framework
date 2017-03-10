package com.demo.dubbo.client;

import com.alibaba.dubbo.config.spring.ReferenceBean;
import com.alibaba.dubbo.remoting.Server;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.cluster.LoadBalance;
import com.alibaba.dubbo.rpc.proxy.InvokerInvocationHandler;

public class Start {

	ReferenceBean<?> referenceBean;
	InvokerInvocationHandler asd;
	LoadBalance loadBalance;
	Server server;
	Invoker invoker;
}
