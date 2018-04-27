package com.qkcare.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qkcare.domain.ScheduleEvent;


@Service(value="appointmentService")
public interface AppointmentService {
	
	public List<ScheduleEvent> getScheduleEvents(Long departmentId, Long doctorId);
}
