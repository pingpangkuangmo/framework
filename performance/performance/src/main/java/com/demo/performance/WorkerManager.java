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
	private ExecutorService getStatusExecutorService;
	
	private SampleManager sampleManager = new SampleManager();
	
	private MyReference<AtomicLong> prepNum = new MyReference<AtomicLong>(new AtomicLong(0));
	private MyReference<AtomicLong> runningNum = new MyReference<AtomicLong>(new AtomicLong(0));
	private MyReference<AtomicLong> succeededNum = new MyReference<AtomicLong>(new AtomicLong(0));
	private MyReference<AtomicLong> failedNum = new MyReference<AtomicLong>(new AtomicLong(0));
	private MyReference<AtomicLong> killedNum = new MyReference<AtomicLong>(new AtomicLong(0));
	private MyReference<AtomicLong> suspendedNum = new MyReference<AtomicLong>(new AtomicLong(0));
	private MyReference<AtomicLong> totalNum = new MyReference<AtomicLong>(new AtomicLong(0));
	
	private List<BaseCallable> callables;
	
	private ScheduledExecutorService scanResultExecutorService = Executors.newScheduledThreadPool(1);
	
	public WorkerManager(int executorThreads){
		if(executorThreads < 2){
			throw new RuntimeException("executorThreads must not less than 2");
		}
		executorService = Executors.newFixedThreadPool(executorThreads);
		getStatusExecutorService = Executors.newFixedThreadPool(executorThreads/2);
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
		scanResultExecutorService.scheduleAtFixedRate(new Runnable() {
			
			@Override
			public void run() {
				scanResult();
			}
		}, 0, 2, TimeUnit.SECONDS);
	}
	
	protected void scanResult() {
		System.out.println("scanResult");
		List<BaseCallable> indexsToRemove = new ArrayList<BaseCallable>();
		
		AtomicLong prepNumTemp = new AtomicLong(0);
		AtomicLong runningNumTemp = new AtomicLong(0);
		AtomicLong suspendedNumTemp = new AtomicLong(0);

		for(BaseCallable baseCallable : callables){
			Status status = baseCallable.getStatus();
			switch (status) {
			case PREP: prepNumTemp.incrementAndGet(); break;
			case RUNNING: runningNumTemp.incrementAndGet(); break;
			case SUSPENDED: suspendedNumTemp.incrementAndGet(); break;
			case SUCCEEDED: succeededNum.get().incrementAndGet(); break;
			case FAILED: failedNum.get().incrementAndGet(); break;
			case KILLED: killedNum.get().incrementAndGet(); break;
			default:
				break;
			}
			if(baseCallable.isDone()){
				indexsToRemove.add(baseCallable);
				totalNum.get().incrementAndGet();
			}else{
				getStatusExecutorService.submit(new GetStatusCallable(baseCallable));
			}
		}
		prepNum.set(prepNumTemp);
		runningNum.set(runningNumTemp);
		suspendedNum.set(suspendedNumTemp);
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
			if(totalNum.get().get()==tasksSize){
				break;
			}
		}
		stop();
	}
	
	public void stop(){
		executorService.shutdown();
		getStatusExecutorService.shutdown();
		scanResultExecutorService.shutdown();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		sampleManager.stop();
	}
	
}
