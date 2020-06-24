package com.softenza.emarket.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.softenza.emarket.service.GenericService;
import com.softenza.emarket.model.BaseEntity;
import com.softenza.emarket.model.User;

@Service(value="userService")
public interface UserService extends GenericService {
	
	public BaseEntity save(BaseEntity entity, MultipartFile file);
	
	public User getUser(String email, String userName, String password);
	
	public String sendPassword(User user, String password);
	
	public String changePassword(User user, String password);
	

}
