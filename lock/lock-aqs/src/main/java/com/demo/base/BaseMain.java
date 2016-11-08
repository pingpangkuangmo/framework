package com.demo.base;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BaseMain {
	
	Collections coll;
	Comparator<String>  sds;

	public static void main(String[] args) throws Exception {
		ArrayList<Integer> arrayList3=new ArrayList<Integer>();  
        arrayList3.add(1);//这样调用add方法只能存储整形，因为泛型类型的实例为Integer  
        arrayList3.getClass().getMethod("add", Object.class).invoke(arrayList3, "asd");  
        for (int i=0;i<arrayList3.size();i++) {  
            System.out.println(arrayList3.get(i));  
        }  
	}
	
	public static void test1(){
		Number[] num = new Integer[10];
		num[0] = 1.2f;
		System.out.println(num[0]);
		
		List<Integer> integerList = new ArrayList<Integer>();
		List<Number> a = integerList;
		
		List<? extends Number> list = new ArrayList<Number>();
		list.add(new Integer(1));
		list.add(new Float(1.2f));
		
		List<? super Number> b = new ArrayList<Number>();
		b.add(new Integer(1));
		b.add(new Float(1.2f));
	}
}
