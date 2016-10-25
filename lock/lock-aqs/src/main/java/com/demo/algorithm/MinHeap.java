package com.demo.algorithm;

public class MinHeap{

	private Integer[] data = new Integer[256];
	
	private int size;
	
	public void add(Integer item){
		assert item != null;
		size++;
		if(size == 1){
			data[1] = item;
		}else{
			data[size] = item;
			fixUp(size);
		}
	}
	
	private void fixUp(int k) {
		int index;
		while((index = k >> 1) > 0){
			if(data[index] > data[k]){
				swap(k, index);
				k = index;
			}else{
				break;
			}
		}
	}
	
	private void fixDown(int k) {
		int index;
		while((index = k << 1) <= size){
			if((index < size) && (data[index] > data[index+1])){
				index++;
			}
			if(data[k] > data[index]){
				swap(k, index);
				k = index;
			}else{
				break;
			}
		}
	}
	
	private void swap(int a, int b){
		Integer tmp = data[a];
		data[a] = data[b];
		data[b] = tmp;
	}

	public void removeMin(){
		data[1] = data[size];
		data[size] = null;
		size--;
		fixDown(1);
	}
	
	public void print(){
		for(int i = 1;i <= size; i++){
			System.out.print(data[i] + " , ");
		}
		System.out.println();
	}
	
	public static void main(String[] args){
		MinHeap minHeap = new MinHeap();
		minHeap.add(80);
		minHeap.add(40);
		minHeap.add(20);
		minHeap.add(60);
		minHeap.add(90);
		minHeap.add(10);
		minHeap.add(50);
		minHeap.add(70);
		minHeap.add(100);
		minHeap.add(30);
		minHeap.print();
		minHeap.removeMin();
		minHeap.print();
	}

}
