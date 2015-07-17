package com.demo.zookeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.jute.Record;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.server.DataTree;
import org.apache.zookeeper.server.FinalRequestProcessor;
import org.apache.zookeeper.server.ServerCnxn;
import org.apache.zookeeper.server.WatchManager;
import org.apache.zookeeper.server.auth.AuthenticationProvider;
import org.apache.zookeeper.server.auth.ProviderRegistry;
import org.apache.zookeeper.server.quorum.FastLeaderElection;
import org.apache.zookeeper.server.quorum.QuorumPeerMain;

public class ZKNodeCRUD implements Watcher{

	private CountDownLatch connectedSemaphone=new CountDownLatch(1);
	
	private ZooKeeper zooKeeper;
	
	ServerCnxn serverCnxn;
	WatchManager watchManager;
	DataTree dataTree;
	FinalRequestProcessor finalRequestProcessor;
	AuthenticationProvider authenticationProvider;
	ProviderRegistry providerRegistry;
	Record record;
	QuorumPeerMain quorumPeerMain;
	FastLeaderElection fastLeaderElection;
	
	private void init() throws IOException{
		zooKeeper=new ZooKeeper("192.168.126.130:2181",5000,this);
		System.out.println(zooKeeper.getState());
		try {
			connectedSemaphone.await();
		} catch (Exception e) {}
		System.out.println("ZooKeeper session established:"+zooKeeper.getState());
	}

	public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
		ZKNodeCRUD zkNodeCRUD=new ZKNodeCRUD();
		zkNodeCRUD.init();
		
		String path1=zkNodeCRUD.createNode("/test","test",CreateMode.PERSISTENT);
		System.out.println("success create znode:"+path1);
		
		String path2=zkNodeCRUD.createNode("/test/n","testn",CreateMode.PERSISTENT_SEQUENTIAL);
		System.out.println("success create znode:"+path2);
	}

	@Override
	public void process(WatchedEvent event) {
		System.out.println("my ZKCreate watcher Receive watched event:"+event);
		if(KeeperState.SyncConnected==event.getState()){
			connectedSemaphone.countDown();
		}
	}
	
	public String createNode(String path,String content,CreateMode createMode) throws KeeperException, InterruptedException{
		return zooKeeper.create(path,content.getBytes(),Ids.OPEN_ACL_UNSAFE,createMode);
	}
	
	public void getChildren(String path) throws KeeperException, InterruptedException{
		Stat stat=new Stat();
		zooKeeper.getChildren(path,new Watcher() {
			
			@Override
			public void process(WatchedEvent event) {
				System.out.println(event);
			}
		}, stat);
		System.out.println(stat);
		
	}
}
