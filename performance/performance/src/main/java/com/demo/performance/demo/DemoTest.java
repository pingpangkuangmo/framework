package com.demo.performance.demo;

import java.util.ArrayList;
import java.util.List;

import com.demo.performance.WorkerManager;

public class DemoTest {

	public static void main(String[] args){
		WorkerManager workerManager = new WorkerManager(10);
		List<DemoCallable> callables = new ArrayList<DemoCallable>();
		for(int i=0; i< 10000; i++){
			callables.add(new DemoCallable());
		}
		workerManager.start(callables);
	}
}
