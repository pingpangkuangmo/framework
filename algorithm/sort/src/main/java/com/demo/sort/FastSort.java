package com.demo.sort;

import org.junit.Test;

import com.demo.sort.util.SortUtil;

public class FastSort {

	int[] arr = {4, 6, 8, 1, 5, 9, 2, 7, 10, 3};
	
	@Test
	public void fastSort(){
		doFastSort(arr, 0, 9);
		SortUtil.print(arr);
	}
	
	public void doFastSort(int[] arr, int low, int high){
		if(low < high){
			int mid = baseSort(arr, low, high);
			doFastSort(arr, low, mid -1);
			doFastSort(arr, mid + 1, high);
		}
	}

	private int baseSort(int[] arr, int low, int high) {
		int baseValue = arr[low];
		while(low < high){
			while(low < high && arr[high] >= baseValue){
				high--;
			}
			SortUtil.swap(arr, low, high);
			while(low < high && arr[low] <= baseValue){
				low++;
			}
			SortUtil.swap(arr, low, high);
		}
		return low;
	}
	
}
