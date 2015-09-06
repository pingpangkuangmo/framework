package com.demo.lock.hashmap;

import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;

public class ConcurrentHashMapTest {
	
	private ConcurrentHashMap<String,String> map=new ConcurrentHashMap<String,String>();

	@Test
	public void testPut(){
		map.put("key1","value1");
		map.get("key1");
	}
}
