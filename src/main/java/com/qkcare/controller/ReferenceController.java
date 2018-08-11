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
import com.qkcare.model.Prescription;
import com.qkcare.model.PrescriptionDiagnosis;
import com.qkcare.model.PrescriptionMedicine;
import com.qkcare.model.Visit;
import com.qkcare.model.VisitVaccine;
import com.qkcare.model.VitalSign;
import com.qkcare.model.Admission;
import com.qkcare.model.Allergy;
import com.qkcare.model.enums.TransferType;
import com.qkcare.service.AdmissionService;
import com.qkcare.service.BillingService;
import com.qkcare.service.GenericService;
import com.qkcare.service.VisitService;
import com.qkcare.util.CacheUtil;
import com.qkcare.util.Constants;

import net.sf.ehcache.Element;


@RestController
@RequestMapping(value="/service/reference")
@CrossOrigin
public class ReferenceController {
	
		private static final Logger LOGGER = Logger.getLogger(ReferenceController.class);
	
		@Autowired 
		@Qualifier("genericService")
		GenericService genericService;
		
		@RequestMapping(value="{reference}/all/active",method = RequestMethod.GET)
		public List<GenericVO> getAllActiveReferences(@PathVariable("reference") String reference) throws ClassNotFoundException{
			Element ele = CacheUtil.CACHE_MANAGER.getCache(reference).get("active");
			List<GenericVO> results = (List<GenericVO>) ele.getObjectValue();
			
			return results;
		}
		
}
