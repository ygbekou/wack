package com.wack.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wack.dao.CareerDao;
import com.wack.domain.JobSearchCriteria;
import com.wack.model.BaseEntity;
import com.wack.model.JobPositionDesc;
import com.wack.model.stock.ContractLabor;
import com.wack.model.stock.Material;
import com.wack.model.stock.Quote;


@Service(value="careerService")
public class CareerServiceImpl  implements CareerService {
	
	@Autowired
	GenericService genericService;
	
	@Autowired
	CareerDao careerDao;
	
	@Autowired
	@Qualifier("myMailSender")
	MyMailSender mailSender;
	
	
	public List<JobPositionDesc> getJobPositionDescs(JobSearchCriteria searchCriteria) {
		return careerDao.getJobPositionDescs(searchCriteria);
	}
}
