package com.wack.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.wack.domain.GenericResponse;
import com.wack.model.BaseEntity;
import com.wack.model.stock.ContractLabor;
import com.wack.model.stock.Material;
import com.wack.service.QuoteService;


@RestController
@RequestMapping(value="/service/Quote")
@CrossOrigin
public class QuoteController extends BaseController {

		@Autowired
		@Qualifier("quoteService")
		QuoteService quoteService;
	
	
		@RequestMapping(value="/material/save",method = RequestMethod.POST)
		public BaseEntity save(@RequestBody Material material) throws Exception {
				
			BaseEntity mtrial = this.quoteService.save(material);
			material.setQuote(null);
			return mtrial;
		}

		
		@RequestMapping(value="/material/delete/{id}", method = RequestMethod.GET, produces = "application/json")
		public GenericResponse delete(@PathVariable("id") Long id) throws JsonParseException, 
				JsonMappingException, IOException, ClassNotFoundException {
			try {
				List<Long> ids = new ArrayList<>();
				ids.add(id);
				this.quoteService.delete(Material.class, id);
				return new GenericResponse("SUCCESS");
			} catch (Exception e) {
				if (e.getMessage().contains("foreign key") || e.getMessage().contains("ConstraintViolationException")) {
					return new GenericResponse("FOREIGN_KEY_FAILURE", e.getMessage());
				} else {
					return new GenericResponse("GENERIC_FAILURE", e.getMessage());
				}
			}
		}
		
		@RequestMapping(value="/contractlabor/save",method = RequestMethod.POST)
		public BaseEntity save(@RequestPart("file[]") MultipartFile[] files,
				@RequestPart("contractLabor") ContractLabor contractLabor) throws Exception {
				
			BaseEntity contLabor = this.quoteService.save(contractLabor, Arrays.asList(files));
			return contLabor;
		}

		
		@RequestMapping(value="/contractlabor/delete/{id}", method = RequestMethod.GET, produces = "application/json")
		public GenericResponse deleteContractLabor(@PathVariable("id") Long id) throws JsonParseException, 
				JsonMappingException, IOException, ClassNotFoundException {
			try {
				List<Long> ids = new ArrayList<>();
				ids.add(id);
				this.quoteService.deleteContractLabor(ContractLabor.class, id);
				return new GenericResponse("SUCCESS");
			} catch (Exception e) {
				if (e.getMessage().contains("foreign key") || e.getMessage().contains("ConstraintViolationException")) {
					return new GenericResponse("FOREIGN_KEY_FAILURE", e.getMessage());
				} else {
					return new GenericResponse("GENERIC_FAILURE", e.getMessage());
				}
			}
		}

}
