package com.wack.domain;

public class PaygateglobalConfirmationEntity {

	private String tx_reference;
	private String identifier;
	private String payment_reference;
	private String amount;
	private String datetime;
	private String payment_method;
	private String phone_number;
	
	
	public PaygateglobalConfirmationEntity() {}

	public String getTx_reference() {
		return tx_reference;
	}

	public void setTx_reference(String tx_reference) {
		this.tx_reference = tx_reference;
	}

	public String getPayment_reference() {
		return payment_reference;
	}

	public void setPayment_reference(String payment_reference) {
		this.payment_reference = payment_reference;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getPayment_method() {
		return payment_method;
	}

	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
}
