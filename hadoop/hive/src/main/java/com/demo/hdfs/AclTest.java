package com.demo.hdfs;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AclTest {
	
	private static Logger logger = LoggerFactory.getLogger(AclTest.class);
	
	private static DistributedFileSystem dfs = null;

	@Before
	public void init(){
		if (dfs == null) {
        	System.setProperty("HADOOP_USER_NAME", "admin");
            Configuration conf = new Configuration();
            conf.addResource("hdfs-site.xml");
            try {
                FileSystem fs = FileSystem.get(conf);

                if (!(fs instanceof DistributedFileSystem)) {
                    throw new IOException("The file system is not a DistributedFileSystem");
                }

                AclTest.dfs = (DistributedFileSystem) fs;
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        }
	}
	
	@Test
	public void testListFiles(){
		listFiles("hdfs://ns/user/op/lg/aa");
	}
	
	private void listAcls(String path){
		Path pathObj = new Path(path);
		try {
			dfs.getAclStatus(pathObj);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void listFiles(String path){
		Path pathObj = new Path(path);
		FileStatus[] fileStatus;
		try {
			fileStatus = dfs.listStatus(pathObj);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		if(fileStatus.length == 0){
			System.out.println("没有文件");
		}else{
			for(FileStatus item : fileStatus){
				System.out.println(item.getPath().getName());
			}
		}
	}
}
