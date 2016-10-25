package com.demo.hystrix.base;

import java.util.concurrent.Future;

import org.junit.Test;

public class Server {

	@Test
	public void sync(){
		String ret = new CommandHelloWorld("lg").execute();
		System.out.println(ret);
	}
	
	
	@Test
	public void async() throws Exception{
		Future<String> future = new CommandHelloWorld("lg").queue();
		String ret = future.get();
		System.out.println(ret);
	}
}
