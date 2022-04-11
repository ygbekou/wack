package com.wack.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wack.domain.JobSearchCriteria;
import com.wack.domain.project.Dashboard;
import com.wack.domain.project.ProjectSearchCriteria;
import com.wack.model.JobPositionDesc;
import com.wack.service.CareerService;
import com.wack.service.ProjectService;


@RestController
@RequestMapping(value="/service/project")
@CrossOrigin
public class ProjectController extends BaseController {

		@Autowired
		@Qualifier("projectService")
		ProjectService projectService;
	
	
		@RequestMapping(value = "getChart", method = RequestMethod.POST)
		public ArrayList<Dashboard> getChart(@RequestBody ProjectSearchCriteria searchCriteria) {
			ArrayList<Dashboard> retVal = new ArrayList<Dashboard>();
			try {
				retVal.add(projectService.getChart(searchCriteria));
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			return retVal;
		}

}
