package com.demo.hive;

import java.io.UnsupportedEncodingException;

import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.metastore.HiveMetaStore;
import org.apache.hadoop.hive.metastore.HiveMetaStore.HMSHandler;
import org.apache.hadoop.hive.metastore.MetaStorePreEventListener;
import org.apache.hadoop.hive.metastore.ObjectStore;
import org.apache.hadoop.hive.ql.Driver;
import org.apache.hadoop.hive.ql.hooks.PostExecute;
import org.apache.hadoop.hive.ql.parse.authorization.HiveAuthorizationTaskFactory;
import org.apache.hadoop.hive.ql.parse.authorization.HiveAuthorizationTaskFactoryImpl;
import org.apache.hive.service.cli.session.HiveSessionHook;
import org.apache.hive.service.server.HiveServer2;

public class Main {

	HiveMetaStore hiveMetaStore;

	HiveAuthorizationTaskFactory hiveAuthorizationTaskFactory;

	Driver driver;
	PostExecute postExecute;
	
	HiveConf hiveConf;
	
	HiveSessionHook hiveSessionHook;
	
	HMSHandler hmsHandler;
	
	MetaStorePreEventListener metaStorePreEventListener;
	
	HiveServer2 hiveServer2;
	
	HiveAuthorizationTaskFactoryImpl hiveAuthorizationTaskFactoryImpl;
	
	ObjectStore objectStore;
	public static void main(String[] args) throws UnsupportedEncodingException{

	}
}
                                                                                                        