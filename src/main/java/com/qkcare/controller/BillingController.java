package com.qkcare.controller;


import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qkcare.domain.GenericDto;
import com.qkcare.model.BaseEntity;
import com.qkcare.model.Bill;
import com.qkcare.model.BillService;
import com.qkcare.model.PackageService;
import com.qkcare.service.BillingService;
import com.qkcare.service.GenericService;
import com.qkcare.util.Constants;


@RestController
@RequestMapping(value="/service/billing")
@CrossOrigin
public class BillingController {
	
		private static final Logger LOGGER = Logger.getLogger(BillingController.class);
	
		@Autowired 
		@Qualifier("billingService")
		BillingService billingService;
		
		@Autowired
		@Qualifier("genericService")
		GenericService genericService;
				
		@RequestMapping(value="/package/save",method = RequestMethod.POST)
		public BaseEntity save(@RequestBody GenericDto dto) throws JsonParseException, 
		JsonMappingException, IOException, ClassNotFoundException {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			BaseEntity obj = (BaseEntity) mapper.readValue(dto.getJson().replaceAll("'", "\"").replaceAll("/", "\\/"),
					Class.forName(Constants.PACKAGE_NAME + "Package"));
			billingService.save((com.qkcare.model.Package)obj);
			
			for (PackageService ps : ((com.qkcare.model.Package)obj).getPackageServices()) {
				ps.setPckage(null);
			}
			
			return obj;
		}
		
		@RequestMapping(value="/bill/save",method = RequestMethod.POST)
		public BaseEntity saveBill(@RequestBody GenericDto dto) throws JsonParseException, 
		JsonMappingException, IOException, ClassNotFoundException {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			BaseEntity obj = (BaseEntity) mapper.readValue(dto.getJson().replaceAll("'", "\"").replaceAll("/", "\\/"),
					Class.forName(Constants.PACKAGE_NAME + "Bill"));
			billingService.save((Bill)obj);
			
			for (BillService bs : ((Bill)obj).getBillServices()) {
				bs.setBill(null);
			}
			
			return obj;
		}
		
		@RequestMapping(value="bill/{id}",method = RequestMethod.GET)
		public BaseEntity getBill(@PathVariable("id") Long id) throws ClassNotFoundException {
			BaseEntity result = billingService.findBill(Class.forName(Constants.PACKAGE_NAME + "Bill"), id);
			
			return result;
		}
		
		@RequestMapping(value="bill/itemNumber/{itemNumber}",method = RequestMethod.GET)
		public BaseEntity getBillByItemNumber(@PathVariable("itemNumber") String itemNumber) {
			BaseEntity result = billingService.findBillInitial(itemNumber);
			
			return result;
		}
}