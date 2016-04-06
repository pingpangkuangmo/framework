package com.demo.performance.oozie;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.oozie.client.OozieClient;
import org.apache.oozie.client.WorkflowJob;

import com.demo.performance.BaseCallable;
import com.demo.performance.BaseResult;
import com.demo.performance.Result;
import com.demo.performance.Result.Status;
import com.demo.performance.util.TimeUtils;

public class OozieCallable implements BaseCallable{
	
	private static String OOZIE_URL = "http://10.142.78.40:11000/oozie";
    private static String JOB_PATH = "hdfs://ns/user/hue/oozie/workspaces/lg-oozie-00001";
    private static String JOB_Tracker = "10.142.78.36:8032";
    private static String NAMENode = "hdfs://ns";
    
    private BaseResult baseResult = new BaseResult();

	@Override
	public Result call() throws Exception {
		baseResult.setStatus(Status.PREP);
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
				boolean done = false;
				while(!done){
					TimeUtils.sleep(100);
					WorkflowJob job = wc.getJobInfo(jobId);
					switch (job.getStatus()) {
						case PREP: baseResult.setStatus(Status.PREP); break;
						case RUNNING: baseResult.setStatus(Status.RUNNING); break;
						case SUCCEEDED: baseResult.setStatus(Status.SUCCEEDED); done = true; break;
						case FAILED: baseResult.setStatus(Status.FAILED); done = true; break;
						case KILLED: baseResult.setStatus(Status.KILLED); done = true; break;
						case SUSPENDED: baseResult.setStatus(Status.SUSPENDED); break;
						default:
							break;
					}
				}
			}else{
				baseResult.setStatus(Status.FAILED);
			}
		} catch (Exception e) {
			e.printStackTrace();
			baseResult.setStatus(Status.FAILED);
		}
		return baseResult;
	}

	@Override
	public BaseResult getBaseResult() {
		return baseResult;
	}

}
