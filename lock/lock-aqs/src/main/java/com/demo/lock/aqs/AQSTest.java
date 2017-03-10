package com.demo.lock.aqs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class AQSTest {

	Lock lock;
	Condition condition;
	ReentrantLock reentrantLock;
	ReentrantReadWriteLock reentrantReadWriteLock;
	CyclicBarrier cyclicBarrier;
	CountDownLatch countDownLatch;
	
	LockSupport lockSupport;
	HashMap<?,?> hashMap;
	Hashtable<?,?> hashTable;
	ConcurrentHashMap<?,?> currConcurrentHashMap;
	ConcurrentLinkedQueue<?> concurrentLinkedQueue;
	
	ForkJoinTask<?> forkJoinTask;
	
	ArrayList<?> arrayList;
	CopyOnWriteArrayList<?> copyOnWriteArrayList;
	
	AtomicInteger atomicInteger;
	
	AbstractQueuedSynchronizer asa;
	
	Object object;
	
	ConcurrentHashMap<String, String> sfdfd;
	AtomicReference<String> sfdfdf;
	
	public static void main(String[] args){
		ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>(24);
		map.put("key", "value");
		System.out.println(map.get("key"));
		System.out.println(Integer.numberOfLeadingZeros(1024));
		System.out.println(1 << 2);
		System.out.println(tableSizeFor(12));
	}
	
	public static void test(){
		
	}
	
	private static final int tableSizeFor(int c) {
        int n = c - 1;
        int a = n >>> 1; 
        n |= a;
        int b = n >>> 1;
        n |= b;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return n + 1;
    }
}


















