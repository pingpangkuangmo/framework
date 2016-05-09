package com.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.hive.HiveContext;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import scala.collection.JavaConversions;

@Service
public class SparkSqlService implements InitializingBean{
	
	private HiveContext hiveContext;

	public Object sql(String sql){
		DataFrame df = hiveContext.sql(sql);
		
		StructType st = df.schema();
		
		final List<StructField> sfs = JavaConversions.asJavaList(st.toList());
		
		@SuppressWarnings("serial")
		List<Map<String, Object>> javaRdd = df.toJavaRDD().map(new Function<Row, Map<String, Object>>(){

			@Override
			public Map<String, Object> call(Row v1) throws Exception {
				Map<String, Object> map = new HashMap<String, Object>();
				for(StructField sf : sfs){
					String name = sf.name();
					map.put(name, v1.getAs(name));
				}
				return map;
			}
			
		}).collect();
		return javaRdd;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		SparkConf conf = new SparkConf();
		SparkContext sc = new SparkContext(conf);
		hiveContext = new HiveContext(sc);
	}
}
