package com.demo;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.security.UserGroupInformation;

import java.io.IOException;
import java.security.PrivilegedAction;

/**
 * @author lg
 *         Date: 5/24/17
 *         Time: 4:43 PM
 */
public class HdfsTest {

    private static FileSystem fs;

    public static void main(String[] args) throws IOException {
        UserGroupInformation ugi = UserGroupInformation.createUserForTesting("master", new String[]{"hadoop"});
        ugi.doAs(new PrivilegedAction<Object>() {

            public Object run() {
                try {
                    Configuration conf=new Configuration(false);
                    conf.addResource("hdfs-site.xml");
                    fs = FileSystem.get(conf);
                    writeData();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        });
    }

    public static void listFiles(String dir) throws IOException, InterruptedException {
        if (!dir.startsWith("hdfs://mycluster")) {
            dir = "hdfs://mycluster" + dir;
        }
        FileStatus[] files = fs.listStatus(new Path(dir));
        if (files != null && files.length > 0) {
            for (FileStatus item : files) {
                if (item.isDirectory()) {
                    listFiles(dir + "/" + item.getPath().getName());
                }else{
                    System.out.println(item.getPath());
                }
                Thread.sleep(1000);
            }
        }
    }

    public static void writeData() throws IOException, InterruptedException {
        Path path = new Path("hdfs://mycluster/test");
        fs.delete(path, true);
        fs.mkdirs(path);
        FSDataOutputStream out = fs.create(new Path("hdfs://mycluster/test/0.txt"));
        long count = 0;
        while(count < 1000){
            System.out.println(count);
            out.writeBytes("count:" + count);
            count++;
            if (count % 10 == 0) {
                out.close();
                out = fs.create(new Path("hdfs://mycluster/test/" + (count/10) + ".txt"));
            }
            Thread.sleep(1000);
        }
    }
}
