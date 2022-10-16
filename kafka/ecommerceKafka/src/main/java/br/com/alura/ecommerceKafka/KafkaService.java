package br.com.alura.ecommerceKafka;

import java.io.Closeable;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.UUID;
import java.util.regex.Pattern;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

public class KafkaService<T> implements Closeable {

	private final KafkaConsumer<String, T> consumer;
	private final ConsumerFunction parse;

	KafkaService(String groupId, String topic, ConsumerFunction parse, String aclass) {
		this.parse = parse;
		this.consumer = new KafkaConsumer<>(properties(groupId, aclass));
		System.out.println(aclass);
		consumer.subscribe(Collections.singletonList(topic));
	}

	public KafkaService(String groupId, Pattern topic, ConsumerFunction parse, String aclass) {
		this.parse = parse;
		this.consumer = new KafkaConsumer<>(properties(groupId, aclass));
		consumer.subscribe(topic);
	}


	@SuppressWarnings("InfiniteLoopStatement")
	void run() {
		while (true) {
			var records = consumer.poll(Duration.ofMillis(100));
			if (!records.isEmpty()) {
				records.forEach(parse::consume);
			}
		}
	}

	private Properties properties(String groupId, String aclass) {
		var properties = new Properties();
		properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, GsonDeserializer.class.getName());
		properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		properties.setProperty(ConsumerConfig.CLIENT_ID_CONFIG, UUID.randomUUID().toString());
		properties.setProperty(GsonDeserializer.TYPE_CONFIG, aclass);
		return properties;
	}

	@Override
	public void close() {
		consumer.close();
	}
}
