package com.lg.design.singleton.hungry;

public final class Singleton{
	
	private static final Singleton instance=new Singleton();
	
	private Singleton(){
		System.out.println("执行构造函数");
		System.out.println("类加载器="+this.getClass().getClassLoader());
	}
	
	public static Singleton getInstance(){
		return instance;
	}

}
