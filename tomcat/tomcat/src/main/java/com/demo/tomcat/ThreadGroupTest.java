package com.demo.tomcat;

public class ThreadGroupTest {

	public static void main(String[] args){
		Thread t = new Thread(){

			@Override
			public void run() {
				print("son ");
				ThreadGroupTest.sleep(3000);
			}
			
		};
		t.start();
		sleep(10000);
		print("main ");
		sleep(10000);
	}
	
	private static void print(String prefix){
		Thread current = Thread.currentThread();
		ThreadGroup tg = current.getThreadGroup();
		System.out.println(prefix + ":" + current.getName());
		System.out.println(prefix + ":" + tg.getName());
	}
	
	private static void sleep(long ms){
		try {
			Thread.sleep(ms);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
