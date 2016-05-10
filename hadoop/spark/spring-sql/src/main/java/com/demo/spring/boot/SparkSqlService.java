package com.demo.spring.boot;

import java.io.Serializable;
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

import scala.collection.JavaConversions;

public class SparkSqlService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public void init(){
		SparkConf conf = new SparkConf();
    	SparkContext sc = new SparkContext(conf);
    	SparkSqlUtil.hiveContext = new HiveContext(sc);
	}

	public List<Map<String, Object>> getData(String sql){
		DataFrame df = SparkSqlUtil.hiveContext.sql(sql);
		
		StructType st = df.schema();
		
		final List<StructField> sfs = JavaConversions.asJavaList(st.toList());
		df.show();
		
		List<Map<String, Object>> javaRdd = df.toJavaRDD().map(new Function<Row, Map<String, Object>>(){

			private static final long serialVersionUID = 1L;

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
	
}
