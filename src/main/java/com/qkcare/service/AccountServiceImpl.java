package com.qkcare.service;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qkcare.dao.AppointmentDao;
import com.qkcare.model.BaseEntity;
import com.qkcare.model.Invoice;
import com.qkcare.model.InvoiceAccount;

@Service(value="accountService")
public class AccountServiceImpl  implements AccountService {
	
	@Autowired
	GenericService genericService;
	
	@Autowired
	AppointmentDao appointmentDao;
	
	@Transactional
	public BaseEntity save(Invoice invoice) {
		
		BaseEntity toReturn = this.genericService.save(invoice);
		
		for (InvoiceAccount ia : invoice.getInvoiceAccounts()) {
			ia.setInvoice((Invoice)toReturn);
			this.genericService.save(ia);
		}
		
		return toReturn;
	}
}
