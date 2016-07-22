package com.demo.hive;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class HiveJdbcTest {

	private static String driverName = "org.apache.hive.jdbc.HiveDriver";
	private static AtomicInteger succeedNum = new AtomicInteger();
	private static AtomicInteger failedNum = new AtomicInteger();
	
	public static void main(String[] args)throws Exception {
		int num = 500;
		ExecutorService executorService = Executors.newFixedThreadPool(num);
		Class.forName(driverName);
		//DriverManager.setLoginTimeout(600);
		
		for(int i = 0; i < num ; i++){
			//Thread.sleep(100);
			executorService.execute(new HiveJob());
		}
	}
	
	public static class HiveJob implements Runnable{

		@Override
		public void run() {
			try {
				Connection con = DriverManager.getConnection("jdbc:hive2://hadoop-yarn.dragon.org:10000", "lg", "");
				Statement stmt = con.createStatement();
				String tableName = "db1_t1";
				stmt.executeQuery("use db1");
				String sql = "select * from " + tableName;
				stmt.executeQuery(sql);
				succeedNum.incrementAndGet();
			} catch (Exception e) {
				e.printStackTrace();
				failedNum.incrementAndGet();
			}
			System.out.println("succeedNum :" + succeedNum.get() + "; failedNum :" + failedNum.get());
		}
		
	}
}
