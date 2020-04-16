package com.wack.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.wack.model.BaseEntity;
import com.wack.model.Company;
import com.wack.model.stock.ContractLabor;
import com.wack.model.stock.Payment;


@Service(value="paymentService")
public class PaymentServiceImpl  implements PaymentService {
	
	@Autowired
	GenericService genericService;
	
	@Autowired
	@Qualifier("myMailSender")
	MyMailSender mailSender;
	
	
	@Transactional
	public BaseEntity save(Payment payment) throws Exception {		
		Boolean isNew = payment.getId() == null;
		
		Payment pymt = (Payment) genericService.save(payment);
		
		if (isNew && payment.getContractLabor() != null) {
			ContractLabor contractLabor = (ContractLabor) genericService.find(ContractLabor.class, payment.getContractLabor().getId());
			
			if (contractLabor.getBalance() < pymt.getAmount()) {
				throw new Exception(String.format("Payment amount %s is greater than Labor balance %s", pymt.getAmount(), contractLabor.getBalance()));
			}
			
			contractLabor.setPaid(contractLabor.getPaid() + pymt.getAmount() );
			contractLabor.setBalance(contractLabor.getBalance() - pymt.getAmount() );
			
			genericService.save(contractLabor);
		
			Company company = this.genericService.getCompany("EN");
			
			String emailMessage = String.format("Payment of %s was made to %s labor", pymt.getAmount(), contractLabor.getLabel());
			
//			mailSender.sendMail(company.getFromEmail(), company.getToEmail().split(","), 
//					"Payment Message from the system", emailMessage);
		}
		
		return pymt;
		
	}

}
