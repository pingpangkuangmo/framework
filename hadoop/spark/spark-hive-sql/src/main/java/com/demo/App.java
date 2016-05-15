package com.demo;

import org.apache.spark.sql.hive.HiveContext;

/**
 * Hello world!
 *
 */
public class App {

    org.apache.spark.sql.hive.thriftserver.SparkSQLCLIDriver d;
    org.apache.spark.sql.hive.thriftserver.HiveThriftServer2 hiveThriftServer2;

    static HiveContext hiveContext;

    public static void main( String[] args ) {
        System.out.println( "Hello World!" );
        hiveContext.sql("");
        hiveContext.table("");
    }
}
