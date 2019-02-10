package com.wack.controller;

import java.io.IOException;
import java.util.Arrays;

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

import com.wack.domain.GenericDto;
import com.wack.domain.GenericResponse;
import com.wack.model.BaseEntity;
import com.wack.model.User;
import com.wack.service.UserService;
import com.wack.util.Constants;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@RequestMapping(value="/service/user/{entity}")
@CrossOrigin
public class UserController extends BaseController {
	
		private static final Logger LOGGER = Logger.getLogger(UserController.class);
	
		@Autowired 
		@Qualifier("userService")
		UserService userService;
		
				
		@RequestMapping(value="/save",method = RequestMethod.POST)
		public BaseEntity save(@PathVariable("entity") String entity, 
				@RequestPart("file") MultipartFile file, @RequestPart GenericDto dto) throws JsonParseException, 
		JsonMappingException, IOException, ClassNotFoundException {
			BaseEntity obj = null;
			try {
				ObjectMapper mapper = new ObjectMapper();
				mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				mapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
				obj = (BaseEntity) mapper.readValue(dto.getJson().replaceAll("'", "\"")
						.replaceAll("/", "\\/").replaceAll("&#039;", "'"),
						Class.forName(Constants.PACKAGE_NAME + entity));
				userService.save(obj, file);
			}
			catch(Exception e) {
				e.printStackTrace();
				obj.setErrors(Arrays.asList(e.getMessage()));
			}
			return obj;
		}
		
		@RequestMapping(value="/saveWithoutPicture",method = RequestMethod.POST)
		public BaseEntity saveWithoutPicture(@PathVariable("entity") String entity,
				@RequestBody GenericDto dto) throws JsonParseException, 
		JsonMappingException, IOException, ClassNotFoundException {
			BaseEntity obj = null;
			try {
				ObjectMapper mapper = new ObjectMapper();
				mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				mapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
				obj = (BaseEntity) mapper.readValue(dto.getJson().replaceAll("'", "\"")
						.replaceAll("/", "\\/").replaceAll("&#039;", "'"),
						Class.forName(Constants.PACKAGE_NAME + entity));
				userService.save(obj, null);
			}
			catch(Exception e) {
				e.printStackTrace();
				obj.setErrors(Arrays.asList(e.getMessage()));
			}
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
		
		@RequestMapping(value = "/sendPassword", method = RequestMethod.POST)
		public @ResponseBody GenericResponse sendPassword(@PathVariable("entity") String entity, @RequestBody User user) {

			if (user == null || (user.getUserName() == null)) {
				return new GenericResponse("Failure");
			}

			return new GenericResponse(this.userService.sendPassword(user, null));
		}
		
		@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
		public @ResponseBody GenericResponse changePassword(@PathVariable("entity") String entity, @RequestBody User user) {

			if (user == null || (user.getUserName() == null)) {
				return new GenericResponse("Failure");
			}

			return new GenericResponse(this.userService.changePassword(user, user.getPassword()));
		}

}
