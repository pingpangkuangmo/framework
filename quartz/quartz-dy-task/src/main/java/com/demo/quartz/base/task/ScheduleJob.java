package com.demo.quartz.base.task;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

public class ScheduleJob {

	 public static void main(String[] args) throws Exception{
		SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();
		Scheduler sched = schedFact.getScheduler();
		sched.start();
		
		// define the job and tie it to our MyJob class
		JobDetail job = JobBuilder.newJob(MyJob.class)
		    .withIdentity("job1", "group1")
		    .build();
		
		// Trigger the job to run now, and then repeat every 40 seconds
		Trigger trigger = TriggerBuilder.newTrigger()
		    .withIdentity("trigger1", "group1")
		    .startNow()
		    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
		    .withIntervalInSeconds(40)
		    .repeatForever())
		    .build();
		
		// Tell quartz to schedule the job using our trigger
		sched.scheduleJob(job, trigger);
	 }
}
