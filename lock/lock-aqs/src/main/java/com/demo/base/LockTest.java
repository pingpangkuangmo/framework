package com.demo.base;

public class LockTest {

	private Object objLock = new Object();
	
	public static void main(String[] args) throws InterruptedException{
		LockTest test = new LockTest();
		test.test();
	}
	
	public void test() throws InterruptedException{
		synchronized (objLock) {
			System.out.println("get objLock");
			objLock.wait();
			System.out.println("reget objLock");
		}
	}
}
