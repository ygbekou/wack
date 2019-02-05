package com.wack.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wack.model.BaseEntity;
import com.wack.model.User;

@Service(value="userService")
public interface UserService {
	
	public BaseEntity save(BaseEntity entity, MultipartFile file);
	
	public User getUser(String email, String userName, String password);
	
	public String sendPassword(User user, String password);
}
