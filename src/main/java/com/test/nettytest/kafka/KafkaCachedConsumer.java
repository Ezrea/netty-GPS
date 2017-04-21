package com.test.nettytest.kafka;

import java.util.Properties;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Future;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import com.test.nettytest.util.GpsUtil;

/**
 * Kafka 缓冲区的消费者，把缓冲区里的数据存进Kafka
 * 
 * @author qc
 */
public class KafkaCachedConsumer implements Runnable
{
	/**
	 * Kafka 的消息提供者
	 */
	private Producer<String, String> producer;

	public static ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<String>();

	private KafkaCachedConsumer()
	{
		Properties props = new Properties();
		//声明zk
		//props.put("zookeeper.connect", "192.168.1.107:2887,192.168.1.107:2888,192.168.1.107:2889");
		// 声明kafka broker
		props.put("bootstrap.servers", GpsUtil.getPropertiesValueByKey("kafka-server"));
		//配置value的序列化类
		props.put("value.serializer", StringSerializer.class.getName());
		//配置key的序列化类
		props.put("key.serializer", StringSerializer.class.getName());
		//判别请求是否为完整的条件（就是是判断是不是成功发送了）。我们指定了“all”将会阻塞消息，这种设置性能最低，但是是最可靠的。
		props.put("acks", "all");

		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);
		props.put("batch.size", 16384);

		producer = new KafkaProducer<String, String>(props);
	}

	public static KafkaCachedConsumer getInstance()
	{
		return InstanceHolder.instance;
	}

	static class InstanceHolder
	{
		private static KafkaCachedConsumer instance = new KafkaCachedConsumer();
	}

	/**
	 * 把 ConcurrentLinkedQueue 里的数据传至 Kafka
	 */
	@Override
	public void run()
	{
		for (int i = 0; i < 10; i++)
		{
			if (queue.isEmpty())
				break;
			String key = System.currentTimeMillis() + "";
			String msg = queue.poll();
			
			producer.send(new ProducerRecord<String, String>(GpsUtil.getPropertiesValueByKey("kafka-topic"), key, msg));
			System.out.println("线程信息 --> " + Thread.currentThread().getName());
			
			//Future<RecordMetadata> future = producer.send(new ProducerRecord<String, String>(GpsUtil.KAFKA_TOPIC, key, msg));
			//System.out.println(future);

			//producer.close();
		}
	}

}
