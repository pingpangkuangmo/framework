package com.demo.netty.bytebuf;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class Test {
	
	ConcurrentHashMap<String, String> sdfsdfds;

	public static void main(String[] args){
		ConcurrentHashMap<String, String> ssd= new ConcurrentHashMap<String, String>();
		ssd.size();
		List<Integer> sizeTable = new ArrayList<Integer>();
		for (int i = 512; i > 0; i <<= 1) {
            sizeTable.add(i);
        }
		for(int i = 0; i<sizeTable.size(); i++){
			System.out.println(sizeTable.get(i));
		}
	}
}
