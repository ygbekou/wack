package com.wack.controller;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import com.wack.config.JwtTokenUtil;
import com.wack.domain.MenuVO;
import com.wack.domain.PermissionVO;
import com.wack.service.AuthorizationService;
import com.wack.domain.AuthToken;
import com.wack.domain.LoginUser;
import com.wack.model.authorization.Role;
import com.wack.model.authorization.UserRole;

@RestController
@RequestMapping(value = "/service/user/{entity}")
@CrossOrigin
public class UserController extends BaseController {

	private static final Logger LOGGER = Logger.getLogger(UserController.class);

	@Autowired
	@Qualifier("userService")
	UserService userService;
	@Autowired
	BCryptPasswordEncoder encoder;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private AuthorizationService authorizationService;

	@Autowired
	private ApplicationContext context;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public BaseEntity save(@PathVariable("entity") String entity, @RequestPart("file") MultipartFile file,
			@RequestPart GenericDto dto)
			throws JsonParseException, JsonMappingException, IOException, ClassNotFoundException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
		BaseEntity obj = (BaseEntity) mapper.readValue(
				dto.getJson().replaceAll("'", "\"").replaceAll("/", "\\/").replaceAll("&#039;", "'"),
				Class.forName(Constants.PACKAGE_NAME + entity));

		Pair<Boolean, List<String>> results = Pair.with(true, new ArrayList());
		try {
			Class validator = this.getClass(Constants.VALIDATOR_PACKAGE_NAME + entity + "CustomValidator");
			Method aMethod = validator.getMethod("validate", BaseEntity.class);
			results = (Pair<Boolean, List<String>>) aMethod.invoke(context.getBean(validator), obj);
		} catch (Exception ex) {
			obj.setErrors(Arrays.asList("Exception happened."));
		}

		if (results.getValue0()) {
			this.userService.save(obj, file);
		} else {
			obj.setErrors(results.getValue1());
		}
		return obj;
	}

	@RequestMapping(value = "/saveWithoutPicture", method = RequestMethod.POST)
	public BaseEntity saveWithoutPicture(@PathVariable("entity") String entity, @RequestBody GenericDto dto)
			throws JsonParseException, JsonMappingException, IOException, ClassNotFoundException {

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
		BaseEntity obj = (BaseEntity) mapper.readValue(
				dto.getJson().replaceAll("'", "\"").replaceAll("/", "\\/").replaceAll("&#039;", "'"),
				Class.forName(Constants.PACKAGE_NAME + entity));

		Pair<Boolean, List<String>> results = Pair.with(true, new ArrayList());
		try {
			Class validator = this.getClass(Constants.VALIDATOR_PACKAGE_NAME + entity + "CustomValidator");
			Method aMethod = validator.getMethod("validate", BaseEntity.class);
			results = (Pair<Boolean, List<String>>) aMethod.invoke(context.getBean(validator), obj);
		} catch (Exception ex) {
			obj.setErrors(Arrays.asList("Exception happened."));
		}

		if (results.getValue0()) {
			this.userService.save(obj, null);
		} else {
			obj.setErrors(results.getValue1());
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

	@RequestMapping(value = "/saveUserAndLogin", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<?> saveUserAndLogin(@PathVariable("entity") String entity, @RequestBody User user) {
		LOGGER.info("User Login :" + user);
		try {
			user.setUserName(user.getUserName()==null?user.getEmail():user.getUserName());
			LoginUser lu = new LoginUser();
			lu.setPassword(user.getPassword());
			lu.setUserName(user.getUserName());
			user.setPassword(encoder.encode(user.getPassword()));
			user.setFirstTimeLogin("N");
			userService.save(user);
			UserRole ur = new UserRole();
			ur.setUser(user);
			ur.setRole((Role) userService.find(Role.class, 2L));
			userService.save(ur);
			return this.register(lu);
		} catch (Exception e) {
			e.printStackTrace();
			if (e.getMessage().contains("ConstraintViolationException")) {
				return ResponseEntity.ok(new AuthToken("E", user.getUserName(), user.getPassword(), null, null, null,
						null, null, null, null, null, null, null));
			} else {
				return ResponseEntity.ok(new AuthToken("S", user.getUserName(), user.getPassword(), null, null, null,
						null, null, null, null, null, null, null));
			}
		}
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> register(@RequestBody LoginUser loginUser) throws AuthenticationException {

		final Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginUser.getUserName(), loginUser.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		final User user = userService.getUser(loginUser.getUserName(), loginUser.getUserName(), null);
		final String token = jwtTokenUtil.generateToken(user);

		Pair<List<MenuVO>, List<PermissionVO>> resources = this.authorizationService.getUserResources(user.getId(),
				loginUser.getLang());

		return ResponseEntity.ok(new AuthToken(token, loginUser.getUserName(), loginUser.getPassword(),
				user.getFirstName(), user.getLastName(), user.getUserGroup().getName(), user.getPicture(),
				user.getFirstTimeLogin(), Arrays.asList(new Long[] { user.getUserGroup().getId() }),
				resources.getValue0(), resources.getValue1(), user.getId(), userService.getHomePage(user)));

	}
}
