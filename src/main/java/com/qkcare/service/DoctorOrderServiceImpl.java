package com.qkcare.service;



import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qkcare.model.BaseEntity;
import com.qkcare.model.DoctorOrder;
import com.qkcare.model.Investigation;
import com.qkcare.model.LabTest;
import com.qkcare.model.enums.DoctorOrderTypeEnum;
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
		return this.save(doctorOrder, false);
	}
	
	@Transactional
	public BaseEntity save(DoctorOrder doctorOrder, boolean notChildInclude) {
		DoctorOrder docOrder = (DoctorOrder)this.genericService.save(doctorOrder);
		
		if (!notChildInclude) {
			if (doctorOrder.getDoctorOrderTypeEnum() == DoctorOrderTypeEnum.LABORATORY) {
				for (LabTest labTest : doctorOrder.getLabTests()) {
					this.investigationService.save(new Investigation(doctorOrder, labTest));
				}
			}
			
			if (doctorOrder.getDoctorOrderTypeEnum() == DoctorOrderTypeEnum.PHARMACY) {
				this.purchasingService.save(new PatientSale(doctorOrder));
			}
		}
		return docOrder;
		
	}

}
