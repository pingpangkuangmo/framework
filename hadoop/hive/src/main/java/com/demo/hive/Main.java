package com.demo.hive;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.apache.hadoop.hive.metastore.HiveMetaStore;

public class Main {

	HiveMetaStore hiveMetaStore;
	
	                    
	public static void main(String[] args) throws UnsupportedEncodingException{
		System.out.println(URLDecoder.decode("hdfs://ns%2Fuser%2Fadmin%2FSearchAge", "UTF-8"));
	}
}
                                                                                                        