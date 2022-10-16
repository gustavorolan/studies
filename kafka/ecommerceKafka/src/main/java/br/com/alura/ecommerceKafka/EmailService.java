package br.com.alura.ecommerceKafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public class EmailService {
	public static void main(String[] args) {
		var emailService = new EmailService();
		try (
				var service = new KafkaService(
						EmailService.class.getSimpleName(),
						"ECOMMERCE_SEND_EMAIL",
						emailService::parse,
						Email.class.getName())
		) {
			service.run();
		}

	}

	private void parse(ConsumerRecord<String, Email> record) {
		System.out.println("Found something");
		System.out.println("--------------------");
		System.out.println("Sending email");
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

		System.out.println("Email Sent");
	}

}
