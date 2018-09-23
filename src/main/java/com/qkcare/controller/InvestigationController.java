package com.qkcare.controller;


import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qkcare.domain.GenericDto;
import com.qkcare.model.BaseEntity;
import com.qkcare.model.Bill;
import com.qkcare.model.BillService;
import com.qkcare.model.InvestigationTest;
import com.qkcare.model.PackageService;
import com.qkcare.service.BillingService;
import com.qkcare.service.GenericService;
import com.qkcare.service.InvestigationService;
import com.qkcare.util.Constants;


@RestController
@RequestMapping(value="/service/laboratory")
@CrossOrigin
public class InvestigationController extends BaseController {
	
		private static final Logger LOGGER = Logger.getLogger(InvestigationController.class);
	
		@Autowired 
		@Qualifier("investigationService")
		InvestigationService investigationService;
		
		@Autowired
		@Qualifier("genericService")
		GenericService genericService;
				
		@RequestMapping(value="/investigation/save",method = RequestMethod.POST)
		public BaseEntity save(@RequestBody GenericDto dto) throws JsonParseException, 
		JsonMappingException, IOException, ClassNotFoundException {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			BaseEntity obj = (BaseEntity) mapper.readValue(dto.getJson().replaceAll("'", "\"").replaceAll("/", "\\/"),
					Class.forName(Constants.PACKAGE_NAME + "Investigation"));
			investigationService.save((com.qkcare.model.Investigation)obj);
			
			
			return obj;
		}
		
		@RequestMapping(value="/investigationTest/list/save",method = RequestMethod.POST)
		public Map saveInvestigationTests(@RequestBody List<InvestigationTest> investigationTests) throws JsonParseException, 
		JsonMappingException, IOException, ClassNotFoundException {
			investigationService.saveInvestigationTests(investigationTests);
			
			return Collections.singletonMap("response", "SUCCESS");
		}
		
}
