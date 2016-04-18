package com.demo.hive;

import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.metastore.HiveMetaStoreClient;
import org.apache.hadoop.hive.metastore.api.Database;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.security.UserGroupInformation.HadoopLoginModule;
import org.junit.Before;
import org.junit.Test;

import sun.util.logging.resources.logging;

public class HiveMetastoreClient {
	
	HiveMetaStoreClient metaStoreClient = null;
	
	HadoopLoginModule loginModule;
	//HadoopGroupMappingService hadoopGroupMappingService;

	@Before
	public void init(){
		HiveConf conf = new HiveConf();
		
		try {
			System.setProperty("HADOOP_USER_NAME", "admin");
			metaStoreClient = new HiveMetaStoreClient(conf);
		} catch (MetaException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void createDb() throws Exception{
		Database database = new Database();
		database.setName("test_lg");
		database.setOwnerName("lg123");
		metaStoreClient.createDatabase(database);
	}

}
