package com.demo.quartz.base;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.quartz.impl.jdbcjobstore.SimpleSemaphore;

public class LockTest {

	private static SimpleSemaphore lock = new SimpleSemaphore();
	private static Lock lock1 = new ReentrantLock();
	private static Condition con = lock1.newCondition();
	
	public static void main(String[] args) throws Exception{
		con.await();
		System.out.println("========");
	}
}
