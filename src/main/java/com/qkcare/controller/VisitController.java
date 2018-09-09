package com.qkcare.controller;


import java.io.IOException;
import java.util.List;

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
import com.qkcare.model.DoctorOrder;
import com.qkcare.model.Visit;
import com.qkcare.model.VisitVaccine;
import com.qkcare.model.VitalSign;
import com.qkcare.service.DoctorOrderService;
import com.qkcare.service.GenericService;
import com.qkcare.service.VisitService;
import com.qkcare.util.Constants;


@RestController
@RequestMapping(value="/service/visit")
@CrossOrigin
public class VisitController {
	
		private static final Logger LOGGER = Logger.getLogger(VisitController.class);
	
		@Autowired 
		@Qualifier("genericService")
		GenericService genericService;
		
		@Autowired 
		@Qualifier("visitService")
		VisitService visitService;
		
		@Autowired 
		@Qualifier("doctorOrderService")
		DoctorOrderService doctorOrderService;
		
		@RequestMapping(value="/visit/save",method = RequestMethod.POST)
		public BaseEntity saveVisit(@RequestBody GenericDto dto) throws JsonParseException, 
		JsonMappingException, IOException, ClassNotFoundException {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			BaseEntity obj = (BaseEntity) mapper.readValue(dto.getJson().replaceAll("'", "\"").replaceAll("/", "\\/"),
					Class.forName(Constants.PACKAGE_NAME + "Visit"));
			visitService.save((Visit)obj);
			
			VitalSign vs = ((Visit)obj).getVitalSign();
			vs.setVisit(null);
			
			for (VisitVaccine vv : ((Visit)obj).getGivenVaccines()) {
				vv.setVisit(null);
			}
			
			return obj;
		}
		
		@RequestMapping(value="visit/get/{id}",method = RequestMethod.GET)
		public BaseEntity getVisit(@PathVariable("id") Long id) throws ClassNotFoundException{
			BaseEntity result = visitService.findVisit(Class.forName(Constants.PACKAGE_NAME + "Visit"), id);
			
			return result;
		}
		
		@RequestMapping(value="diagnosis/{id}/all",method = RequestMethod.GET)
		public List<BaseEntity> getVisitDiagnoses(@PathVariable("id") Long id) throws ClassNotFoundException{
			List<BaseEntity> results = visitService.getVisitChilds("VisitDiagnosis", id);
			
			return results;
		}
		
		@RequestMapping(value="prescription/{id}/all",method = RequestMethod.GET)
		public List<BaseEntity> getVisitPrescriptions(@PathVariable("id") Long id) throws ClassNotFoundException{
			List<BaseEntity> results = visitService.getVisitChilds("Prescription", id);
			
			return results;
		}
		
		@RequestMapping(value="prescription/get/{id}",method = RequestMethod.GET)
		public BaseEntity getPrescription(@PathVariable("id") Long id) throws ClassNotFoundException{
			BaseEntity result = visitService.findPrescription(Class.forName(Constants.PACKAGE_NAME + "Prescription"), id);
			
			return result;
		}
		
		@RequestMapping(value="/doctororder/save",method = RequestMethod.POST)
		public BaseEntity saveDoctorOrder(@RequestBody GenericDto dto) throws JsonParseException, 
		JsonMappingException, IOException, ClassNotFoundException {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			BaseEntity obj = (BaseEntity) mapper.readValue(dto.getJson().replaceAll("'", "\"").replaceAll("/", "\\/"),
					Class.forName(Constants.PACKAGE_NAME + "DoctorOrder"));
			doctorOrderService.save((DoctorOrder)obj);
			
			return obj;
		}
}
