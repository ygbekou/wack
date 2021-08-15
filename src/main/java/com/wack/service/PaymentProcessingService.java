package com.wack.service;

import javax.transaction.Transactional;

import com.stripe.exception.StripeException;
import com.wack.model.Transaction;


public interface PaymentProcessingService {

	
	@Transactional
	public String secret(Transaction transaction) throws StripeException;
	
	@Transactional
	public String retrievePublishableKey();
}
