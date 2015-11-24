package com.demo.metaq.base;

import java.util.concurrent.Executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.taobao.metamorphosis.Message;
import com.taobao.metamorphosis.client.MessageSessionFactory;
import com.taobao.metamorphosis.client.MetaClientConfig;
import com.taobao.metamorphosis.client.MetaMessageSessionFactory;
import com.taobao.metamorphosis.client.consumer.ConsumerConfig;
import com.taobao.metamorphosis.client.consumer.MessageConsumer;
import com.taobao.metamorphosis.client.consumer.MessageListener;
import com.taobao.metamorphosis.exception.MetaClientException;
import com.taobao.metamorphosis.utils.ZkUtils.ZKConfig;

public class MetaqConsumer {
	
	private static Logger logger=LoggerFactory.getLogger(MetaqConsumer.class);

	public static void main(String[] args) throws MetaClientException{
		final MetaClientConfig metaClientConfig = new MetaClientConfig();
        final ZKConfig zkConfig = new ZKConfig();
        zkConfig.zkConnect = "192.168.126.131:2181";
        metaClientConfig.setZkConfig(zkConfig);
        MessageSessionFactory sessionFactory = new MetaMessageSessionFactory(metaClientConfig);
        final String topic = "test";
        final String group = "meta-example";
        MessageConsumer consumer = sessionFactory.createConsumer(new ConsumerConfig(group));
        consumer.subscribe(topic, 1024 * 1024, new MessageListener() {

            public void recieveMessages(Message message) {
                logger.info("Receive message " + new String(message.getData()));
            }
            public Executor getExecutor() {
                return null;
            }
        });
        consumer.completeSubscribe();
	}
}
