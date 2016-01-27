package com.demo.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.HTable;

public class DoubleHbaseDemo {

	public static void main(String[] args) throws Exception{
		String hYTableName="LGW-SYSLog-HY";
		Configuration oldConf=new Configuration(false);
		oldConf.addResource("core-site.xml");
		oldConf.addResource("oldhbase-site.xml");
		
		HTable oldhyTable=new HTable(oldConf, hYTableName);
		
		Configuration newConf=new Configuration(false);
		newConf.addResource("newhbase-site.xml");
		
		HTable newhyTable=new HTable(newConf, hYTableName);
		
		oldhyTable.close();
		newhyTable.close();
	}
}
