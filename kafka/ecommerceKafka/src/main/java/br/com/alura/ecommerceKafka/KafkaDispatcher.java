package br.com.alura.ecommerceKafka;

import java.io.Closeable;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

public class KafkaDispatcher<T> implements Closeable {
	private final KafkaProducer<String, T> producer;

	KafkaDispatcher(){
		this.producer = new KafkaProducer<>(properties());
	}

	private static Properties properties(){
		var properties = new Properties();
		properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
		properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, GsonSerializer.class.getName());
		return properties;
	}

	public KafkaProducer<String, T> getProducer() {
		return producer;
	}

	protected void send(String typeMessage, String key, T value) throws ExecutionException, InterruptedException {
		var producer = new ProducerRecord<>(typeMessage, key, value);
		this.producer.send(producer, getCallback()).get();
	}

	private static Callback getCallback() {
		return (data, ex) -> {
			if (ex != null) {
				ex.printStackTrace();
				return;
			} else {
				System.out.println("sucesso:" + data.topic() + "/partition:" + data.partition() + "/" + data.offset() + "/" + data.timestamp());
			}
		};
	}

	@Override
	public void close()  {
		producer.close();
	}
}
