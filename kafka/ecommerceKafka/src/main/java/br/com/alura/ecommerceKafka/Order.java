package br.com.alura.ecommerceKafka;

import java.math.BigDecimal;

public class Order {
	private final String userId, orderID;
	private final BigDecimal value;

	public Order(String userId, String orderID, BigDecimal value) {
		this.userId = userId;
		this.orderID = orderID;
		this.value = value;
	}

	public String getUserId() {
		return userId;
	}

	public String getOrderID() {
		return this.orderID;
	}

	public BigDecimal getValue() {
		return value;
	}

}
