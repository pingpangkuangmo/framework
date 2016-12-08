package com.demo.limit;

import com.google.common.util.concurrent.RateLimiter;

public class RateLimiterTest {

	RateLimiter rateLimiter;
	
	public static void main(String[] args){
		RateLimiter rateLimiter = RateLimiter.create(2.0);
		rateLimiter.acquire();
	}
}
