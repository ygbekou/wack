package com.wack.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wack.domain.JobSearchCriteria;
import com.wack.model.JobPositionDesc;
import com.wack.service.CareerService;


@RestController
@RequestMapping(value="/service/career")
@CrossOrigin
public class CareerController extends BaseController {

		@Autowired
		@Qualifier("careerService")
		CareerService careerService;
	
	
		@RequestMapping(value="/jobPositionDesc/search",method = RequestMethod.POST)
		public List<JobPositionDesc> save(@RequestBody JobSearchCriteria searchCriteria) throws Exception {
				
			List<JobPositionDesc> jobPositionDescs = careerService.getJobPositionDescs(searchCriteria);
			
			return jobPositionDescs;
		}

}
