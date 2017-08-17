package com.min.kafka;

import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
//Producer
public class KafkaP {
	public static void main(String[] args) {
		Properties prop = new Properties();
		// 集群
		prop.put("bootstrap.servers", "centos131:9092,centos132:9092,centos133:9092");
		// 将记录全部提交
		prop.put("acks", "all");
		// 请求失败重试次数
		prop.put("retries", 0);
		// batch大小
		prop.put("batch.size", 16384);
		// 减少请求数目，增加延迟1ms
		prop.put("linger.ms", 1);
		// 缓冲区大小
		prop.put("buffer.memory", 102400);
		// 序列化
		prop.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		prop.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		Producer<String, String> producer = new KafkaProducer<String, String>(prop);
		for (int i = 0; i < 10; i++) {
			ProducerRecord<String, String> record = new ProducerRecord<String, String>("min2", "key:" + i,
					"value:" + i);
			producer.send(record);
		}
		producer.close();
	}
}
