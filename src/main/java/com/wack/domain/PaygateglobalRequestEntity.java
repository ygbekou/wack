package com.wack.domain;

public class PaygateglobalRequestEntity {

	private String auth_token;
	private String phone_number;
	private String amount;
	private String description;
	private String identifier;
	
	private PaygateglobalRequestEntity() {}
	
	
	
	public PaygateglobalRequestEntity(String auth_token, String phone_number, String amount, String description,
			String identifier) {
		super();
		this.auth_token = auth_token;
		this.phone_number = phone_number;
		this.amount = amount;
		this.description = description;
		this.identifier = identifier;
	}



	public String getAuth_token() {
		return auth_token;
	}
	public void setAuth_token(String auth_token) {
		this.auth_token = auth_token;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
}
