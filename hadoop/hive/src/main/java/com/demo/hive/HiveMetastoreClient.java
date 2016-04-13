package com.demo.hive;

import java.util.HashMap;

import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.metastore.HiveMetaStoreClient;
import org.apache.hadoop.hive.metastore.api.AlreadyExistsException;
import org.apache.hadoop.hive.metastore.api.Database;
import org.apache.hadoop.hive.metastore.api.InvalidObjectException;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.thrift.TException;
import org.junit.Before;
import org.junit.Test;

public class HiveMetastoreClient {
	
	HiveMetaStoreClient metaStoreClient = null;

	@Before
	public void init(){
		HiveConf conf = new HiveConf();
		try {
			metaStoreClient = new HiveMetaStoreClient(conf);
		} catch (MetaException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void createDb() throws AlreadyExistsException, InvalidObjectException, MetaException, TException{
		metaStoreClient.createDatabase(new Database("test", "created by metaStoreClient", "hdfs://user/hive/warehouse/test", new HashMap<String, String>()));
	}

}
