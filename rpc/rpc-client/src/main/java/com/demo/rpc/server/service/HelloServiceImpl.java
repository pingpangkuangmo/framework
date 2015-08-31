package com.demo.rpc.server.service;

import com.demo.rpc.HelloService;
import com.demo.rpc.server.annotation.RpcService;

@RpcService(HelloService.class)
public class HelloServiceImpl implements HelloService{

	@Override
	public String hello(String msg) {
		return "Hello "+msg;
	}

}
