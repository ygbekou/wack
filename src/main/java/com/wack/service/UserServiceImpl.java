package com.wack.service;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wack.dao.UserDao;
import com.wack.model.BaseEntity;
import com.wack.model.User;
import com.wack.util.Constants;

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
		try {
			
			Field userField = entity.getClass().getDeclaredField("user");
			userField.setAccessible(true);
			Field matriculeField = null;
			Field passwordField = null;
			try {
				matriculeField = entity.getClass().getDeclaredField("medicalRecordNumber");
				if (matriculeField != null)
					matriculeField.setAccessible(true);
				
			} catch(NoSuchFieldException nsfe) {
				
			}
	        user = (User) userField.get(entity);
	        passwordField = user.getClass().getDeclaredField("password");
			if (passwordField != null) {
				passwordField.setAccessible(true);
        		passwordField.set(user, "1234");
			}
	        user = (User) genericService.save(user);
	        
	       
	        if (user != null) {	 
	        	if (matriculeField != null) {
					matriculeField.set(entity, StringUtils.leftPad(user.getId().toString(), 8));
				}
	        	
	        	if (file != null && !file.isEmpty()) {
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

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userDao.getUser(null, userName, null);
		if(user == null){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), getAuthority());
	}
	
	private List<SimpleGrantedAuthority> getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ADMIN"));
	}
}
