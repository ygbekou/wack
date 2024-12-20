package com.wack.controller;

import com.wack.domain.MenuVO;
import com.wack.domain.PermissionVO;
import com.wack.service.AuthorizationService;
import com.wack.config.JwtTokenUtil;
import com.wack.domain.AuthToken;
import com.wack.domain.LoginUser;
import com.wack.model.User;
import com.wack.service.UserService;

import java.util.Arrays;
import java.util.List;

import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service/token")
@CrossOrigin
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserService userService;

	@Autowired
	private AuthorizationService authorizationService;

	@Autowired
	BCryptPasswordEncoder encoder;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> authenticate(@RequestBody LoginUser loginUser) throws AuthenticationException {
		System.out.println("Authenticating user :" + loginUser);
		try {
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
					resources.getValue0(), resources.getValue1(), user.getId(), userService.getHomePage(user),
					user.getRestrictions(), user.getCompany() != null ? user.getCompany().getId(): null, user.getUserType()));

		} catch (Exception b) {
			b.printStackTrace();
			return ResponseEntity.ok(new AuthToken("", loginUser.getUserName(), loginUser.getPassword(), null, null,
					null, null, null, null, null, null, null, null));
		}

	}

}
