package com.demo.netty.bytebuf;

import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;
import sun.nio.ch.DirectBuffer;

public class DirectBufTest {

	public static void main(String[] args) throws InterruptedException{
		//分配512MB直接缓存
        ByteBuffer bb = ByteBuffer.allocate(1024*1024*512);
         
        TimeUnit.SECONDS.sleep(120);
         
        //清除直接缓存
        ((DirectBuffer)bb).cleaner().clean();
         
        TimeUnit.SECONDS.sleep(120);
         
        System.out.println("ok");
	}
}
