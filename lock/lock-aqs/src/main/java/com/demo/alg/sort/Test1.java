package com.demo.alg.sort;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class Test1 {

	int[] arr;
	

	private void swap(int x, int y) {
		int temp = arr[x];
		arr[x] = arr[y];
		arr[y] = temp;
	}

	private void quick_sort_recursive(int start, int end) {
		if (start >= end)
			return;
		int mid = arr[end];
		int left = start, right = end - 1;
		while (left < right) {
			while (arr[left] < mid && left < right)
				left++;
			while (arr[right] >= mid && left < right)
				right--;
			swap(left, right);
		}
		if (arr[left] >= arr[end])
			swap(left, end);
		else
			left++;
		quick_sort_recursive(start, left - 1);
		quick_sort_recursive(left + 1, end);
	}

	public void sort(int[] arrin) {
		arr = arrin;
		quick_sort_recursive(0, arr.length - 1);
	}
	
	private void print(){
		for(int i=0;i<arr.length;i++){
			System.out.print(arr[i]+" ");
		}
	}
	
	public static void main(String[] args){
		Test1 test = new Test1();
		int[] data = {33,33,33,22};
		test.sort(data);
		test.print();
	}
	
	public static String reverse(String str){
		int len = str.length();
		StringBuilder sb = new StringBuilder();
		for(int i = len-1;i > 0; i++){
			sb.append(str.charAt(i));
		}
		return sb.toString();
	}
	
	public static String reverse2(String str){
		if(str == null || str.length() == 0){
			return null;
		}
		char[] arr = str.toCharArray();
		int len = arr.length;
		for(int i = 0; i < len/2; i++){
			char tmp = arr[i];
			arr[i] = arr[len-1-i];
			arr[len-1-i] = tmp;
		}
		return new String(arr);
	}
	
	public static void reverse(TreeNode root){
		if(root == null || (root.left == null && root.right == null)){
			return;
		}
		TreeNode tmp = root.left;
		root.left = root.right;
		root.right = tmp;
		reverse(root.left);
		reverse(root.right);
	}
	
	public static List<Integer> sort(List<List<Integer>> arrs){
		List<Integer> ret = new ArrayList<Integer>();
		if(arrs == null){
			return null;
		}
		if(arrs.size() == 1){
			return arrs.get(0);
		}
		int len = arrs.size();
		int totalLen = 0;
		Integer[] minIndexArr = new Integer[len];
		for(int i = 0; i< len; i++){
			if(arrs.get(i) == null){
				minIndexArr[i] = null;
			}else{
				minIndexArr[i] = arrs.get(i).get(0);
			}
			totalLen = totalLen + arrs.get(i).size();
		}
		for(int i = 0; i< totalLen; i++){
			ret.add(getMin(minIndexArr, arrs));
			
		}
		return ret;
	}
	
	private static Integer getMin(Integer[] minArr, List<List<Integer>> arrs) {
		int min = minArr[0];
		int index = 0;
		for(int i = 1; i < minArr.length; i++){
			if(minArr[i] < min){
				min = minArr[i];
				index++;
			}
		}
		return min;
	}

	class TreeNode{
		TreeNode left;
		TreeNode right;
		Object value;
	}
	
	@Test
	public void testBit(){
		System.out.println(~15);
	}
}
