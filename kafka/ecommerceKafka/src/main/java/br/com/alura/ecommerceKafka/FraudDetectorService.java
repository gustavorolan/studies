package br.com.alura.ecommerceKafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public class FraudDetectorService {
	public static void main(String[] args) {
		var fraudDetectorService = new FraudDetectorService();
		try (
				var service = new KafkaService(
						FraudDetectorService.class.getSimpleName(),
						"ECOMMERCE_NEW_ORDER",
						fraudDetectorService::parse)
		) {
			service.run();
		}
	}

	private void parse(ConsumerRecord<String, String> record) {
		System.out.println("--------------------");
		System.out.println("Processing new order, checking fraud");
		System.out.println("--------------------");
		System.out.println(record.key());
		System.out.println("--------------------");
		System.out.println(record.value());
		System.out.println("--------------------");
		System.out.println(record.partition());
		System.out.println("--------------------");
		System.out.println(record.offset());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		System.out.println("Order Processed");
	}
}
