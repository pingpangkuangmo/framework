package com.demo.hive;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.apache.hadoop.hive.metastore.HiveMetaStore;
import org.apache.hadoop.hive.ql.parse.AbstractSemanticAnalyzerHook;
import org.apache.hadoop.hive.ql.parse.HiveSemanticAnalyzerHook;
import org.apache.hadoop.hive.ql.parse.authorization.HiveAuthorizationTaskFactoryImpl;
import org.apache.hive.service.server.HiveServer2;

public class Main {

	HiveMetaStore hiveMetaStore;

	HiveServer2 hiveServer2;

	HiveAuthorizationTaskFactoryImpl hiveAuthorizationTaskFactory;
	AbstractSemanticAnalyzerHook hook;
	HiveSemanticAnalyzerHook hiveSemanticAnalyzerHook;

	public static void main(String[] args) throws UnsupportedEncodingException{

	}
}
                                                                                                        