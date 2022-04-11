package com.wack.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.wack.dao.ProjectDao;
import com.wack.domain.project.Dashboard;
import com.wack.domain.project.ProjectSearchCriteria;


@Service(value="projectService")
public class ProjectServiceImpl  implements ProjectService {
	
	@Autowired
	GenericService genericService;
	
	@Autowired
	ProjectDao projectDao;
	
	@Autowired
	@Qualifier("myMailSender")
	MyMailSender mailSender;
	
	
	public Dashboard getChart(ProjectSearchCriteria searchCriteria) {
		return projectDao.getChart(searchCriteria);
	}
}
