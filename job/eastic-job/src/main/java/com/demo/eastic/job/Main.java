package com.demo.eastic.job;

import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.TaskScheduler;

import sun.misc.Signal;
import sun.misc.SignalHandler;

import com.dangdang.ddframe.job.api.ElasticJob;
import com.dangdang.ddframe.job.internal.job.AbstractElasticJob;
import com.dangdang.ddframe.job.internal.schedule.JobScheduleController;

public class Main {

	// job
	ElasticJob elasticJob;
	AbstractElasticJob abstractOneOffElasticJob;
	
	JobScheduleController jobScheduleController;
	
	Timer timer;
	
	ScheduledExecutorService scheduledExecutorService;
	ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;
	Executors executors;
	
	TaskExecutor taskExecutor;
	TaskScheduler taskScheduler;
	
	SignalHandler signalHandler;
	Signal signal;
	
	public static void main(String[] args){
		Signal.handle(new Signal("INT"), new SignalHandler(){

			@Override
			public void handle(Signal arg0) {
				System.out.println("hello signal");
			}
		});
	}
}
