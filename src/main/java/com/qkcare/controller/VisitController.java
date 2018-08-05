package com.qkcare.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.qkcare.domain.GenericVO;
import com.qkcare.model.BaseEntity;
import com.qkcare.model.BedAssignment;
import com.qkcare.model.Bill;
import com.qkcare.model.BillService;
import com.qkcare.model.Category;
import com.qkcare.model.DoctorAssignment;
import com.qkcare.model.PackageService;
import com.qkcare.model.Visit;
import com.qkcare.model.VitalSign;
import com.qkcare.model.Admission;
import com.qkcare.model.Allergy;
import com.qkcare.model.enums.TransferType;
import com.qkcare.service.AdmissionService;
import com.qkcare.service.BillingService;
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
			
			return obj;
		}
		
		@RequestMapping(value="visit/get/{id}",method = RequestMethod.GET)
		public BaseEntity getVisit(@PathVariable("id") Long id) throws ClassNotFoundException{
			BaseEntity result = visitService.findVisit(Class.forName(Constants.PACKAGE_NAME + "Visit"), id);
			
			return result;
		}
		
		@RequestMapping(value="diagnosis/{id}/all",method = RequestMethod.GET)
		public List<BaseEntity> getAdmissionDiagnoses(@PathVariable("id") Long id) throws ClassNotFoundException{
			List<BaseEntity> results = visitService.getVisitChilds("AdmissionDiagnosis", id);
			
			return results;
		}
		
		@RequestMapping(value="prescription/{id}/all",method = RequestMethod.GET)
		public List<BaseEntity> getAdmissionPrescriptions(@PathVariable("id") Long id) throws ClassNotFoundException{
			List<BaseEntity> results = visitService.getVisitChilds("Prescription", id);
			
			return results;
		}
		
		@RequestMapping(value="prescription/get/{id}",method = RequestMethod.GET)
		public BaseEntity getPrescription(@PathVariable("id") Long id) throws ClassNotFoundException{
			BaseEntity result = visitService.findPrescription(Class.forName(Constants.PACKAGE_NAME + "Prescription"), id);
			
			return result;
		}
		
		
		@RequestMapping(value="allergy/all/active",method = RequestMethod.GET)
		public Set<GenericVO> getAllActiveAllergies() throws ClassNotFoundException{
			Set<GenericVO> results = visitService.getCategoryAllergies();
			
			return results;
		}
		
}
