package com.qkcare.service;



import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qkcare.model.BaseEntity;
import com.qkcare.model.DoctorOrder;
import com.qkcare.model.Investigation;
import com.qkcare.model.LabTest;
import com.qkcare.model.stocks.PatientSale;

@Service(value="doctorOrderService")
public class DoctorOrderServiceImpl  implements DoctorOrderService {
	
	@Autowired
	GenericService genericService;
	
	@Autowired
	InvestigationService investigationService;
	
	@Autowired
	PurchasingService purchasingService;
	
	@Transactional
	public BaseEntity save(DoctorOrder doctorOrder) {
		DoctorOrder docOrder = (DoctorOrder)this.genericService.save(doctorOrder);
		
		for (LabTest labTest : doctorOrder.getLabTests()) {
			this.investigationService.save(new Investigation(doctorOrder, labTest));
		}
		
		this.purchasingService.save(new PatientSale(doctorOrder));
		
		return docOrder;
		
	}

}
