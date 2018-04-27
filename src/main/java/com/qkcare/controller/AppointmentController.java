package com.qkcare.controller;


import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qkcare.domain.ScheduleEvent;
import com.qkcare.service.AppointmentService;


@RestController
@RequestMapping(value="/service/appointment")
@CrossOrigin
public class AppointmentController {
	
		private static final Logger LOGGER = Logger.getLogger(AppointmentController.class);
	
		@Autowired 
		@Qualifier("appointmentService")
		AppointmentService appointmentService;
				
		@RequestMapping(value="department/{departmentId}/doctor/{doctorId}",method = RequestMethod.GET)
		public List<ScheduleEvent> get(@PathVariable("departmentId") Long departmentId, @PathVariable("doctorId") Long doctorId) throws ClassNotFoundException{
			List<ScheduleEvent> result = appointmentService.getScheduleEvents(departmentId, doctorId);
			return result;
		}
		
}
