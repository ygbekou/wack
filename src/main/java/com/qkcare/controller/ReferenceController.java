package com.qkcare.controller;


import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qkcare.domain.GenericVO;
import com.qkcare.service.GenericService;
import com.qkcare.util.CacheUtil;

import net.sf.ehcache.Element;


@RestController
@RequestMapping(value="/service/reference")
@CrossOrigin
public class ReferenceController extends BaseController {
	
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
