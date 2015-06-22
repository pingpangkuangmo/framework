package com.demo.zookeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.Watcher.Event.KeeperState;

public class ZookeeperConstructorSimple implements Watcher{
	
	private static CountDownLatch connectedSemaphone=new CountDownLatch(1);

	public static void main(String[] args) throws IOException {
		ZooKeeper zooKeeper=new ZooKeeper("192.168.126.130:2181",5000,new ZookeeperConstructorSimple());
		System.out.println(zooKeeper.getState());
		try {
			connectedSemaphone.await();
		} catch (Exception e) {}
		System.out.println("ZooKeeper session established");
	}

	@Override
	public void process(WatchedEvent event) {
		System.out.println("Receive watched event:"+event);
		if(KeeperState.SyncConnected==event.getState()){
			connectedSemaphone.countDown();
		}
	}

}
