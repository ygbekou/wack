package com.qkcare.service;

import org.springframework.stereotype.Service;

import com.qkcare.model.BaseEntity;


@Service(value="billingService")
public interface BillingService {
	
	public BaseEntity save(com.qkcare.model.Package pckage);
}
