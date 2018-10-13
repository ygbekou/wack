package com.qkcare.service;

import java.text.ParseException;

import org.springframework.stereotype.Service;

import com.qkcare.model.BaseEntity;
import com.qkcare.model.stocks.PatientSale;
import com.qkcare.model.stocks.PurchaseOrder;
import com.qkcare.model.stocks.ReceiveOrder;


@Service(value="purchasingService")
public interface PurchasingService {
	
	public BaseEntity save(PurchaseOrder purchaseOrder);
	
	public BaseEntity findPurchaseOrder(Class cl, Long key);
	
	public BaseEntity save(ReceiveOrder receiveOrder);
	
	public BaseEntity findInitialReceiveOrder(Class cl, Long key) throws NumberFormatException, ParseException;
	
	public BaseEntity findReceiveOrder(Class cl, Long key);
	
	public BaseEntity save(PatientSale patientSale);
	
	public BaseEntity findPatientSale(Class cl, Long key);
	
	public BaseEntity findSaleReturn(Class cl, Long key);
	
	public BaseEntity findInitialSaleReturn(Class cl, Long patientSaleId) throws NumberFormatException, ParseException;
}
