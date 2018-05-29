package com.qkcare.controller;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.security.SecureRandom;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.qkcare.domain.GenericDto;
import com.qkcare.model.BaseEntity;
import com.qkcare.model.User;
import com.qkcare.service.GenericService;
import com.qkcare.service.UserService;
import com.qkcare.util.Constants;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@RequestMapping(value="/service/user/{entity}")
@CrossOrigin
public class UserController {
	
		private static final Logger LOGGER = Logger.getLogger(UserController.class);
	
		@Autowired 
		@Qualifier("userService")
		UserService userService;
				
		@RequestMapping(value="/save",method = RequestMethod.POST)
		public BaseEntity save(@PathVariable("entity") String entity, 
				@RequestPart("file") MultipartFile file, @RequestPart GenericDto dto) throws JsonParseException, 
		JsonMappingException, IOException, ClassNotFoundException {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			BaseEntity obj = (BaseEntity) mapper.readValue(dto.getJson().replaceAll("'", "\"").replaceAll("/", "\\/"),
					Class.forName(Constants.PACKAGE_NAME + entity));
			userService.save(obj, file);
			
			
			
			return obj;
		}
		
		
		@RequestMapping(value = "/login", method = RequestMethod.POST, headers = "Accept=application/json")
		public @ResponseBody User login(@RequestBody User user) {
			LOGGER.info("User Login :" + user);
			if (user.getEmail() != null && !user.getEmail().contains("@")) {
				user.setUserName(user.getEmail());
			}
			user = userService.getUser(user.getEmail(), user.getUserName(), user.getPassword());

			if (user != null) {
				return user;
			}
			return new User();

		}
}
