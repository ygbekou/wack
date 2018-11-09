package com.qkcare.validator;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.javatuples.Pair;
import org.javatuples.Quartet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qkcare.model.BaseEntity;
import com.qkcare.model.Schedule;
import com.qkcare.service.GenericService;

@Component
public class ScheduleCustomValidator {
	
	private static final Logger LOGGER = Logger.getLogger(ScheduleCustomValidator.class);
	
	@Autowired
	GenericService genericService;
	
	
	public Pair<Boolean, List<String>> validate(final BaseEntity toBeValidate) {
		
		Schedule sched = (Schedule)toBeValidate;

		List<Quartet<String, String, String, String>> paramTupleList = new ArrayList<Quartet<String, String, String, String>>();
		
		if (sched.getDoctor() != null) {
			paramTupleList.add(Quartet.with("e.doctor.id = ", "doctorId", sched.getDoctor().getId() + "", "Long"));
		}
		if (sched.getWeekday() != null) {
			paramTupleList.add(Quartet.with("e.weekday.id = ", "weekdayId", sched.getWeekday().getId() + "", "Long"));
		}
		
		String queryStr =  "SELECT e FROM Schedule e WHERE 1 = 1";
		List<Schedule> schedules = (List)this.genericService.getByCriteria(queryStr, paramTupleList, null);
		
		for (Schedule schedule : schedules) {
			if (schedule.getId() != toBeValidate.getId()) {
				if (schedule.getWeekday().getId() == sched.getWeekday().getId()) {
					if (	(schedule.getBeginDateTime().compareTo(sched.getBeginDateTime()) != 0
							&& schedule.getEndDateTime().compareTo(sched.getEndDateTime()) != 0)
							
						&& ((schedule.getBeginDateTime().compareTo(sched.getBeginDateTime()) < 0
								&& schedule.getEndDateTime().compareTo(sched.getEndDateTime()) < 0)
						
						|| (schedule.getBeginDateTime().compareTo(sched.getBeginDateTime()) > 0
								&& schedule.getEndDateTime().compareTo(sched.getEndDateTime()) > 0))
						
						){
						LOGGER.info(String.format("No Overlapping schedules"));
					} else {
						LOGGER.info(String.format("Overlapping schedules {0} - {1}", sched, schedule));
						return new Pair(false, Arrays.asList("OVERLAP_SCHEDULE"));
					}
				}
			}
		}
		return new Pair(true, new ArrayList());
		
	}
	
}
