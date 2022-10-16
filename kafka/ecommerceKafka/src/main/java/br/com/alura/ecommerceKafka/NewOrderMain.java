package br.com.alura.ecommerceKafka;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class NewOrderMain {
	public static void main(String[] args)  {
		try (var dispatcher = new KafkaDispatcher()) {
			for (var i = 0; i < 10; i++) {
				var key = UUID.randomUUID()
						.toString();
				var value = "1234,,6789,1242141241241";
				var email = "welcome";
				dispatcher.send("ECOMMERCE_NEW_ORDER", key, value);
				dispatcher.send("ECOMMERCE_SEND_EMAIL", key, email);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
