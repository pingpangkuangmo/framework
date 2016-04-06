package com.demo.performance.demo;

import java.util.Random;

import com.demo.performance.BaseCallable;
import com.demo.performance.Result;
import com.demo.performance.BaseResult;
import com.demo.performance.Result.Status;

public class DemoCallable implements BaseCallable{
	
	private BaseResult baseResult = new BaseResult();

	@Override
	public Result call() throws Exception {
		baseResult.setStatus(Status.RUNNING);
		Thread.sleep(300);
		Random random = new Random();
		boolean successed = random.nextInt() % 2 == 1 ? false:true;
		if(successed){
			baseResult.setStatus(Status.SUCCEEDED);
		}else{
			baseResult.setStatus(Status.FAILED);
		}
		return baseResult;
	}

	@Override
	public BaseResult getBaseResult() {
		return baseResult;
	}

}
