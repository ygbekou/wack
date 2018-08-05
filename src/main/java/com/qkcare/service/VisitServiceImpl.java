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
import com.qkcare.model.Prescription;
import com.qkcare.model.PrescriptionDiagnosis;
import com.qkcare.model.PrescriptionMedicine;
import com.qkcare.model.Vaccine;
import com.qkcare.model.Visit;
import com.qkcare.model.VisitAllergy;
import com.qkcare.model.VitalSign;

@Service(value="visitService")
public class VisitServiceImpl  implements VisitService {
	
	@Autowired
	GenericService genericService;
	
	@Transactional
	public BaseEntity save(Visit visit) {
		
		Visit v = (Visit)this.genericService.save(visit);
		
		VitalSign vitalSign = visit.getVitalSign();
		vitalSign.setVisit(visit);
		this.genericService.save(vitalSign);
		vitalSign.setVisit(null);
		visit.setVitalSign(vitalSign);
		
		// save allergies 
		List<Quartet<String, String, String, String>> paramTupleList = new ArrayList<Quartet<String, String, String, String>>();
		paramTupleList.add(Quartet.with("VISIT_ID = ", "visitId", v.getId() + "", "Long"));
		List<Object[]> list = this.genericService.getNativeByCriteria("SELECT ALLERGY_ID FROM VISIT_ALLERGY WHERE 1 = 1 ", paramTupleList, null);
		Set<Long> existingAllergyIds = new HashSet<Long>();
		
		for (Object object : list) {
			existingAllergyIds.add(new Long(object.toString()));
		}
		
		// Find differences in both list
		List<Long> removedIds = existingAllergyIds.stream().filter(aObject -> {
		     return !visit.getAllergies().contains(aObject);
		 }).collect(Collectors.toList());
		
		List<Long> addedIds = visit.getAllergies().stream().filter(aObject -> {
		     return !existingAllergyIds.contains(aObject);
		 }).collect(Collectors.toList());

		// delete allergies 
		if (removedIds.size() > 0) {
			paramTupleList = new ArrayList<Quartet<String, String, String, String>>();
			paramTupleList.add(Quartet.with("e.allergy.id IN ", "allergyId", 
					removedIds.toString().substring(1, removedIds.toString().length() - 1) + "", "List"));
			int deleteds = this.genericService.deleteByCriteria("DELETE FROM VisitAllergy e WHERE 1 = 1 ", paramTupleList);
		}
		
		// Insert newly added ones
		for (Long addedId : addedIds) {
			VisitAllergy va = new VisitAllergy(visit.getId(), addedId);
			this.genericService.save(va);
		}
		
		
		return v;
	}
	
	
	public BaseEntity findVisit(Class cl, Long key) {
		Visit visit = (Visit) this.genericService.find(cl, key);
		
		List<Quartet<String, String, String, String>> paramTupleList = new ArrayList<Quartet<String, String, String, String>>();
		paramTupleList.add(Quartet.with("e.visit.id = ", "visitId", key + "", "Long"));
		String queryStr =  "SELECT e FROM Visit e WHERE 1 = 1 ";
		List<BaseEntity> vitalSigns = genericService.getByCriteria(queryStr, paramTupleList, " ");
		
		for (BaseEntity entity : vitalSigns) {
			VitalSign vitalSign = (VitalSign)entity;
			vitalSign.setVisit(null);
			visit.setVitalSign(vitalSign);
			break;
		}
		
		return visit;
		
	}
	
	public List<BaseEntity> getVisitChilds(String child, Long visitId) {
		List<Quartet<String, String, String, String>> paramTupleList = new ArrayList<Quartet<String, String, String, String>>();
		paramTupleList.add(Quartet.with("e.visit.id = ", "visitId", visitId + "", "Long"));
		String queryStr =  "SELECT e FROM " + child + " e WHERE 1 = 1 ";
		List<BaseEntity> childs = genericService.getByCriteria(queryStr, paramTupleList, " ORDER BY e.modDate DESC ");
		
		return childs;
	}
	
	public BaseEntity findPrescription(Class cl, Long key) {
		Prescription prescription = (Prescription) this.genericService.find(cl, key);
		
		List<Quartet<String, String, String, String>> paramTupleList = new ArrayList<Quartet<String, String, String, String>>();
		paramTupleList.add(Quartet.with("e.prescription.id = ", "prescriptionId", key + "", "Long"));
		String queryStr =  "SELECT e FROM PrescriptionMedicine e WHERE 1 = 1 ";
		List<BaseEntity> medicines = genericService.getByCriteria(queryStr, paramTupleList, " ");
		List<PrescriptionMedicine> prescriptionMedicines = new ArrayList<PrescriptionMedicine>();
		
		for (BaseEntity entity : medicines) {
			PrescriptionMedicine prescriptionMedicine = (PrescriptionMedicine)entity;
			prescriptionMedicine.setPrescription(null);
			prescriptionMedicines.add(prescriptionMedicine);
		}
		
		prescription.setPrescriptionMedicines(prescriptionMedicines);
		
		queryStr =  "SELECT e FROM PrescriptionDiagnosis e WHERE 1 = 1 ";
		List<BaseEntity> diagnoses = genericService.getByCriteria(queryStr, paramTupleList, " ");
		List<PrescriptionDiagnosis> prescriptionDiagnoses = new ArrayList<PrescriptionDiagnosis>();
		
		for (BaseEntity entity : diagnoses) {
			PrescriptionDiagnosis prescriptionDiagnosis = (PrescriptionDiagnosis)entity;
			prescriptionDiagnosis.setPrescription(null);
			prescriptionDiagnoses.add(prescriptionDiagnosis);
		}
		
		prescription.setPrescriptionDiagnoses(prescriptionDiagnoses);
		
		return prescription;
		
	}
	
	public Set<GenericVO> getCategoryAllergies() {
		List<Quartet<String, String, String, String>> paramTupleList = new ArrayList<Quartet<String, String, String, String>>();
		String queryStr =  "SELECT e FROM Allergy e WHERE 1 = 1 AND status = 0 ";
		List<BaseEntity> childs = genericService.getByCriteria(queryStr, paramTupleList, " ORDER BY e.name ");
		Map<GenericVO, List<GenericVO>> resultMap = new HashMap<>();
		List<GenericVO> results = new ArrayList<>();
		Map<Long, GenericVO> keyMap = new HashMap<Long, GenericVO>();
		
		for (BaseEntity ch : childs) {
			Allergy allergy = (Allergy)ch;
			if (keyMap.get(allergy.getCategory().getId()) == null) {
				keyMap.put(allergy.getCategory().getId(), 
						new GenericVO(allergy.getCategory().getId(), allergy.getCategory().getName()));
			}
			GenericVO keyVO = keyMap.get(allergy.getCategory().getId());
			if (resultMap.get(keyVO) == null) {
				resultMap.put(keyVO, new ArrayList<>());
			}
			resultMap.get(keyVO).add(new GenericVO(allergy.getId(), allergy.getName()));
		}
		
		for (GenericVO vo : resultMap.keySet()) {
			vo.setChilds(resultMap.get(vo));
		}
		return resultMap.keySet();
	}
	
}
