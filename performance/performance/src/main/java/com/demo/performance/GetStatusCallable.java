package com.demo.performance;

public class GetStatusCallable implements Runnable{

	private BaseCallable baseCallable;
	
	public GetStatusCallable(BaseCallable baseCallable){
		this.baseCallable = baseCallable;
	}
	
	@Override
	public void run(){
		baseCallable.updateBaseResult();
	}

}