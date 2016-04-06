package com.demo.performance;

public interface Result{

	public boolean isDone();
	
	public static enum Status {
        PREP, RUNNING, SUCCEEDED, KILLED, FAILED, SUSPENDED
    }
	
	public Status getStatus();
	
}
