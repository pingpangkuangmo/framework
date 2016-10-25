package com.demo.netty.bytebuf;

public class HelloB extends HelloA{

	public HelloB(){
		System.out.println("hello B 构造函数");
	}
	
	{
		System.out.println("hello B 块");
	}
	
	static{
		System.out.println("hello static B");
	}
}
