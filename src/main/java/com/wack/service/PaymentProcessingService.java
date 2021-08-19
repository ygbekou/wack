package com.wack.service;

import javax.transaction.Transactional;

import com.stripe.exception.StripeException;
import com.wack.domain.PaygateglobalConfirmationEntity;
import com.wack.model.Transaction;


public interface PaymentProcessingService {

	
	@Transactional
	public String secret(Transaction transaction) throws StripeException;
	
	@Transactional
	public String retrievePublishableKey();
	
	@Transactional
	public void processPayment(Transaction transaction) throws InterruptedException;
	
	@Transactional
	public Transaction processPaymentConfirmation(PaygateglobalConfirmationEntity confirmationEntity);
	
	@Transactional
	public void setRedirectionPaymentUrl(Transaction transaction);
}
