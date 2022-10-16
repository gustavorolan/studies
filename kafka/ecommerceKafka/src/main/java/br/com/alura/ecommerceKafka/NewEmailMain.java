package br.com.alura.ecommerceKafka;

import java.math.BigDecimal;
import java.util.UUID;

public class NewEmailMain {
	public static void main(String[] args)  {
		try (var dispatcher = new KafkaDispatcher<Email>()) {
			for (var i = 0; i < 10; i++) {
				var email = new Email(
						UUID.randomUUID().toString(),
						UUID.randomUUID().toString(),
						"message");

				dispatcher.send("ECOMMERCE_SEND_EMAIL", email.getUserId(), email);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
