package com.demo.kafka;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

import kafka.admin.AdminUtils;
import kafka.consumer.ZookeeperConsumerConnector;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import kafka.serializer.StringEncoder;
import kafka.server.KafkaApis;
import kafka.server.KafkaServer;
import kafka.server.ReplicaManager;
import me.ele.arch.metric.core.Counter;
import me.ele.arch.metric.core.Metric;
import me.ele.arch.metric.core.MetricType;
import org.codehaus.jackson.map.ObjectMapper;

import static org.codehaus.jackson.JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES;
import static org.codehaus.jackson.map.DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY;
import static org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES;
import static org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion.NON_NULL;


public class KafkaProducer {
	
	kafka.producer.Producer p;
	LinkedBlockingQueue q;
	KafkaServer kafkaServer;
	KafkaApis kafkaApis;
	AdminUtils adminUtils;
	ReplicaManager replicaManager;
	ZookeeperConsumerConnector zookeeperConsumerConnector;

	private Producer<byte[], byte[]> producer;

	private static ObjectMapper mapper;
	static {
		mapper = new ObjectMapper();
		mapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.configure(ALLOW_UNQUOTED_FIELD_NAMES, true);
		mapper.configure(ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		mapper.setSerializationInclusion(NON_NULL);
	}
	
	private String metadata_broker_list = "10.2.27.122:9092";
	private static Long count = 0L;
	
	public KafkaProducer(String metadata_broker_list) {
		this.metadata_broker_list = metadata_broker_list;
		producer = new Producer<byte[], byte[]>(createProducerConfig());
	}
	
	public static void main(String[] args){
		//final KafkaProducer producer=new KafkaProducer("10.104.105.122:9092,10.104.105.120:9092,10.104.105.123:9092");
		//final String topic="_new_application";
		final String topic="my_test";
		final KafkaProducer producer=new KafkaProducer("10.103.102.168:9092");
		Thread t=new Thread(){

			@Override
			public void run() {
				for(int i=0;i<Long.MAX_VALUE;i++){
					try {
						producer.send(topic, MetricsEncodeDecode.encodeMetrics(getCounters()));
						System.out.println("send ok");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				System.out.println("producer over");
			}
			
		};
		t.start();

		List<Metric> metrics = new ArrayList<Metric>();
	}

	private static List<Metric> getCounters(){
		List<Metric> counters = new ArrayList();
		for(int i = 0; i < 4; i++){
			count++;
			Counter counter = new Counter();
			counter.setName("counter" + count % 30);
			counter.setMetricType(MetricType.Counter.type());
			counter.setValue(1);
			long now = System.currentTimeMillis();
			counter.setTimestamp(now);
			counter.setSampling(now + " sample");

			Map<String, String> tags = new HashMap();
			tags.put("ip", count % 20 + "");
			counter.setTags(tags);

			counters.add(counter);
		}
		return counters;
	}

	StringEncoder sds;
	private ProducerConfig createProducerConfig() {
		Properties properties = new Properties();
		properties.put("serializer.class", "kafka.serializer.DefaultEncoder");
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


	public void send(String topicName, byte[] message) {
		try {
	        if(topicName == null || message == null) {  
	            return;  
	        }
	        KeyedMessage<byte[], byte[]> km = new KeyedMessage<byte[], byte[]>(topicName, String.valueOf(Math.random()).getBytes(), message);
	        producer.send(km);
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
