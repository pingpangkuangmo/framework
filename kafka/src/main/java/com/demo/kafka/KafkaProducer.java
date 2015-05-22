package com.demo.kafka;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import com.alibaba.fastjson.JSON;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;


public class KafkaProducer {

	private Producer<String, String> producer;
	
	private String metadata_broker_list = "127.0.0.1:9092";
	
	public KafkaProducer(String metadata_broker_list) {
		this.metadata_broker_list = metadata_broker_list;
		producer = new Producer<String, String>(createProducerConfig());
	}
	
	public static void main(String[] args){
		final KafkaProducer producer=new KafkaProducer("192.168.81.232:9092");
		Thread t=new Thread(){

			@Override
			public void run() {
				for(int i=0;i<Integer.MAX_VALUE;i++){
					producer.send("lg-test",i+"message");
				}
				System.out.println("producer over");
			}
			
		};
		t.start();
	}

	private ProducerConfig createProducerConfig() {
		Properties properties = new Properties();
		properties.put("serializer.class", "kafka.serializer.StringEncoder");
		properties.put("compression.codec", "none");
		properties.put("queue.buffering.max.ms", "1000");
		properties.put("queue.buffering.max.messages", "30000");
		properties.put("queue.enqueue.timeout.ms", "-1");
		properties.put("request.required.acks", "1");
		properties.put("producer.type", "async");
		properties.put("metadata.broker.list", metadata_broker_list);
		properties.put("metadata.fetch.timeout.ms", 600000L);
		return new ProducerConfig(properties);
	}


	public void send(String topicName, String message) {
		try {
	        if(topicName == null || message == null) {  
	            return;  
	        }
	        KeyedMessage<String, String> km = new KeyedMessage<String, String>(topicName, String.valueOf(Math.random()), message);  
	        producer.send(km);
		} catch (Exception e) {
			shutdown();
    	}
    } 
	
	public void send(String topicName, Collection<?> messages) {
    	try {
    		if(topicName == null || messages == null) {  
                return;  
            }  
            if(messages.isEmpty()){  
                return;
            }
            List<KeyedMessage<String, String>> kms = new ArrayList<KeyedMessage<String, String>>();  
            for(Object entry : messages){
                KeyedMessage<String, String> km = new KeyedMessage<String, String>(topicName, String.valueOf(Math.random()), JSON.toJSONString(entry));  
                kms.add(km);  
            }  
            producer.send(kms);
    	} catch (Exception e) {
    		shutdown();
    	}
    }
    
    public void shutdown() {
    	if (producer != null) {
    		producer.close();
    		producer = null;
		}
    }

}
