package com.qkcare.service;

import org.springframework.stereotype.Service;

import com.qkcare.model.BaseEntity;
import com.qkcare.model.Bill;


@Service(value="billingService")
public interface BillingService {
	
	public BaseEntity save(com.qkcare.model.Package pckage);
	
	public BaseEntity save(Bill bill);
	
	public BaseEntity findBill(Class cl, Long key);
	
	public BaseEntity findBillInitial(String itemNumber);
	
	public BaseEntity findPackage(Class cl, Long key);
}
