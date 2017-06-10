package com.demo;

import org.apache.hadoop.hbase.util.FSHDFSUtils;
import org.apache.hadoop.hdfs.DFSConfigKeys;
import org.apache.hadoop.hdfs.server.namenode.NameNode;

/**
 * Hello world!
 *
 */
public class App 
{

    FSHDFSUtils fshdfsUtils;
    NameNode nameNode;
    DFSConfigKeys dfsConfigKeys;

    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
}
