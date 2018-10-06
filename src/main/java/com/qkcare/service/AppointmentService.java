package com.qkcare.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qkcare.domain.ScheduleEvent;
import com.qkcare.domain.SearchCriteria;
import com.qkcare.model.BaseEntity;
import com.qkcare.model.Prescription;


@Service(value="appointmentService")
public interface AppointmentService {
	
	public List<ScheduleEvent> getScheduleEvents(SearchCriteria searchCriteria);
	
	public BaseEntity save(Prescription prescription);
}
