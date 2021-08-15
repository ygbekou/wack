package com.wack.domain;


public class PaymentResponse {
	private String clientSecret;
	
	private String errorCode;
    
    public PaymentResponse() {
    	
    }
    
    public PaymentResponse(String clientSecret) {
    	this.clientSecret = clientSecret;
    }

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	
    
}
