package com.softenza.emarket.validator;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.javatuples.Pair;
import org.javatuples.Quartet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.softenza.emarket.model.BaseEntity;
import com.softenza.emarket.model.Employee;
import com.softenza.emarket.service.GenericService;

@Component
public class EmployeeCustomValidator {
	
	private static final Logger LOGGER = Logger.getLogger(EmployeeCustomValidator.class);
	
	@Autowired
	GenericService genericService;
	
	
	public Pair<Boolean, List<String>> validate(final BaseEntity toBeValidate) {
		
		Employee emp = (Employee)toBeValidate;

		List<Quartet<String, String, String, String>> paramTupleList = new ArrayList<Quartet<String, String, String, String>>();
		
		
		if (emp.getEmail() != null) {
			paramTupleList.add(Quartet.with("e.user.email = ", "email", emp.getEmail() + "", "String"));
		}
		
		
		String queryStr =  "SELECT e FROM Employee e WHERE 1 = 1";
		List<Employee> employees = (List)this.genericService.getByCriteria(queryStr, paramTupleList, null);
		
		for (Employee employee : employees) {
			if (employee.getId() != toBeValidate.getId()) {
				
				LOGGER.info(String.format("This email is taken already"));
				return new Pair(false, Arrays.asList("EMAIL_EXISTS"));
			} 
		}
		
		
		paramTupleList.clear();
		
		if (emp.getUser().getUserName() != null) {
			paramTupleList.add(Quartet.with("e.user.userName = ", "userName", emp.getUser().getUserName() + "", "String"));
		}
		
		employees = (List)this.genericService.getByCriteria(queryStr, paramTupleList, null);
		
		for (Employee employee : employees) {
			if (employee.getId() != toBeValidate.getId()) {
				
				LOGGER.info(String.format("USER_ERR_002"));
				return new Pair(false, Arrays.asList("USER_NAME_EXISTS"));
			} 
		}
		
		return new Pair(true, new ArrayList());
		
	}
	
}
