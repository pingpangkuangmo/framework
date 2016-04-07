package com.demo.performance.oozie;

import org.apache.oozie.client.OozieClientException;
import org.apache.oozie.client.WorkflowJob;

import com.demo.performance.BaseCallable;
import com.demo.performance.Result;
import com.demo.performance.Result.Status;

public class OozieAllCallable extends BaseCallable{
	
    private String jobId = null;
    
	@Override
	public Result call() throws Exception {
		setStatus(Status.PREP);
		try {
			jobId = OozieUtils.runjob();
		} catch (Exception e) {
			e.printStackTrace();
			setStatus(Status.FAILED);
		}
		return getBaseResult();
	}

	@Override
	public void updateBaseResult() {
		if(jobId != null && !getBaseResult().isDone()){
			WorkflowJob.Status status = null;
			try {
				status = OozieUtils.getJobStatus(jobId);
			} catch (OozieClientException e) {
				e.printStackTrace();
			}
			switch (status) {
				case PREP: setStatus(Status.PREP); break;
				case RUNNING: setStatus(Status.RUNNING); break;
				case SUCCEEDED: setStatus(Status.SUCCEEDED); break;
				case FAILED: setStatus(Status.FAILED); break;
				case KILLED: setStatus(Status.KILLED); break;
				case SUSPENDED: setStatus(Status.SUSPENDED); break;
				default:
					break;
			}
		}
	}

}
