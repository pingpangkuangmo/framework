package com.demo.sort;

import org.junit.Test;

import com.demo.sort.util.SortUtil;

public class InsertSort {

	int[] data = {4, 6, 8, 1, 5, 9, 2, 7, 10, 3};
	
	@Test
	public void insertSort(){
		doInsertSort(data);
		SortUtil.print(data);
	}

	private void doInsertSort(int[] arr) {
		int len = arr.length;
		for(int i = 1; i < len; i++){
			if(arr[i] < arr[i-1]){
				int x = arr[i];
				int j = i-1;
				while(j>=0 && x < arr[j]){
					arr[j+1] = arr[j];
					j--;
				}
				arr[j+1] =x;
			}
		}
	}
}
