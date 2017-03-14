package com.demo.alg.sort;

import java.math.BigInteger;

public class TestMain {

	public static void main(String[] args){
		System.out.println("test 快排");
		BigInteger bi1 = new BigInteger(new byte[]{1,1}); 
        System.out.println("bi1=" + bi1.toString()); 
	}
	
	public static void fastSort(int[] arr){
		if(arr == null || arr.length <= 1){
			return;
		}
		doFastSort(arr, 0, arr.length-1);
	}
	
	public static void doFastSort(int[] arr, int start, int end){
		if(start >= end){
			return;
		}
		int midValue = arr[start];
		int i = start;
		int j = end;
		while(i < j){
			while(arr[j] >= midValue && i < j){
				j--;
			}
			arr[i] = arr[j];
			while(arr[i] <= midValue && i < j){
				i++;
			}
			arr[j] = arr[i];
		}
		arr[i] = midValue;
		doFastSort(arr, i+1, end);
		doFastSort(arr, start, i-1);
	}
	
	public static void print(int[] arr){
		if(arr == null || arr.length < 1){
			return;
		}
		for(int i = 0, len = arr.length; i< len-1; i++){
			System.out.println(arr[i]);
		}
	}

}
