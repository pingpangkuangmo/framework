package com.demo;

import org.codehaus.jackson.io.NumberInput;

import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{

    private NumberInput numberInput;
    private com.fasterxml.jackson.core.io.NumberInput numberInput2;
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Map<String, String> data = new HashMap();
        data.put("key1", "value1");
        data.put("key2", "value2");
        data.put("key3", "value3");

    }
}
