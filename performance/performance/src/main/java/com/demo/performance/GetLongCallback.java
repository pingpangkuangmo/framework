package com.demo.performance;

import java.util.concurrent.atomic.AtomicLong;

public class GetLongCallback implements GetDataCallback<Long>{

	private AtomicLong atomicLong;
	private String name;
	
	public GetLongCallback(String name, AtomicLong atomicLong) {
		this.atomicLong = atomicLong;
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}
	@Override
	public Long get() {
		return atomicLong.get();
	}
}
