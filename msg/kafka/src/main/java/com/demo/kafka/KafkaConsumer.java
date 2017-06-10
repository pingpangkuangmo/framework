package com.demo.kafka;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import kafka.message.MessageAndMetadata;
import me.ele.arch.metric.core.Counter;
import me.ele.arch.metric.core.Metric;
import me.ele.arch.metric.core.Timer;
import org.codehaus.jackson.map.ObjectMapper;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerFetcherThread;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.type.TypeReference;

public class KafkaConsumer extends Thread{
	
	private final ConsumerConnector consumer;
	private final String topic;
	
	private ObjectMapper om = new ObjectMapper();

	ConsumerFetcherThread consumerFetcherThread;
	
	public KafkaConsumer(String topic) {
		consumer = Consumer.createJavaConsumerConnector(createConsumerConfig());
		this.topic = topic;
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException{
		KafkaConsumer consumer=new KafkaConsumer("_application");
		consumer.start();
	}
	
	public void run() {
		try {
			Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
			topicCountMap.put(topic, new Integer(1));
			Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
			KafkaStream<byte[], byte[]> stream = consumerMap.get(topic).get(0);
			ConsumerIterator<byte[], byte[]> it = stream.iterator();
			while (it.hasNext()){
				MessageAndMetadata data = it.next();
				List<Metric> metrics = MetricsEncodeDecode.decodeMetrics((byte[]) data.message());
				for (Metric item : metrics) {
					if (item instanceof Counter) {
						Counter counter = (Counter) item;
						if (counter.getName().equalsIgnoreCase("etrace.dashboard.agent_success")
								&& counter.getTimestamp() == 0) {
							String str = om.writeValueAsString(item);
							System.out.println(str);
						}
					}
					String str = om.writeValueAsString(item);
					System.out.println(str);
//					if (item.getMetricType().equalsIgnoreCase("timer")) {
//						Timer timer = (Timer) item;
//						if (timer.getMaxMsg() == null ){
//							System.out.println(om.writeValueAsString(item));
//						}
//					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static ConsumerConfig createConsumerConfig() {
		Properties props = new Properties();
		//props.put("zookeeper.connect", 			  "alta1-etrace-kafka-1.vm.elenet.me:2181,alta1-etrace-kafka-2.vm.elenet.me:2181,alta1-etrace-kafka-3.vm.elenet.me:2181");
		props.put("zookeeper.connect", "192.168.112.78:2181,192.168.112.79:2181,192.168.112.81:2181");
		props.put("group.id", 					  "metrics-lg3");
		props.put("auto.offset.reset", 			  "largest");
		props.put("zookeeper.session.timeout.ms", "15000");
		props.put("zookeeper.sync.time.ms", 	  "3000");
		props.put("auto.commit.interval.ms", 	  "1000");
		props.put("fetch.message.max.bytes", "10485760");
		props.put("consumer.timeout.ms", "2");
		return new ConsumerConfig(props);
	}
}
