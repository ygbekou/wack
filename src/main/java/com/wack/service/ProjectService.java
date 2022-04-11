package com.wack.service;

import org.springframework.stereotype.Service;

import com.wack.domain.project.Dashboard;
import com.wack.domain.project.ProjectSearchCriteria;

@Service(value="projectService")
public interface ProjectService {
	
	public Dashboard getChart(ProjectSearchCriteria searchCriteria);
}
