package com.wack.domain;

public class PaygateglobalVerificationEntity {

	private String auth_token;
	private String identifier;
	
	private PaygateglobalVerificationEntity() {}
	
	public PaygateglobalVerificationEntity(String auth_token, String identifier) {
		super();
		this.auth_token = auth_token;
		this.identifier = identifier;
	}



	public String getAuth_token() {
		return auth_token;
	}
	public void setAuth_token(String auth_token) {
		this.auth_token = auth_token;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
}
