package com.demo.performance;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class WorkerManager {

	private ExecutorService executorService;
	
	private SampleManager sampleManager = new SampleManager();
	
	private AtomicLong successedTaskNum = new AtomicLong(0);
	private AtomicLong failedTaskNum = new AtomicLong(0);
	private AtomicLong totalFinished = new AtomicLong(0);
	
	private ConcurrentHashMap<Integer,Future<? extends Result>> futures = new ConcurrentHashMap<Integer,Future<? extends Result>>();
	
	private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
	
	public WorkerManager(int executorThreads){
		executorService = Executors.newFixedThreadPool(executorThreads);
	}
	
	public void initSample(){
		sampleManager.addGetDataCallback(new GetLongCallback("successedTaskNum", successedTaskNum));
		sampleManager.addGetDataCallback(new GetLongCallback("failedTaskNum", failedTaskNum));
		sampleManager.addGetDataCallback(new GetLongCallback("totalFinished", totalFinished));
		sampleManager.start();
		scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
			
			@Override
			public void run() {
				scanResult();
			}
		}, 0, 1, TimeUnit.SECONDS);
	}
	
	protected void scanResult() {
		List<Integer> indexsToRemove = new ArrayList<Integer>();
		for(Integer index : futures.keySet()){
			Future<? extends Result> f = futures.get(index);
			if(f.isDone()){
				indexsToRemove.add(index);
				try {
					Result baseResult = f.get();
					if(baseResult.isSuccessed()){
						successedTaskNum.incrementAndGet();
					}else{
						failedTaskNum.incrementAndGet();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
					failedTaskNum.incrementAndGet();
				} catch (ExecutionException e) {
					e.printStackTrace();
					failedTaskNum.incrementAndGet();
				}
				totalFinished.incrementAndGet();
			}
		}
		for(Integer index : indexsToRemove){
			futures.remove(index);
		}
	}

	public void start(List<? extends BaseCallable> tasks){
		if(tasks == null){
			throw new RuntimeException("the list of tasks can not be null");
		}
		initSample();
		int tasksSize = tasks.size();
		for(int i = 0; i < tasksSize; i++){
			try {
				futures.put(i, executorService.submit(tasks.get(i)));
			} catch (Exception e) {
				e.printStackTrace();	
			}
		}
		while(true){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(totalFinished.get()==tasksSize){
				break;
			}
		}
		stop();
	}
	
	public void stop(){
		executorService.shutdown();
		scheduledExecutorService.shutdown();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		sampleManager.stop();
	}
	
	
}
