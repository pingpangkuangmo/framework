package com.demo.performance.oozie;

import java.util.ArrayList;
import java.util.List;

import com.demo.performance.WorkerManager;

public class OozieTest {

	public static void main(String[] args){
		WorkerManager workerManager = new WorkerManager(100);
		List<OozieCallable> callables = new ArrayList<OozieCallable>();
		for(int i=0; i< 500; i++){
			callables.add(new OozieCallable());
		}
		workerManager.start(callables);
	}
}
