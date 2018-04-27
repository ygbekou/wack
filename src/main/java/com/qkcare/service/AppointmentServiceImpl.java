package com.qkcare.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qkcare.dao.AppointmentDao;
import com.qkcare.domain.ScheduleEvent;

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
}
