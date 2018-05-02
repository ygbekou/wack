package com.qkcare.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qkcare.dao.AppointmentDao;
import com.qkcare.domain.ScheduleEvent;
import com.qkcare.model.BaseEntity;
import com.qkcare.model.Prescription;
import com.qkcare.model.PrescriptionDiagnosis;
import com.qkcare.model.PrescriptionMedicine;

@Service(value="appointmentService")
public class AppointmentServiceImpl  implements AppointmentService {
	
	@Autowired
	GenericService genericService;
	
	@Autowired
	AppointmentDao appointmentDao;
	
	@Transactional
	public List<ScheduleEvent> getScheduleEvents(Long departmentId, Long doctorId) {
		return this.appointmentDao.getScheduleEvents(departmentId, doctorId);
	}
	
	@Transactional
	public BaseEntity save(Prescription prescription) {
		
		BaseEntity toReturn = this.genericService.save(prescription);
		
		for (PrescriptionMedicine pm : prescription.getPrescriptionMedicines()) {
			pm.setPrescription((Prescription)toReturn);
			this.genericService.save(pm);
		}
		
		for (PrescriptionDiagnosis pd : prescription.getPrescriptionDiagnoses()) {
			pd.setPrescription((Prescription)toReturn);
			this.genericService.save(pd);
		}
		
		
		return toReturn;
	}
}
