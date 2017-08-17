package com.min.kafka;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Properties;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class KafkaC {
	public static void main(String[] args) {
		Properties prop = new Properties();
		prop.put("bootstrap.servers", "centos131:9092,centos132:9092,centos133:9092");
		prop.put("group.id", "test");
		// 自动拉取消息
		prop.put("enable.auto.commit", "true");
		// 拉取间隔
		prop.put("max.poll.interval.ms", "5000");
		// 超时时间
		prop.put("session.timeout.ms", "30000");
		// 反序列化
		prop.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		prop.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		Consumer<String, String> custom = new KafkaConsumer<String, String>(prop);
		custom.subscribe(Arrays.asList("min2"));
		while (true) {
			ConsumerRecords<String, String> record = custom.poll(10);
			for (ConsumerRecord<String, String> consumerRecord : record) {
				System.out.println(consumerRecord.offset() + ":" + consumerRecord.key() + ":" + consumerRecord.value());
			}
		}
	}
}
