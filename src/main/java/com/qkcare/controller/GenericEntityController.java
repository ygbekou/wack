package com.qkcare.controller;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.javatuples.Quartet;
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

import com.qkcare.domain.GenericDto;
import com.qkcare.model.BaseEntity;
import com.qkcare.model.DoctorOrder;
import com.qkcare.model.User;
import com.qkcare.model.Visit;
import com.qkcare.model.VisitVaccine;
import com.qkcare.model.VitalSign;
import com.qkcare.service.GenericService;
import com.qkcare.util.Constants;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@RequestMapping(value="/service/{entity}")
@CrossOrigin
public class GenericEntityController extends BaseController {

		@Autowired
		@Qualifier("genericService")
		GenericService genericService;
		
		@RequestMapping(value="/{id}",method = RequestMethod.GET)
		public BaseEntity get(@PathVariable("entity") String entity, @PathVariable("id") Long id) throws ClassNotFoundException{
			BaseEntity result = genericService.find(this.getClass(entity), id);
			return result;
		}
		
		@RequestMapping(value="/all",method = RequestMethod.GET)
		public List<BaseEntity> getAll(@PathVariable("entity") String entity) throws ClassNotFoundException{
			 List<BaseEntity> entities = genericService.getAll(this.getClass(entity));
			 System.out.println(entities);
			 return entities;
		}		
		
		@RequestMapping(value="/allByCriteria",method = RequestMethod.POST)
		public List<BaseEntity> getAllByCriteria(@PathVariable("entity") String entity, 
				@RequestBody List<String> parameters) throws ClassNotFoundException{
			
			List<Quartet<String, String, String, String>> paramTupleList = new ArrayList<Quartet<String, String, String, String>>();
			for (String parameter : parameters) {
				String[] paramSplit = parameter.split("\\|");
				paramTupleList.add(Quartet.with(paramSplit[0], paramSplit[1], paramSplit[2], paramSplit[3]));
			}
			
			String queryStr =  "SELECT e FROM " + entity + " e WHERE 1 = 1";
			List<BaseEntity> entities = genericService.getByCriteria(queryStr, paramTupleList, null);
			System.out.println(entities);
			return entities;
		}		

		
		@RequestMapping(value="/save",method = RequestMethod.POST)
		public BaseEntity save(@PathVariable("entity") String entity, @RequestBody GenericDto dto) throws JsonParseException, 
		JsonMappingException, IOException, ClassNotFoundException {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			BaseEntity obj = (BaseEntity) mapper.readValue(dto.getJson().replaceAll("'", "\"").replaceAll("/", "\\/"), 
					this.getClass(entity));
			genericService.save(obj);
			return obj;
		}
		
		@RequestMapping(value="/saveWithFile",method = RequestMethod.POST)
		public BaseEntity saveWithFile(@PathVariable("entity") String entity, 
				@RequestPart("file") MultipartFile file, @RequestPart("dto") GenericDto dto) throws JsonParseException, 
		JsonMappingException, IOException, ClassNotFoundException {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			BaseEntity obj = (BaseEntity) mapper.readValue(dto.getJson().replaceAll("'", "\"").replaceAll("/", "\\/"), 
					this.getClass(entity));
			genericService.save(obj);
			
			if (!file.isEmpty()) {
				try {
					String originalFileExtension = file.getOriginalFilename()
							.substring(file.getOriginalFilename().lastIndexOf("."));

					// transfer to upload folder
					String storageDirectory = null;
					if (entity != null) {					
						storageDirectory = Constants.DOC_FOLDER	+ entity + File.separator;
						File dir = new File(storageDirectory);
						if (!dir.exists()) {
							dir.mkdirs();
						}

					} else {
						throw new Exception("Unknown");
					}
					
					String newFilename = null;
					newFilename = obj.getId() + originalFileExtension;
					

					File newFile = new File(storageDirectory + "/" + newFilename);
					Field field = obj.getClass().getDeclaredField("fileLocation");
					field.setAccessible(true);
			        field.set(obj, newFile.getAbsolutePath());
			        genericService.save(obj);
			        
					file.transferTo(newFile);
			        
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			
					
			return obj;
		}
		
		@RequestMapping(value="/delete",method = RequestMethod.POST)
		public String delete(@PathVariable("entity") String entity, @RequestBody List<Long> ids) throws JsonParseException, 
		JsonMappingException, IOException, ClassNotFoundException {
			this.genericService.delete(this.getClass(entity), ids);
			return "SUCCESS";
		}
		
		@RequestMapping(value="/cascade/delete",method = RequestMethod.POST)
		public String cascadeDelete(@PathVariable("entity") String entity, @RequestBody List<Long> ids) throws JsonParseException, 
		JsonMappingException, IOException, ClassNotFoundException {
			this.genericService.delete(this.getClass(entity), ids);
			return "SUCCESS";
		}
		
}
