package com.demo.redis;


import java.net.Socket;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import redis.clients.jedis.Connection;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPipeline;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.jedis.Transaction;

public class RedisCastMain {
	
	Socket socket;
	Connection con;
	JedisPool jedisPool;
	Jedis jedis = new Jedis("192.168.186.128", 6379);
	ShardedJedis shardedJedis;
	ShardedJedisPool shardedJedisPool;

	public static void main(String[] args) throws Exception{
		Jedis jedis = new Jedis("192.168.186.128", 6379);
		System.out.println("get name=" + jedis.get("name"));
		System.out.println("Make SocketTimeoutException");
		System.in.read(); //等待制造SocketTimeoutException
		try {
		    System.out.println(jedis.get("name"));
		} catch (Exception e) {
		    e.printStackTrace();
		}
		System.out.println("Recover from SocketTimeoutException");
		Thread.sleep(50000); // 继续休眠一段时间 等待网络完全恢复
		boolean isMember = jedis.sismember("urls", "baidu");
		System.out.println("isMember " + isMember);
		jedis.close();
	}
	
	public void test6shardpipelined() {
		List<JedisShardInfo> shards = Arrays.asList(
				new JedisShardInfo("localhost",6379),
				new JedisShardInfo("localhost",6380));
				
		ShardedJedis sharding = new ShardedJedis(shards);
		
		ShardedJedisPipeline pipeline = sharding.pipelined();
		long start = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
		    pipeline.set("sp" + i, "p" + i);
		}
		List<Object> results = pipeline.syncAndReturnAll();
		long end = System.currentTimeMillis();
		System.out.println("Pipelined@Sharing SET: " + ((end - start)/1000.0) + " seconds");
		
		sharding.close();
	}
	
	public void test7shardSimplePool() {
		List<JedisShardInfo> shards = Arrays.asList(
				new JedisShardInfo("localhost",6379),
				new JedisShardInfo("localhost",6380));

		ShardedJedisPool pool = new ShardedJedisPool(new JedisPoolConfig(), shards);

		ShardedJedis one = pool.getResource();
		
		long start = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
		    one.set("spn" + i, "n" + i);
		}
		long end = System.currentTimeMillis();
		System.out.println("Simple@Pool SET: " + ((end - start)/1000.0) + " seconds");
		
		pool.close();
	}
	
	public void test5shardNormal() {
		List<JedisShardInfo> shards = Arrays.asList(
				new JedisShardInfo("localhost",6379),
				new JedisShardInfo("localhost",6380));
				
		ShardedJedis sharding = new ShardedJedis(shards);
		
		long start = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
		    sharding.set("sn" + i, "n" + i);
		}
		long end = System.currentTimeMillis();
		System.out.println("Simple@Sharing SET: " + ((end - start)/1000.0) + " seconds");
		
		sharding.close();
	}
	
	public void transaction(){  
        String key = "transaction-key";  
        jedis.set(key, "20");  
        jedis.watch(key);  
        Transaction tx = jedis.multi();  
        tx.incr(key);  
        tx.incr(key);  
        tx.incr(key);  
        List<Object> result = tx.exec();  
        if(result == null || result.isEmpty()){  
            System.out.println("Transaction error...");//可能是watch-key被外部修改，或者是数据操作被驳回  
            return;  
        }  
        for(Object rt : result){  
            System.out.println(rt.toString());  
        }  
    }
	
	@Test
	public void pipeline(){  
        String key = "pipeline-test";  
        String old = jedis.get(key);  
        if(old != null){  
            System.out.println("Key:" + key + ",old value:" + old);  
        }  
        //代码模式1,这种模式是最常见的方式  
        Pipeline p1 = jedis.pipelined();  
        p1.incr(key);  
        System.out.println("Request incr");  
        p1.incr(key);  
        System.out.println("Request incr");  
        //结束pipeline，并开始从相应中获得数据  
        List<Object> responses = p1.syncAndReturnAll();  
        if(responses == null || responses.isEmpty()){  
            throw new RuntimeException("Pipeline error: no response...");  
        }  
        for(Object resp : responses){  
            System.out.println("Response:" + resp.toString());//注意，此处resp的类型为Long  
        }
        
        //代码模式2  
        Pipeline p2 = jedis.pipelined();  
        Response<Long> r1 = p2.incr(key);  
        try{  
            r1.get();  
        }catch(Exception e){  
            System.out.println("Error,you cant get() before sync,because IO of response hasn't begin..");  
        }  
        Response<Long> r2 = p2.incr(key);  
        p2.sync();  
        System.out.println("Pipeline,mode 2,--->" + r1.get());  
        System.out.println("Pipeline,mode 2,--->" + r2.get());  
          
    }  
	
}
