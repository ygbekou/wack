package com.qkcare.service;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.qkcare.dao.UserDao;
import com.qkcare.model.BaseEntity;
import com.qkcare.model.User;
import com.qkcare.util.Constants;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service(value="userService")
public class UserServiceImpl  implements UserService, UserDetailsService {
	
	@Autowired
	GenericService genericService;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	BCryptPasswordEncoder encoder;
	
	@Transactional
	public BaseEntity save(BaseEntity entity, MultipartFile file) {		
		User user;
		throw new RuntimeException();
		
		//return genericService.save(entity);		
	}

	@Transactional
	public User getUser(String email, String userName, String password) {
		return userDao.getUser(email, userName, password);
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		System.out.println("This is my user: " + userName);
		User user = userDao.getUser(null, userName, null);
		if(user == null){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		System.out.println("This is my user password: " + user.getPassword());
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), getAuthority());
	}
	
	private List<SimpleGrantedAuthority> getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ADMIN"));
	}
}
