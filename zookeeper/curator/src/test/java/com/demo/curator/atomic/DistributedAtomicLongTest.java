package com.demo.curator.atomic;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.atomic.AtomicValue;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicLong;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryNTimes;
import org.junit.Before;
import org.junit.Test;

public class DistributedAtomicLongTest {
	
	CuratorFramework client;
	
	@Before
	public void init(){
		System.out.println("start connect zk...");
		CuratorFrameworkFactory.Builder builder = CuratorFrameworkFactory.builder();  
		//fluent style  
		String namespace = "cluster-worker";  
		client = builder.connectString("127.0.0.1:2181")  
		        .sessionTimeoutMs(30000)  
		        .connectionTimeoutMs(30000)  
		        .canBeReadOnly(false)  
		        .retryPolicy(new ExponentialBackoffRetry(1000, Integer.MAX_VALUE))  
		        .namespace(namespace)  
		        .defaultData(null)  
		        .build();  
		client.start();
		System.out.println("connected zk!");
	}

	@Test
	public void testDistributedAdd(){
		try {
			DistributedAtomicLong distributedAtomicLong=new DistributedAtomicLong(client,"/cms/distributedAtomicLong/appId",new RetryNTimes(3,1000));
			distributedAtomicLong.increment();
			AtomicValue<Long> longObj=distributedAtomicLong.get();
			System.out.println(longObj.preValue());
			System.out.println(longObj.postValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
