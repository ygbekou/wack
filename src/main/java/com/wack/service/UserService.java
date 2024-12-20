package com.wack.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.wack.service.GenericService;
import com.wack.model.BaseEntity;
import com.wack.model.Mail;
import com.wack.model.User;

@Service(value="userService")
public interface UserService extends GenericService {
	
	public BaseEntity save(BaseEntity entity, MultipartFile file);
	
	public User getUser(String email, String userName, String password);
	
	public String sendPassword(User user, String password);
	
	public String changePassword(User user, String password);
	
	public List<User> getActiveUsers();
	
	public String sendMassEmails(Mail mail);
	
	public BaseEntity getUserWithProjects(Long employeeId);
	
	public BaseEntity getEmployeeByUserWithProjects(Long userId);
}
