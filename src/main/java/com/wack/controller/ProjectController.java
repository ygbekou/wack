package com.wack.controller;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wack.domain.project.Dashboard;
import com.wack.domain.project.ProjectSearchCriteria;
import com.wack.model.BaseEntity;
import com.wack.model.Company;
import com.wack.model.Employee;
import com.wack.service.GenericService;
import com.wack.service.ProjectService;


@RestController
@RequestMapping(value="/service/project")
@CrossOrigin
public class ProjectController extends BaseController {

		@Autowired
		@Qualifier("projectService")
		ProjectService projectService;
	
	
		@Autowired
		@Qualifier("genericService")
		GenericService genericService;
	
		
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

		
		@RequestMapping(value="/saveCompanyWithFiles",method = RequestMethod.POST)
		public BaseEntity saveCompanyWithFiles(
				@RequestPart("file[]") MultipartFile[] files,
				@RequestPart("dto") Company company) throws Exception {
			

			this.genericService.saveCompany(company, Arrays.asList(files));
			
			return company;
		}
		
		@RequestMapping(value="/saveCompany",method = RequestMethod.POST)
		public BaseEntity saveCompany(
				@RequestBody Company company) throws Exception {

			this.genericService.saveCompany(company, Arrays.asList());
			
			return company;
		}
		
		@RequestMapping(value="/employees",method = RequestMethod.POST)
		public List<Employee> getProjectEmployees(@RequestBody ProjectSearchCriteria searchCriteria) throws Exception {

			return this.projectService.getProjectEmployees(searchCriteria);
		}
}
