package com.wack.dao;

import com.wack.domain.project.Dashboard;
import com.wack.domain.project.ProjectSearchCriteria;

public interface ProjectDao {
	
	public Dashboard getChart(ProjectSearchCriteria searchCriteria);

}
