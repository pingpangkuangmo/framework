package com.demo.hive;

import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.metastore.HiveMetaStoreClient;
import org.apache.hadoop.hive.metastore.api.Database;
import org.apache.hadoop.hive.metastore.api.MetaException;
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
	public void createDb() throws Exception{
		Database database = new Database();
		database.setName("lgtest1");
		database.setOwnerName("lg123");
		metaStoreClient.createDatabase(database);
	}

}
