package com.wack.service;

import org.springframework.stereotype.Service;

import com.wack.model.BaseEntity;
import com.wack.model.ContactUsMessage;
import com.wack.model.stock.Payment;

@Service(value="paymentService")
public interface PaymentService {
	
	public BaseEntity save(Payment payment) throws Exception;
}
