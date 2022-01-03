package com.wack.domain;

public class PaygateglobalResponseEntity {

	private String payment_reference;
	private String tx_reference;
	private String status;
	private String datetime;
	private String payment_method;
	
	
	private PaygateglobalResponseEntity() {}

	public String getTx_reference() {
		return tx_reference;
	}

	public void setTx_reference(String tx_reference) {
		this.tx_reference = tx_reference;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
	
	
}
