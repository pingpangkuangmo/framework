package com.demo.performance.oozie;

import com.demo.performance.BaseCallable;
import com.demo.performance.Result;
import com.demo.performance.Result.Status;

public class OozieSubmitCallable extends BaseCallable{
	
    private String jobId = null;
    
	@Override
	public Result call() throws Exception {
		setStatus(Status.RUNNING);
		try {
			jobId = OozieUtils.runjob();
			if(jobId != null){
				setStatus(Status.SUCCEEDED);
			}
		} catch (Exception e) {
			e.printStackTrace();
			setStatus(Status.FAILED);
		}
		return getBaseResult();
	}

}
