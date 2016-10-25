package com.demo.gc;

public class TestGc {
	
	
	/**
	 * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+UseConcMarkSweepGC
	 */
	private static final int _1MB = 1024 * 1024;

	public static void main(String[] args) throws InterruptedException{
		byte[] a1, a2, a3, a4;
		a1 = new byte[2 * _1MB];
		a2 = new byte[2 * _1MB];
		a3 = new byte[2 * _1MB];
		a4 = new byte[4 * _1MB];
		System.out.println("====================");
		Thread.sleep(1000);
	}
}
