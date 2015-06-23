package com.demo.zookeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;

public class ZKCreate implements Watcher{

	private static CountDownLatch connectedSemaphone=new CountDownLatch(1);

	public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
		ZooKeeper zooKeeper=new ZooKeeper("192.168.126.130:2181",5000,new ZKCreate());
		System.out.println(zooKeeper.getState());
		try {
			connectedSemaphone.await();
		} catch (Exception e) {}
		System.out.println("ZooKeeper session established:"+zooKeeper.getState());
		
		String path1=zooKeeper.create("/test/d","dContent".getBytes(),Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
		System.out.println("success create znode:"+path1);
		
		String path2=zooKeeper.create("/test/b","bContent".getBytes(),Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT_SEQUENTIAL);
		System.out.println("success create znode:"+path2);
	}

	@Override
	public void process(WatchedEvent event) {
		System.out.println("my ZKCreate watcher Receive watched event:"+event);
		if(KeeperState.SyncConnected==event.getState()){
			connectedSemaphone.countDown();
		}
	}
}
