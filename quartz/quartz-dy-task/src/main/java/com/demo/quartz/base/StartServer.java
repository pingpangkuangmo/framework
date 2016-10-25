package com.demo.quartz.base;

import java.io.IOException;
import java.util.Timer;
import java.util.concurrent.ScheduledExecutorService;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.jdbcjobstore.Semaphore;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean.MethodInvokingJob;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean.StatefulMethodInvokingJob;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

public class StartServer {
	
	//spring job
	MethodInvokingJob methodInvokingJob;
	StatefulMethodInvokingJob statefulMethodInvokingJob;
	
	Job job;
	JobDetail jobDetail;
	Scheduler scheduler;
	Trigger trigger;
	
	SchedulerFactory schedulerFactory;
	StdSchedulerFactory stdSchedulerFactory;
	Semaphore semaphore;
	
	//spring
	TaskExecutor taskExecutor;
	TaskScheduler taskScheduler;
	
	SchedulerFactoryBean schedulerFactoryBean;
	
	Timer timer;
	ScheduledExecutorService scheduledExecutorService;
	

	public static void main(String[] args) throws IOException{
		@SuppressWarnings("resource")
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"spring-context.xml"});  
        context.start();  
        System.out.println("按任意键退出");  
        System.in.read();
	}
}
