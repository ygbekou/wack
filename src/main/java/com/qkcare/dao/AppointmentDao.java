package com.qkcare.dao;

import java.util.List;

import com.qkcare.domain.ScheduleEvent;

public interface AppointmentDao {
	public List<ScheduleEvent> getScheduleEvents(Long departmentId, Long doctorId);
}
