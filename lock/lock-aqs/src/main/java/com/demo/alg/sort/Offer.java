package com.demo.alg.sort;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

import org.junit.Test;

import sun.security.util.Length;

public class Offer {

	
	@Test
	public void testPushPop(){
		List<Integer> pushArr = Arrays.asList(1, 2, 3, 4, 5);
		List<Integer> popArr = Arrays.asList(4, 3, 5, 1, 2);
		System.out.println(pushPop(pushArr, popArr));
	}
	
	public boolean pushPop(List<Integer> pushArr, List<Integer> popArr){
		if(pushArr == null || popArr == null || pushArr.size() != popArr.size()){
			throw new RuntimeException("not valid");
		}
		Stack<Integer> stack = new Stack<Integer>();
		int i = 0;
		int j = 0;
		int len = pushArr.size();
		while(i < len && j < len){
			stack.push(pushArr.get(i));
			i++;
			while(stack.size() != 0 && stack.peek() == popArr.get(j) && j < len){
				stack.pop();
				j++;
			}
		}
		if(j == len && i == len){
			return true;
		}else{
			return false;
		}
	}
	
	@Test
	public void testPrintNode(){
		TreeNode root = new TreeNode(8);
		TreeNode a = new TreeNode(6);
		TreeNode b = new TreeNode(10);
		root.left = a;
		root.right = b;
		
		TreeNode c = new TreeNode(5);
		TreeNode d = new TreeNode(7);
		a.left = c;
		a.right = d;
		
		TreeNode e = new TreeNode(9);
		TreeNode f = new TreeNode(11);
		b.left = e;
		b.right = f;
		
		printNode(root);
	}
	
	public void printNode(TreeNode root){
		if(root == null){
			return;
		}
		Queue<TreeNode> nodes = new ArrayDeque<TreeNode>();
		nodes.add(root);
		while(nodes.size() > 0){
			TreeNode node = nodes.poll();
			System.out.println(node.value);
			if(node.left != null){
				nodes.add(node.left);
			}
			if(node.right != null){
				nodes.add(node.right);
			}
		}
		
	}
	
	class TreeNode{
		Object value;
		TreeNode left;
		TreeNode right;
		
		public TreeNode(Object value){
			this.value = value;
		}
	}
	
	@Test
	public void testBackReverse(){
		System.out.println(backReverse(new Integer[]{7, 6, 4, 5}));
	}
	
	public boolean backReverse(Integer[] arr){
		if(arr == null || arr.length == 0){
			throw new RuntimeException("arr not valid");
		}
		return doBackReverse(arr, 0, arr.length-1);
	}
	
	public boolean doBackReverse(Integer[] arr, int start, int end){
		if(start >= end){
			return true;
		}
		Integer rootValue = arr[end];
		int midIndex = start;
		int large = 0;
		for(int i = start; i < end; i++){
			if(arr[i] > rootValue){
				midIndex = i;
				large ++;
			}
			if(large > 0 && arr[i] < rootValue){
				return false;
			}
		}
		if(midIndex == start || midIndex == end-1){
			return doBackReverse(arr, start, end-1);
		}else{
			boolean leftValid = true;
			boolean rightValid = true;
			leftValid = doBackReverse(arr, start, midIndex -1);
			rightValid = doBackReverse(arr, midIndex, end-1);
			if(leftValid && rightValid){
				return true;
			}else{
				return false;
			}
		}
		
	}
	
	public void arr(){
		int[][] arr = new int[][]{{1,2,3},{1,2,3}};
	}
	
	@Test
	public void testTwoSum(){
		int[] nums = new int[]{1, 3, 5, 8, 10, 16, 19, 21, 27, 33, 43, 49, 51, 55, 59, 60, 63, 78, 99};
		int[] ret = twoSum(nums, 36);
		System.out.println(ret[0] + ", " + ret[1]);
	}
	
	@Test
	public void testTwoSum2(){
		int[] nums = new int[]{3, 2, 4};
		int[] ret = twoSum(nums, 6);
		System.out.println(ret[0] + ", " + ret[1]);
	}
	
	public int[] twoSum(int[] nums, int target) {
        if(nums == null || nums.length < 2){
        	throw new RuntimeException("nums is invalid");
        }
        int min = nums[0] + nums[1];
        int max = nums[nums.length-1] + nums[nums.length-2];
        if(min > target || max < target){
        	throw new RuntimeException("no two sum solution");
        }
        int start = 0;
        int endEnd = nums.length-1;
        int startEnd = 0;
        int midEnd = (startEnd + endEnd)/2;
        boolean step = false;
        while(start < midEnd){
        	if(!step){
        		int maxSum = nums[start] + nums[endEnd];
            	if(maxSum < target){
            		start++;
            		continue;
            	}
        		int sum = nums[start] + nums[midEnd];
        		if(sum == target){
        			return new int[]{start, midEnd};
        		}else if(sum > target){
        			endEnd = midEnd;
        			midEnd = (startEnd + endEnd)/2;
        		}else{
        			startEnd = midEnd;
        			midEnd = (startEnd + endEnd)/2;
        		}
        		if(midEnd - startEnd < 3){
        			step = true;
        			midEnd = endEnd;
        		}
        	}else{
        		int sum = nums[start] + nums[midEnd];
        		if(sum == target){
        			return new int[]{start, midEnd};
        		}else if(sum > target){
        			midEnd--;
        		}else{
        			start++;
        		}
        	}
        }
        throw new RuntimeException("no two sum solution");
	}
}
