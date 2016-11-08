package com.demo.jvm;

import java.lang.reflect.Field;
import java.sql.DriverManager;

import sun.misc.Unsafe;

@SuppressWarnings("restriction")
public class JVMTest {
	
	DriverManager dd;

	public static void main(String[] args) throws Exception{
		try {
			Unsafe.getUnsafe();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Field f = Unsafe.class.getDeclaredField("theUnsafe"); //Internal reference  
		f.setAccessible(true);  
		Unsafe unsafe = (Unsafe) f.get(null);
		System.out.println(unsafe);
		
	}
}
