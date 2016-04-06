package com.demo.performance;


public class BaseResult implements Result{

	private boolean successed = false;

	public BaseResult(boolean successed) {
		this.successed = successed;
	}
	
	@Override
	public boolean isSuccessed() {
		return successed;
	}

	
}
