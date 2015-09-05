package com.demo.lock.aqs;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class AQSTest {

	Lock lock;
	ReentrantLock reentrantLock;
	ReentrantReadWriteLock reentrantReadWriteLock;
	LockSupport lockSupport;
	HashMap<?,?> hashMap;
	Hashtable<?,?> hashTable;
	ConcurrentHashMap<?,?> currConcurrentHashMap;
}
