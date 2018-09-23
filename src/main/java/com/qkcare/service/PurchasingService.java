package com.qkcare.service;

import java.text.ParseException;

import org.springframework.stereotype.Service;

import com.qkcare.model.BaseEntity;
import com.qkcare.model.stocks.PurchaseOrder;
import com.qkcare.model.stocks.ReceiveOrder;


@Service(value="purchasingService")
public interface PurchasingService {
	
	public BaseEntity save(PurchaseOrder purchaseOrder);
	
	public BaseEntity findPurchaseOrder(Class cl, Long key);
	
	public BaseEntity save(ReceiveOrder receiveOrder);
	
	public BaseEntity findInitialReceiveOrder(Class cl, Long key) throws NumberFormatException, ParseException;
}
