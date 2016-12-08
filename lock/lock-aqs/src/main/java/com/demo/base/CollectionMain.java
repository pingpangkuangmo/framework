package com.demo.base;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

public class CollectionMain {

	Collection<?> collection;
	LinkedList<?> link;
	ArrayList<?> arr;
	Stack<?> stack;
	Queue<?> queue;
	BlockingQueue<?> blockingQueue;
	ArrayBlockingQueue<?> arrayBlockingQueue;
	LinkedBlockingQueue<?> linkedBlockingQueue;
	
	public static void main(String[] args){
		final List<String> list = new ArrayList<>();
		list.add("a1");
		list.add("a2");
		list.add("a3");
		list.add("a4");
		list.add("a5");
		
		Thread t1 = new Thread(){

			@Override
			public void run() {
				CollectionMain.sleep(2000L);
				list.add("a6");
				CollectionMain.sleep(2000L);
				list.add("a7");
			}
			
		};
		
		Thread t2 = new Thread(){

			@Override
			public void run() {
				Iterator<String> it = list.iterator();
				while(it.hasNext()){
					CollectionMain.sleep(2000L);
					System.out.println(it.next());
				}
			}

		};
		t2.start();
		t1.start();
		
	}
	
	public static void sleep(long ms){
		try {
			Thread.sleep(ms);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
