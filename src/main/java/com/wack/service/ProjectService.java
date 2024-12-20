package com.wack.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wack.domain.project.Dashboard;
import com.wack.domain.project.ProjectSearchCriteria;
import com.wack.model.Employee;

@Service(value="projectService")
public interface ProjectService {
	
	public Dashboard getChart(ProjectSearchCriteria searchCriteria);
	
	public List<Employee> getProjectEmployees(ProjectSearchCriteria searchCriteria);
}
