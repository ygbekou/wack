package com.wack.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wack.domain.JobSearchCriteria;
import com.wack.model.JobPositionDesc;

@Service(value="careerService")
public interface CareerService {
	
	List<JobPositionDesc> getJobPositionDescs(JobSearchCriteria searchCriteria);
}
