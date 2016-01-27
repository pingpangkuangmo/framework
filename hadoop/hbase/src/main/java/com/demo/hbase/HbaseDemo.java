package com.demo.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;

public class HbaseDemo {

	public static void main(String[] args) throws Exception{
		Configuration config1=HBaseConfiguration.create();
		
		Configuration config = new Configuration();
		config.addResource("hbase-site.xml");
		
		HBaseAdmin admin=new HBaseAdmin(config);
		
		String tableName="test";
		HTableDescriptor htd=new HTableDescriptor(tableName);
		HColumnDescriptor hcd=new HColumnDescriptor("data");
		htd.addFamily(hcd);
		
		if(!admin.tableExists(tableName)){
			admin.createTable(htd);
		}
		
		HTableDescriptor[] tableNames=admin.listTables();
		System.out.println("list hbase tables:");
		for(HTableDescriptor item:tableNames){
			System.out.println(item.getNameAsString());
		}
		
		HTable table=new HTable(config,tableName);
		
		byte[] row1=Bytes.toBytes("row3");
		Put put=new Put(row1);
		byte[] dataBytes=Bytes.toBytes("data");
		put.add(dataBytes,Bytes.toBytes("1"),Bytes.toBytes("value1"));
		table.put(put);
		
		Get get=new Get(row1);
		Result result=table.get(get);
		System.out.println("Get: "+result);
		
		Scan scan=new Scan();
		ResultScanner resultScanner=table.getScanner(scan);
		for(Result item:resultScanner){
			System.out.println("Scan: "+item);
		}
		resultScanner.close();
		
		//admin.disableTable("test");
		//admin.deleteTable("test");
	}
}
