package com.demo.hbase;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.replication.master.ReplicationLogCleaner;

public class Main {

	//TableInputFormat in;
	/*HFile hFile;
	HLog hLog;
	HRegion hRegion;
	HLogKey hLogKey;
	WALEdit wALEdit;
	LogSyncer logSyncer;
	LogRoller logRoller;
	Store hStore;*/
	HTable hTable;
	TableName tableName;
	/*AsyncProcess asyncProcess;
	RpcClient rpcClient;
	RPC rpc;
	HBaseRPC hBaseRPC;
	User user;*/
	
	Future<?> f;
	FutureTask<?> futureTask;
	Object obj;
	
	ConcurrentHashMap<String, String> sds;
	ConcurrentLinkedDeque<String> sdfdfd;
	ArrayBlockingQueue<String> afsdfdsf;
	LinkedBlockingQueue<String> afcsdfd;
	ReplicationLogCleaner replicationLogCleaner;
}
