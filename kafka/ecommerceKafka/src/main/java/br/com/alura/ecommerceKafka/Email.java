package br.com.alura.ecommerceKafka;

import java.math.BigDecimal;

public class Email {
	private final String userId;
	private final String emailId;
	private final String value;

	public Email(String userId, String emailId, String value) {
		this.userId = userId;
		this.emailId = emailId;
		this.value = value;
	}

	public Email() {
		this.userId = null;
		this.emailId = null;
		this.value = null;
	}

	public String getUserId() {
		return userId;
	}

	public String getEmailId() {
		return emailId;
	}

	public String getValue() {
		return value;
	}
}
