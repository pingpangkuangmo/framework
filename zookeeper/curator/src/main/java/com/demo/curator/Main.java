package com.demo.curator;

import org.apache.curator.framework.recipes.atomic.DistributedAtomicLong;
import org.apache.curator.test.TestingCluster;
import org.apache.curator.test.TestingServer;

public class Main {

	DistributedAtomicLong distributedAtomicLong;
	TestingServer testingServer;
	TestingCluster testingCluster;
}
