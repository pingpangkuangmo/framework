package com.demo.performance;

import java.util.concurrent.atomic.AtomicLong;

public class GetRateCallback implements GetDataCallback<Double>{

	private MyReference<AtomicLong> reference;;
	private String name;
	
	public GetRateCallback(String name, MyReference<AtomicLong> reference) {
		this.name = name;
		this.reference = reference;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public Double get(Long index) {
		Double d = Double.parseDouble(reference.get().get()+"");
		return d/index;
	}

}
