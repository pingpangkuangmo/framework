package com.demo.kafka.base;

import kafka.cluster.Partition;
import kafka.cluster.Replica;
import kafka.controller.KafkaController;
import kafka.controller.ReplicaStateMachine;
import kafka.log.Log;
import kafka.log.LogManager;
import kafka.server.DelayedFetch;
import kafka.server.KafkaApis;
import kafka.server.ReplicaManager;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import kafka.server.KafkaServer;


public class KafkaProducerTest {


    KafkaServer kafkaServer;
	Partition partition;
    Replica replica;
	KafkaApis kafkaApis;
	KafkaController kafkaController;
	LogManager logManager;
	Log log;
	ReplicaManager replicaManager;
	DelayedFetch delayedFetch;
    ReplicaStateMachine replicaStateMachine;
    //TopicCommand topicCommand;



    public static void main(String[] args){
		Properties props=new Properties();
		props.setProperty("metadata.broker.list","192.168.126.131:9092");
		props.setProperty("serializer.class","kafka.serializer.StringEncoder");
		String topic="test";
        props.put("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer<Integer, String> producer = new KafkaProducer<Integer, String>(props);
		int count = 0;
		boolean isAsync = false;
		while(true){
			String messageStr = "msg " + count;
			long startTime = System.currentTimeMillis();
			if (isAsync) { // Send asynchronously
				producer.send(new ProducerRecord<Integer, String>(topic, count, messageStr),
						new DemoCallBack(startTime, count, messageStr));
			} else { // Send synchronously
				try {
					producer.send(new ProducerRecord<Integer, String>(topic, count, messageStr)).get();
					System.out.println("Sent message: (" + count + ", " + messageStr + ")");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			++count;
			System.out.println(count);
		}
		
	}

}

class DemoCallBack implements Callback {

	private final long startTime;
	private final int key;
	private final String message;

	public DemoCallBack(long startTime, int key, String message) {
		this.startTime = startTime;
		this.key = key;
		this.message = message;
	}

	/**
	 * A callback method the user can implement to provide asynchronous handling of request completion. This method will
	 * be called when the record sent to the server has been acknowledged. Exactly one of the arguments will be
	 * non-null.
	 *
	 * @param metadata  The metadata for the record that was sent (i.e. the partition and offset). Null if an error
	 *                  occurred.
	 * @param exception The exception thrown during processing of this record. Null if no error occurred.
	 */
	public void onCompletion(RecordMetadata metadata, Exception exception) {
		long elapsedTime = System.currentTimeMillis() - startTime;
		if (metadata != null) {
			System.out.println(
					"message(" + key + ", " + message + ") sent to partition(" + metadata.partition() +
							"), " +
							"offset(" + metadata.offset() + ") in " + elapsedTime + " ms");
		} else {
			exception.printStackTrace();
		}
	}
}
