package com.wack.dao;

import java.util.List;

import com.wack.domain.JobSearchCriteria;
import com.wack.model.JobPositionDesc;

public interface CareerDao {
	
	public List<JobPositionDesc> getJobPositionDescs(JobSearchCriteria searchCriteria);

}
