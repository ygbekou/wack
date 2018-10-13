package com.qkcare.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qkcare.domain.ScheduleEvent;
import com.qkcare.domain.SearchCriteria;

@SuppressWarnings("unchecked")
@Repository
public class AppointmentDaoImpl implements AppointmentDao {
	private static Logger logger = Logger.getLogger(UserDaoImpl.class) ;
	
	@Autowired
	private EntityManager entityManager;

	public List<ScheduleEvent> getScheduleEvents(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		List<ScheduleEvent> events = new ArrayList<ScheduleEvent>();
		try {
			StringBuilder sqlBuilder = new StringBuilder("SELECT AP.APPOINTMENT_ID, U.FIRST_NAME, U.MIDDLE_NAME, "
					+ "U.LAST_NAME, AP.APPOINTMENT_DATE, AP.BEGIN_TIME, AP.END_TIME "
					+ "FROM APPOINTMENT AP " 
					+ "JOIN PATIENT P ON AP.PATIENT_ID = P.PATIENT_ID "
					+ "JOIN USERS U ON P.USER_ID = U.USER_ID "
					+ "WHERE 1 = 1 ");
			
			
			if (searchCriteria.hasDoctorId()) {
				sqlBuilder.append(" AND AP.DOCTOR_ID = :doctorId ");
			}
			if (searchCriteria.hasHospitalLocationId()) {
				sqlBuilder.append(" AND AP.HOSPITAL_LOCATION_ID = :locationId ");
			}
			if (searchCriteria.hasDepartmentId()) {
				sqlBuilder.append(" AND AP.DEPARTMENT_ID = :departmentId ");
			}
			
			Query query = entityManager.createNativeQuery(sqlBuilder.toString());
			
			if (searchCriteria.hasDoctorId()) {
				query.setParameter("doctorId", searchCriteria.getDoctor().getId());
			}
			if (searchCriteria.hasDepartmentId()) {
				query.setParameter("departmentId", searchCriteria.getDepartment().getId());
			}
			if (searchCriteria.hasHospitalLocationId()) {
				query.setParameter("locationId", searchCriteria.getHospitalLocation().getId());
			}
			
			List<Object[]> list = query.getResultList();
		

			for (Object[] obj : list) {
				ScheduleEvent event = new ScheduleEvent();
				event.setId(new Long(obj[0].toString()));
				event.setTitle((String) obj[1] + " " + (String) obj[2] + " " + (String) obj[3]);
				event.setStart(obj[4].toString().split(" ")[0] + "T" + obj[5].toString());
				event.setEnd(obj[4].toString().split(" ")[0] + "T" + obj[6].toString());
				event.setClassName("availability");
				events.add(event);
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return events;
		}

	}

}
