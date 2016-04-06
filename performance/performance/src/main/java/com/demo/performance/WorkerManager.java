package com.demo.performance;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import com.demo.performance.Result.Status;

public class WorkerManager {

	private ExecutorService executorService;
	
	private SampleManager sampleManager = new SampleManager();
	
	private AtomicLong prepNum = new AtomicLong(0);
	private AtomicLong runningNum = new AtomicLong(0);
	private AtomicLong succeededNum = new AtomicLong(0);
	private AtomicLong failedNum = new AtomicLong(0);
	private AtomicLong killedNum = new AtomicLong(0);
	private AtomicLong suspendedNum = new AtomicLong(0);
	private AtomicLong totalNum = new AtomicLong(0);
	
	private List<BaseCallable> callables;
	
	private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
	
	public WorkerManager(int executorThreads){
		executorService = Executors.newFixedThreadPool(executorThreads);
	}
	
	public void initSample(){
		sampleManager.addGetDataCallback(new GetLongCallback(Status.PREP.name(), prepNum));
		sampleManager.addGetDataCallback(new GetLongCallback(Status.RUNNING.name(), runningNum));
		sampleManager.addGetDataCallback(new GetLongCallback(Status.SUCCEEDED.name(), succeededNum));
		sampleManager.addGetDataCallback(new GetLongCallback(Status.FAILED.name(), failedNum));
		sampleManager.addGetDataCallback(new GetLongCallback(Status.KILLED.name(), killedNum));
		sampleManager.addGetDataCallback(new GetLongCallback(Status.SUSPENDED.name(), suspendedNum));
		sampleManager.addGetDataCallback(new GetLongCallback("TOTAL", totalNum));
		sampleManager.start();
		scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
			
			@Override
			public void run() {
				scanResult();
			}
		}, 0, 1, TimeUnit.SECONDS);
	}
	
	protected void scanResult() {
		List<BaseCallable> indexsToRemove = new ArrayList<BaseCallable>();
		
		AtomicLong prepNumTemp = new AtomicLong(0);
		AtomicLong runningNumTemp = new AtomicLong(0);
		AtomicLong suspendedNumTemp = new AtomicLong(0);

		for(BaseCallable baseCallable : callables){
			BaseResult baseResult = baseCallable.getBaseResult();
			Status status = baseResult.getStatus();
			switch (status) {
			case PREP: prepNumTemp.incrementAndGet(); break;
			case RUNNING: runningNumTemp.incrementAndGet(); break;
			case SUSPENDED: suspendedNumTemp.incrementAndGet(); break;
			case SUCCEEDED: succeededNum.incrementAndGet(); break;
			case FAILED: failedNum.incrementAndGet(); break;
			case KILLED: killedNum.incrementAndGet(); break;
			default:
				break;
			}
			if(baseResult.isDone()){
				indexsToRemove.add(baseCallable);
				totalNum.incrementAndGet();
			}
		}
		prepNum = prepNumTemp;
		runningNum = runningNumTemp;
		suspendedNum = suspendedNumTemp;
		for(BaseCallable baseCallable : indexsToRemove){
			callables.remove(baseCallable);
		}
	}

	public void start(List<BaseCallable> tasks){
		if(tasks == null){
			throw new RuntimeException("the list of tasks can not be null");
		}
		initSample();
		int tasksSize = tasks.size();
		callables = new ArrayList<BaseCallable>();
		callables.addAll(tasks);
		for(int i = 0; i < tasksSize; i++){
			try {
				executorService.submit(tasks.get(i));
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
			if(totalNum.get()==tasksSize){
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
