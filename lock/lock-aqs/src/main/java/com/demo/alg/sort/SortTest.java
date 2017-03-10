package com.demo.alg.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Random;
import java.util.Timer;
import java.util.TreeMap;
import java.util.Vector;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.Lock;

import org.junit.Test;

public class SortTest {
	
	Timer timer;
	Random random;
	ArrayBlockingQueue<String> arrayBlockingQueue;
	LinkedBlockingQueue<String> linkedBlockingQueue;
	PriorityBlockingQueue<String> priorityBlockingQueue;
	ThreadPoolExecutor threadPoolExecutor;
	Executors executors;
	DelayQueue<Delayed> delayQueue;
	Future<String> future;
	Vector<String> vector;
	Hashtable<String, String> hashTable;
	Collections collections;
	Thread thread;
	CountDownLatch countDownLatch;
	CyclicBarrier cyclicBarrier;
	Lock lock;
	CopyOnWriteArrayList<String> copyOnWriteArrayList;
	Semaphore semaphore;
	Short sht;
	StringBuffer sb;
	TreeMap<String, String> treeMap;
	LinkedHashMap<String, String> linkedHashMap;
	ArrayList<String> dds;
	ConcurrentSkipListMap<String, String> skipList;
	
	/**
	 * 5.冒泡排序
		基本思想：在要排序的一组数中，对当前还未排好序的范围内的全部数，自上而下对相邻的两个数依次进行比较和调整，
		让较大的数往下沉，较小的往上冒。即：每当两相邻的数比较后发现它们的排序与排序要求相反时，就将它们互换
	 */
	@Test
	public void bubbleSort(){
		 int[] data={49,38,65,97,76,13,27,49,78,34,12,64,5,4,62,99,98,54,56,17,18,23,34,15,35,25,53,51};
		 for(int i=0,len=data.length;i<len-1;i++){
			 for(int j=0;j<len-1-i;j++){
				 if(data[j]>data[j+1]){
					 int temp=data[j];
					 data[j]=data[j+1];
					 data[j+1]=temp;
				 }
			 }
		 }
		 for(int i=0;i<data.length;i++){
			 System.out.print(data[i]+" ");
		 }
	}
	
	/**
	 * 6.快速排序
		基本思想：选择一个基准元素,通常选择第一个元素或者最后一个元素,通过一趟扫描，将待排序列分成两部分,一部分比基准元素小,一部分大于等于基准元素,此时基准元素在其排好序后的正确位置,
		然后再用同样的方法递归地排序划分的两部分。
	 */
	@Test
	public void fastSort(){
		int[] data={49,44,44,64,64,32,64,40,49,49,32,64,99};
		doFastSort(data, 0, data.length-1);
		print(data);
	}
	
	@Test
	public void test2(){
		Random r = new Random(1000);   
        for(int i = 1; i < 4; i++){   
            System.out.println("第" + i + "次:" + r.nextInt());   
        }   
	}
	
	private void doFastSort(int[] data,int start,int end){
		if(start >= end){
			return;
		}
		int midValue=data[end];
		int i = start;
		int j = end-1;
		int index = i;
		while(i < j){
			while(data[i] < midValue && i<j)
				i++;
			while(data[j] >= midValue && i<j)
				j--;
			swap(data, i, j);
		}
		if (data[i] >= data[j])
			swap(data, i, j);
		else
			i++;
		doFastSort(data, start, index-1);
		doFastSort(data, index+1, end);
	}
	
	private void doFastSort1(int[] data,int start,int end){
		if(start >= end){
			return;
		}
		int midValue = data[start];
		int i = start;
		int j = end;
		while(i < j){
			while(data[j] >= midValue && i < j)
				j--;
			data[i] = data[j]; 
			while(data[i] <= midValue && i < j)
				i++;
			data[j] = data[i]; 
		}
		data[i] = midValue;
		doFastSort1(data, start, i-1);
		doFastSort1(data, i+1, end);
	}
	
	private void swap(int[] data, int a, int b){
		int tmp = data[a];
		data[a] = data[b];
		data[b] = tmp;
	}
	
	private void print(int[] data){
		for(int i=0;i<data.length;i++){
			System.out.print(data[i]+" ");
		}
	}
}
