package com.demo.hbase;

import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.hbase.Coprocessor;
import org.apache.hadoop.hbase.client.BufferedMutator;
import org.apache.hadoop.hbase.client.Consistency;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.coprocessor.BaseRegionObserver;
import org.apache.hadoop.hbase.coprocessor.CoprocessorService;
import org.apache.hadoop.hbase.coprocessor.MultiRowMutationEndpoint;
import org.apache.hadoop.hbase.mapreduce.TableInputFormat;
import org.apache.hadoop.hbase.mapreduce.TableInputFormatBase;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.mapreduce.TableOutputFormat;
import org.apache.hadoop.hbase.master.HMaster;
import org.apache.hadoop.hbase.master.balancer.StochasticLoadBalancer;
import org.apache.hadoop.hbase.regionserver.CellSkipListSet;
import org.apache.hadoop.hbase.regionserver.CompactSplitThread;
import org.apache.hadoop.hbase.regionserver.FlushRequester;
import org.apache.hadoop.hbase.regionserver.HRegion;
import org.apache.hadoop.hbase.regionserver.HRegionServer;
import org.apache.hadoop.hbase.regionserver.MemStore;
import org.apache.hadoop.hbase.regionserver.RSRpcServices;
import org.apache.hadoop.hbase.regionserver.Store;
import org.apache.hadoop.hbase.regionserver.StoreFileScanner;
import org.apache.hadoop.hbase.regionserver.wal.FSHLog;
import org.apache.hadoop.ipc.VersionedProtocol;
import org.apache.hadoop.mapred.InputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.RunningJob;
import org.apache.hadoop.mapreduce.Cluster;
import org.apache.hadoop.mapreduce.OutputFormat;
import org.apache.hadoop.yarn.client.RMFailoverProxyProvider;

public class DoubleHbaseDemo {
	
	InputFormat<String, String> in;
	TableInputFormatBase base;
	TableInputFormat habseTable;
	TableMapper tableMapper;
	TableOutputFormat tableOutputFormat;
	HRegionServer hRegionServer;
	HRegion hRegion;
	MultiRowMutationEndpoint multiRowMutationEndpoint;
	OutputFormat<String, String> out;
	FileSystem fs;
	Cluster cluster;
	RMFailoverProxyProvider rmFailoverProxyProvider;
	JobClient jobClient;
	RunningJob runningJob;
	org.apache.hadoop.hbase.client.NoServerForRegionException ex;
	HTable hTable;
	Table table;
	BufferedMutator bufferedMutator;
	VersionedProtocol versionedProtocol;
	FSHLog fshLog;
	MemStore memStore;
	Store store;
	ConcurrentSkipListSet<String> concurrentSkipListSet;
	ConcurrentSkipListMap<String, String> concurrentSkipListMap;
	RSRpcServices rsRpcServices;
	CellSkipListSet cellSkipListSet;
	HRegionServer hRegionServer2;
	//MemStoreFlusher memStoreFlusher;
	CompactSplitThread compactSplitThread;
	StoreFileScanner storeFileScanner;
	//HFileScanner HFileScanner;
	Table tassble;
	Consistency consistency;
	StochasticLoadBalancer stochasticLoadBalancer;
	FlushRequester flushRequester;
	HMaster hmaster;
	Coprocessor coprocessor;
	BaseRegionObserver baseRegionObserver;
	CoprocessorService coprocessorService;
	
	public static void main(String[] args) throws Exception{
		
		String hYTableName="LGW-SYSLog-HY";
		Configuration oldConf=new Configuration(false);
		oldConf.addResource("core-site.xml");
		oldConf.addResource("oldhbase-site.xml");
		
		HTable oldhyTable=new HTable(oldConf, hYTableName);
		
		Configuration newConf=new Configuration(false);
		newConf.addResource("newhbase-site.xml");
		
		HTable newhyTable=new HTable(newConf, hYTableName);
		
		oldhyTable.close();
		newhyTable.close();
	}
}
