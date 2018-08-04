package com.qkcare.service;

import java.io.File;
import java.lang.reflect.Field;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.qkcare.dao.GenericDao;
import com.qkcare.dao.UserDao;
import com.qkcare.model.BaseEntity;
import com.qkcare.model.User;
import com.qkcare.util.Constants;

@Service(value="userService")
public class UserServiceImpl  implements UserService {
	
	@Autowired
	GenericService genericService;
	
	@Autowired
	UserDao userDao;
	
	@Transactional
	public BaseEntity save(BaseEntity entity, MultipartFile file) {		
		User user;
		try {
			
			Field userField = entity.getClass().getDeclaredField("user");
			userField.setAccessible(true);
			Field matriculeField = null;
			try {
				matriculeField = entity.getClass().getDeclaredField("matricule");
				if (matriculeField != null)
					matriculeField.setAccessible(true);
			} catch(NoSuchFieldException nsfe) {
				
			}
	        user = (User) userField.get(entity);
	        user = (User) genericService.save(user);
	        
	       
	        if (user != null) {	 
	        	if (matriculeField != null) {
					matriculeField.set(entity, user.getUserName());
				}
	        	if (!file.isEmpty()) {
					try {
						String originalFileExtension = file.getOriginalFilename()
								.substring(file.getOriginalFilename().lastIndexOf("."));

						// transfer to upload folder
						String storageDirectory = null;
						if (entity != null) {					
							storageDirectory = Constants.IMAGE_FOLDER	+ "User" + File.separator;
							File dir = new File(storageDirectory);
							if (!dir.exists()) {
								dir.mkdirs();
							}

						} else {
							throw new Exception("Unknown");
						}
						
						String newFilename = null;
						newFilename = user.getId() + originalFileExtension;
						

						File newFile = new File(storageDirectory + "/" + newFilename);
						Field pictureField = user.getClass().getDeclaredField("picture");
						pictureField.setAccessible(true);
						pictureField.set(user, newFilename);
						user = (User) genericService.save(user);
				        
						file.transferTo(newFile);
				        
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
	        	userField.set(entity, user);
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
