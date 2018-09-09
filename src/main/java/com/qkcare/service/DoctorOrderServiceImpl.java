package com.qkcare.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.hibernate.hql.internal.ast.util.NodeTraverser.VisitationStrategy;
import org.javatuples.Quartet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qkcare.domain.GenericVO;
import com.qkcare.model.Allergy;
import com.qkcare.model.BaseEntity;
import com.qkcare.model.Category;
import com.qkcare.model.DoctorOrder;
import com.qkcare.model.Investigation;
import com.qkcare.model.LabTest;
import com.qkcare.model.MedicalHistory;
import com.qkcare.model.Prescription;
import com.qkcare.model.PrescriptionDiagnosis;
import com.qkcare.model.PrescriptionMedicine;
import com.qkcare.model.SocialHistory;
import com.qkcare.model.Vaccine;
import com.qkcare.model.Visit;
import com.qkcare.model.VisitAllergy;
import com.qkcare.model.VisitMedicalHistory;
import com.qkcare.model.VisitSocialHistory;
import com.qkcare.model.VisitSymptom;
import com.qkcare.model.VisitVaccine;
import com.qkcare.model.VitalSign;

@Service(value="doctorOrderService")
public class DoctorOrderServiceImpl  implements DoctorOrderService {
	
	@Autowired
	GenericService genericService;
	
	@Autowired
	InvestigationService investigationService;
	
	@Transactional
	public BaseEntity save(DoctorOrder doctorOrder) {
		DoctorOrder docOrder = (DoctorOrder)this.genericService.save(doctorOrder);
		
		for (LabTest labTest : doctorOrder.getLabTests()) {
			Investigation investigation = new Investigation();
			investigation.setAdmission(doctorOrder.getAdmission());
			investigation.setVisit(doctorOrder.getVisit());
			investigation.setName(labTest.getName());
			investigation.setLabTest(labTest);
			investigation.setInvestigationDatetime(doctorOrder.getDoctorOrderDatetime());
			this.investigationService.save(investigation);
		}
		return docOrder;
		
	}

}
