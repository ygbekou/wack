package com.qkcare.service;

import org.springframework.stereotype.Service;

import com.qkcare.model.BaseEntity;
import com.qkcare.model.Invoice;


@Service(value="accountService")
public interface AccountService {
	
	public BaseEntity save(Invoice invoice);
}
