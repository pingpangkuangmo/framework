package com.demo;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception {
        System.out.println( "Hello World!" );
        CuratorFramework cf = CuratorFrameworkFactory.newClient("192.168.112.78:2181,192.168.112.79:2181,192.168.112.81:2181",
                new RetryNTimes(4, 1000));
        //cf.start();
        cf.create().creatingParentContainersIfNeeded().withProtection().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath("/etrace/test/lock-11");
//        LeaderSelector leaderSelector = new LeaderSelector(cf, "/etrace/test",
//                new LeaderSelectorListenerAdapter() {
//                    public void takeLeadership(CuratorFramework client) throws Exception {
//                        System.out.println("I am leader");
//                    }
//                });
//        leaderSelector.autoRequeue();
//        leaderSelector.start();
        Thread.sleep(1000*60*10);
    }
}
