package com.qkcare.service;

import java.lang.reflect.Field;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qkcare.dao.GenericDao;
import com.qkcare.dao.UserDao;
import com.qkcare.model.BaseEntity;
import com.qkcare.model.User;

@Service(value="userService")
public class UserServiceImpl  implements UserService {
	
	@Autowired
	GenericService genericService;
	
	@Autowired
	UserDao userDao;
	
	@Transactional
	public BaseEntity save(BaseEntity entity) {		
		User user;
		try {
			
			Field field = entity.getClass().getDeclaredField("user");
			field.setAccessible(true);
	        user = (User) field.get(entity);
	        user = (User) genericService.save(user);
	        if (user != null) {	        	
				field.set(entity, user);
			}
		} catch (Exception e) {
			throw new NullPointerException();
		} 
				
		return genericService.save(entity);		
	}

	@Transactional
	public User getUser(String email, String userName, String password) {
		return userDao.getUser(email, userName, password);
	}
}
