package com.qkcare.service;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Transient;
import javax.transaction.Transactional;

import org.javatuples.Quartet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qkcare.model.BaseEntity;
import com.qkcare.model.Bill;
import com.qkcare.model.BillPayment;
import com.qkcare.model.BillService;
import com.qkcare.model.DoctorOrder;
import com.qkcare.model.Investigation;
import com.qkcare.model.InvestigationTest;
import com.qkcare.model.LabTest;
import com.qkcare.model.PackageService;
import com.qkcare.model.Visit;

@Service(value="investigationService")
public class InvestigationServiceImpl  implements InvestigationService {
	
	@Autowired
	GenericService genericService;
	
	@Transactional
	public BaseEntity save(Investigation investigation) {
		boolean isAddition = investigation.getId() == null;
		
		BaseEntity toReturn = this.genericService.save(investigation);
		
		if (isAddition) {
			// Initial save 
			List<Quartet<String, String, String, String>> paramTupleList = new ArrayList<Quartet<String, String, String, String>>();
			paramTupleList.add(Quartet.with("e.parent.id = ", "parentId", investigation.getLabTest().getId() + "", "Long"));
			String queryStr =  "SELECT e FROM LabTest e WHERE 1 = 1 ";
			List<BaseEntity> labTests = genericService.getByCriteria(queryStr, paramTupleList, null);
			
			for (BaseEntity lt : labTests) {
				InvestigationTest investigationTest = new InvestigationTest();
				investigationTest.setLabTest((LabTest)lt);
				investigationTest.setInvestigation((Investigation)toReturn);
				this.genericService.save(investigationTest);
			}
		} 
//		else {
//			for (InvestigationTest investigationTest : investigation.getInvestigationTests()) {
//				this.genericService.save(investigationTest);
//			}
//		}
		return toReturn;
	}
	
	
	@Transactional
	public void saveInvestigationTests(List<InvestigationTest> investigationTests) {
		
		// Get 
		for (InvestigationTest investigationTest : investigationTests) {
			this.genericService.save(investigationTest);
		}
		
	}
	
}
