package com.demo.kafka.base;

import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;


public class KafkaProducer {

	public static void main(String[] args){
		Properties props=new Properties();
		props.setProperty("metadata.broker.list","192.168.126.131:9092");
		props.setProperty("serializer.class","kafka.serializer.StringEncoder");
		props.setProperty("key.serializer.class","kafka.serializer.StringEncoder");
		String topic="test";
		ProducerConfig config = new ProducerConfig(props);
		Producer<String,String> producer=new Producer<String,String>(config);
		Long count=0L;
		while(true){
			KeyedMessage<String, String> msg = new KeyedMessage<String, String>(topic,"一一"+count);
			producer.send(msg);
			count++;
		}
		
	}
}
