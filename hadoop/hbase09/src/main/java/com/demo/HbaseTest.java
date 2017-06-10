package com.demo;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.RetriesExhaustedWithDetailsException;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lg
 *         Date: 5/24/17
 *         Time: 4:20 PM
 */
public class HbaseTest {


    public static void main(String[] args) throws IOException {
        Configuration conf=new Configuration(false);
        conf.addResource("hdfs-site.xml");
        conf.addResource("hbase-site.xml");
        HTable oldhyTable=new HTable(conf, "test");

        Long count = 0L;
        while (true) {
            try {
                count++;
                System.out.println(count);
                byte[] row1= Bytes.toBytes("row" + count);
                byte[] family=Bytes.toBytes("id");
                byte[] qualifier=Bytes.toBytes("age");
                Put put=new Put(row1);
                put.add(family, qualifier, Bytes.toBytes(count));


                oldhyTable.put(put);
                oldhyTable.flushCommits();

                Thread.sleep(3000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
