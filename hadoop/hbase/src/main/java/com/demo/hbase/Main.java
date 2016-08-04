package com.demo.hbase;

import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.HTable;

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
	
}
