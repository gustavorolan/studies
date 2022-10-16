package br.com.alura.ecommerceKafka;

import java.util.regex.Pattern;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public class LogService {
	public static void main(String[] args) {
		var logService = new LogService();
		try (var service = new KafkaService(
				LogService.class.getSimpleName(),
				Pattern.compile("ECOMMERCE.*"), logService::parse,
				String.class.getName())
		) {
			service.run();
		}
	}

	private void parse(ConsumerRecord<String, String> record) {
		System.out.println("--------------------");
		System.out.println(
				"LOG: " + record.topic() +
				" | " + record.key() +
				" | " + record.value() +
				" | " + record.partition() +
				" | " + record.offset());
		System.out.println("--------------------");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

	}
}
