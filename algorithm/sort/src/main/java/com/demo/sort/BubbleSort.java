package com.demo.sort;

import org.junit.Test;

import com.demo.sort.util.SortUtil;

public class BubbleSort {
	
	int[] arr = {4, 6, 8, 1, 5, 9, 2, 7, 3};

	
	@Test
	public void base(){
		int len = arr.length;
		for(int i = 0; i < len - 1; i++){
			for(int j = 0; j< len - 1 - i; j++){
				if(arr[j] > arr[j+1]){
					int tmp = arr[j+1];
					arr[j+1] = arr[j];
					arr[j] = tmp;
				}
			}
		}
		SortUtil.print(arr);
	}
	
	@Test
	public void improve1(){
		int len = arr.length;
		int i = len -1;
		while(i > 0){
			int pos = 0;
			for(int j = 0; j< i; j++){
				if(arr[j] > arr[j+1]){
					pos = j;
					int tmp = arr[j+1];
					arr[j+1] = arr[j];
					arr[j] = tmp;
				}
			}
			i = pos;
		}
		SortUtil.print(arr);
	}
	
	@Test
	public void improve2(){
		int high = arr.length -1;
		int low = 0;
		while(low < high){
			for(int j = low; j < high; j++){
				if(arr[j] > arr[j+1]){
					int tmp = arr[j+1];
					arr[j+1] = arr[j];
					arr[j] = tmp;
				}
			}
			high--;
			for(int j = high; j > low; j--){
				if(arr[j] < arr[j-1]){
					int tmp = arr[j-1];
					arr[j-1] = arr[j];
					arr[j] = tmp;
				}
			}
			low++;
		}
		SortUtil.print(arr);
	}
	
}
