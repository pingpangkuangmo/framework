package com.demo.performance.demo;

import java.util.Random;

import com.demo.performance.BaseCallable;
import com.demo.performance.Result;
import com.demo.performance.BaseResult;

public class DemoCallable implements BaseCallable{

	@Override
	public Result call() throws Exception {
		Thread.sleep(300);
		Random random = new Random();
		boolean successed = random.nextInt() % 2 == 1 ? false:true;
		return new BaseResult(successed);
	}

}
