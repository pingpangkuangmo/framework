package com.demo.kerberos;

import javax.security.sasl.SaslClient;
import javax.security.sasl.SaslServer;

import org.apache.hadoop.hive.metastore.HiveMetaStore;
import org.apache.hadoop.hive.metastore.ObjectStore;
import org.apache.hadoop.hive.ql.metadata.SessionHiveMetaStoreClient;
import org.apache.hadoop.ipc.RPC.Server;
import org.apache.hadoop.security.UserGroupInformation;

public class Main {

	//Krb5LoginModule krb5LoginModule;
	UserGroupInformation ugi;
	Server server;
	
	SaslServer saslServer;
	SaslClient saslClient;
	
	ObjectStore asfsd;
	
	SessionHiveMetaStoreClient sessionHiveMetaStoreClient;
	
	HiveMetaStore hiveMetaStore;
	
}
