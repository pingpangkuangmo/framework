package com.demo;

import org.apache.hive.service.cli.thrift.ThriftCLIService;
import org.apache.hadoop.hive.ql.Driver;
import org.apache.hadoop.hive.ql.lockmgr.HiveTxnManager;
import org.apache.hive.service.cli.CLIService;
import org.apache.hive.service.cli.operation.SQLOperation;
import org.apache.hive.service.cli.session.HiveSessionImpl;
import org.apache.hive.service.server.HiveServer2;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.hive.HiveContext;
import org.apache.spark.sql.hive.thriftserver.SparkExecuteStatementOperation;
import org.apache.spark.sql.hive.thriftserver.SparkSQLCLIService;
import org.apache.spark.sql.hive.thriftserver.SparkSQLSessionManager;

/**
 * Hello world!
 *
 */
public class App {

    org.apache.spark.sql.hive.thriftserver.SparkSQLCLIDriver d;
    org.apache.spark.sql.hive.thriftserver.HiveThriftServer2 hiveThriftServer2;

    ThriftCLIService thriftCLIService;
    HiveServer2 hiveServer2;

    SQLOperation sqlOperation;
    Driver driver;

    CLIService cliService;
    SparkSQLCLIService sparkSQLCLIService;

    SparkSQLSessionManager sparkSQLSessionManager;
    SparkExecuteStatementOperation sparkExecuteStatementOperation;

    HiveSessionImpl hiveSession;

    static HiveContext hiveContext;
    SQLContext sqlContext;

    HiveTxnManager hiveTxnManager;


    public static void main( String[] args ) {
        System.out.println( "Hello World!" );
        hiveContext.sql("");
        hiveContext.table("");
    }
}
