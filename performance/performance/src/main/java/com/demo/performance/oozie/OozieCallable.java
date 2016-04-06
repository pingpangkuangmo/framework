package com.demo.performance.oozie;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.oozie.client.OozieClient;

import com.demo.performance.BaseCallable;
import com.demo.performance.BaseResult;
import com.demo.performance.Result;

public class OozieCallable implements BaseCallable{
	
	private static String OOZIE_URL = "http://10.142.78.40:11000/oozie";
    private static String JOB_PATH = "hdfs://ns/user/hue/oozie/workspaces/lg-oozie-00001";
    private static String JOB_Tracker = "10.142.78.36:8032";
    private static String NAMENode = "hdfs://ns";

	@Override
	public Result call() throws Exception {
		boolean successed = false;
		try {
			OozieClient wc = new OozieClient(OOZIE_URL);
			
			Map<String,String> params = new HashMap<String,String>();
			params.put("businessDate", "201604051754");
			
			System.setProperty("user.name","admin");
			Properties conf = wc.createConfiguration();
			conf.setProperty(OozieClient.APP_PATH, JOB_PATH);
			conf.setProperty("jobTracker", JOB_Tracker);
			conf.setProperty("nameNode", NAMENode);
			if((params != null) && (params.size() > 0)){
			    for(String parameter : params.keySet())
			        conf.setProperty(parameter, params.get(parameter));
			}

			String jobId = wc.run(conf);
			if(jobId != null){
				successed = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new BaseResult(successed);
	}

}
