package com.wack.dao;

import java.util.List;

import com.wack.domain.project.Dashboard;
import com.wack.domain.project.ProjectSearchCriteria;
import com.wack.model.Employee;
import com.wack.model.ProjectDesc;

public interface ProjectDao {
	
	public Dashboard getChart(ProjectSearchCriteria searchCriteria);

	public List<Employee> getProjectEmployees(ProjectSearchCriteria searchCriteria);
	
	public ProjectDesc getProjectDesc(Long long1, String lang);
}
