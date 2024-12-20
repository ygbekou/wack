package com.wack.service;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.RandomStringGenerator;
import org.javatuples.Quartet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wack.dao.UserDao;
import com.wack.model.BaseEntity;
import com.wack.model.Company;
import com.wack.model.Employee;
import com.wack.model.Mail;
import com.wack.model.Project;
import com.wack.model.ProjectUser;
import com.wack.model.SMPP;
import com.wack.model.SMS;
import com.wack.model.User;
import com.wack.model.authorization.Role;
import com.wack.model.website.Slider;
import com.wack.util.BulkSMS;
import com.wack.util.Constants;
import com.wack.util.ServiceConstants;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;

@Service(value = "userService")
public class UserServiceImpl extends GenericServiceImpl implements UserService, UserDetailsService {

	@Autowired
	GenericService genericService;

	@Autowired
	UserDao userDao;

	@Autowired
	BCryptPasswordEncoder encoder;

	@Autowired
	@Qualifier("myMailSender")
	MyMailSender mailSender;

	private static RandomStringGenerator stringGenerator;

	static {
		stringGenerator = new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(LETTERS, DIGITS).build();
	}

	@Transactional
	public BaseEntity save(BaseEntity entity, MultipartFile file) {
		User user;
		try {

			Field userField = entity.getClass().getDeclaredField("user");
			userField.setAccessible(true);
			Field passwordField = null;

			user = (User) userField.get(entity);
			passwordField = user.getClass().getDeclaredField("password");
			String generatedPassword = null;

			boolean isUserExisting = user.getId() != null && user.getId() > 0;

			if (!isUserExisting && passwordField != null) {
				passwordField.setAccessible(true);
				// passwordField.set(user, encoder.encode(passwordField.get(user).toString()));
				generatedPassword = stringGenerator.generate(8);
				passwordField.set(user, encoder.encode(generatedPassword));
			}
			user = (User) genericService.save(user);

			if (!isUserExisting && passwordField != null) {
				if (generatedPassword != null) {
					this.sendPassword(user, generatedPassword);
				}
			}

			if (user != null) {

				if (file != null && !file.isEmpty()) {
					try {
						String originalFileExtension = file.getOriginalFilename()
								.substring(file.getOriginalFilename().lastIndexOf("."));

						// transfer to upload folder
						String storageDirectory = null;
						if (entity != null) {
							storageDirectory = Constants.PIC_FOLDER + File.separator + "users";
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
			e.printStackTrace();
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
		User user = userDao.getUser(userName, userName, null);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				getAuthority());
	}

	private List<SimpleGrantedAuthority> getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ADMIN"));
	}

	@Transactional
	public String sendPassword(User user, String password) {
		String generatedPassword = StringUtils.isEmpty(password) ? stringGenerator.generate(8) : password;
		try {
			User storedUser = this.getUser(user.getEmail(), user.getUserName(), null);

			if (StringUtils.isEmpty(password)) {
				storedUser.setPassword(encoder.encode(generatedPassword));
				storedUser.setFirstTimeLogin("Y");
				this.genericService.save(storedUser);
			}

			Company company = this.genericService.getCompany("EN");

			String emailMessage = "Your password is: " + generatedPassword + ". Please keep it safe.";

			mailSender.sendMimeMail(company.getFromEmail(), new String[] { storedUser.getEmail() },
					"Password change message from " + company.getName(), emailMessage);

//			mailSender.sendMail(company.getFromEmail(), storedUser.getEmail().split("'"),
//					"Message from " + company.getName(), emailMessage);

		} catch (Exception ex) {
			ex.printStackTrace();
			return "Failure";
		}

		return "Success";

	}

	@Transactional
	public String changePassword(User user, String password) {
		try {
			User storedUser = this.getUser(user.getEmail(), user.getUserName(), null);

			if (!StringUtils.isEmpty(password)) {
				storedUser.setPassword(encoder.encode(password));
				storedUser.setFirstTimeLogin("N");
				this.genericService.save(storedUser);
			}
		} catch (Exception ex) {
			return "Failure";
		}

		return "Success";
	}

	public List<User> getActiveUsers() {
		List<Quartet<String, String, String, String>> paramTupleList = new ArrayList<Quartet<String, String, String, String>>();
		paramTupleList.add(Quartet.with("c.status = ", "status", "1", "Integer"));
		String queryStr = "SELECT c FROM User c WHERE 1 = 1 ";
		List<User> users = (List) this.getByCriteria(queryStr, paramTupleList, null);

		return users;
	}

	public String sendMassEmails(Mail mail) {
		try {
			List<User> users = getActiveUsers();
			List<String> emails = new ArrayList<>();
			List<String> phones = new ArrayList<>();

			for (User user : users) {
				emails.add(user.getEmail());
				phones.add(user.getMobilePhone());
			}

			String[] emailArray = emails.stream().toArray(String[]::new);
			String[] phoneArray = phones.stream().toArray(String[]::new);

			Company company = this.genericService.getCompany("EN");

			boolean inserted = false;

			if (emailArray.length > 0 && mail.isSendEmail()) {
				// send Mail
				String message = Constants.EMAIL_TEMPLATE_2
						.replace("AUTHOR", company.getFromEmail() + " " + company.getName())
						.replace("EMAIL_BODY", mail.getBody()).replace("ORG_WEBSITE", company.getWebsite())
						.replaceAll("ORG_NAME", company.getName()).replaceAll("ORG_ADDRESS", company.getAddress());

				mailSender.sendMail(company.getFromEmail(), emailArray, mail.getSubject(), message);

				// save mail
				genericService.save(mail);
				inserted = true;
			}

			if (phoneArray.length > 0 && mail.isSendSms()) {
				// send Sms
				String message = Constants.EMAIL_TEMPLATE_SMS
						.replace("AUTHOR", company.getFromEmail() + " " + company.getName())
						.replace("EMAIL_BODY", mail.getBody()).replace("ORG_WEBSITE", company.getWebsite())
						.replaceAll("ORG_NAME", company.getName()).replaceAll("ORG_ADDRESS", company.getAddress());

				for (String ph : phones) {
					sendMessage(mail, ph, message);
				}

				// save mail
				if (!inserted) {
					genericService.save(mail);
				}
			}

		} catch (Exception ex) {
			return ServiceConstants.RESPONSE_FAILURE;

		}
		return ServiceConstants.RESPONSE_SUCCESS;
	}

	private String sendMessage(Mail mail, String phone, String message) {

		try {
			if (phone != null && message != null) {
				SMS sms = new SMS();
				User user = new User(1L);
				String msg1 = message;
				boolean more = true;
				SMPP smpp = (SMPP) genericService.find(SMPP.class, 1L);
				while (more) {
					String msg = null;
					if (msg1 != null && msg1.length() > 160) {
						msg = msg1.substring(0, 159);
						msg1 = msg1.substring(159, msg1.length());
					} else {
						msg = msg1;
						more = false;
					}
					sms.setBody(msg);
					sms.setPhone(phone);
					sms.setSmpp(smpp);
					sms.setUser(user);
					sms.setModifiedBy(mail.getModifiedBy());
					sms.setResponse(BulkSMS.sendSMS(smpp.getUrl(), smpp.getUserName(), smpp.getPassword(), msg, phone));
					this.save(sms);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			return "Failure";
		}

		return "SUCCESS";

	}

	public BaseEntity getUserWithProjects(Long employeeId) {
		Employee employee = (Employee) this.genericService.find(Employee.class, employeeId);

		// Get the user assigned projects
		if (employee.getUser().getCompany() != null) {
			List<Quartet<String, String, String, String>> paramTupleList = new ArrayList<Quartet<String, String, String, String>>();
			paramTupleList.add(Quartet.with("pu.status = ", "status", "0", "Integer"));
			paramTupleList.add(Quartet.with("pu.user.id = ", "userId", employee.getUser().getId() + "", "Long"));
			List<ProjectUser> assProjects = (List) getByCriteria("SELECT pu FROM ProjectUser pu WHERE 1 = 1",
					paramTupleList, " ORDER BY pu.project.title ");

			paramTupleList.clear();
			paramTupleList.add(Quartet.with("p.status = ", "status", "0", "Integer"));

			paramTupleList.add(
					Quartet.with("p.company.id = ", "companyId", employee.getUser().getCompany().getId() + "", "Long"));

			List<Project> unAssProjects = (List) getByCriteria("SELECT p FROM Project p WHERE 1 = 1 ", paramTupleList,
					" ORDER BY p.title ");

			for (ProjectUser pu : assProjects) {
				unAssProjects.remove(pu.getProject());
			}

			employee.setUnAssignedProjects(unAssProjects);
			employee.setAssignedProjects(assProjects);
		}
		return employee;
	}

	public BaseEntity getEmployeeByUserWithProjects(Long userId) {

		Employee employee = null;

		List<Quartet<String, String, String, String>> paramTupleList = new ArrayList<Quartet<String, String, String, String>>();
		paramTupleList.add(Quartet.with("e.user.id = ", "userId", userId + "", "Long"));
		List<Employee> employees = (List) getByCriteria("SELECT e FROM Employee e WHERE 1 = 1", paramTupleList, " ");

		if (employees.size() > 0) {

			employee = employees.get(0);

			// Get the user assigned projects
			paramTupleList.clear();
			paramTupleList = new ArrayList<Quartet<String, String, String, String>>();
			paramTupleList.add(Quartet.with("pu.status = ", "status", "0", "Integer"));
			paramTupleList.add(Quartet.with("pu.user.id = ", "userId", employee.getUser().getId() + "", "Long"));
			List<ProjectUser> assProjects = (List) getByCriteria("SELECT pu FROM ProjectUser pu WHERE 1 = 1",
					paramTupleList, " ORDER BY pu.project.title ");

			paramTupleList.clear();
			paramTupleList.add(Quartet.with("p.status = ", "status", "0", "Integer"));
			paramTupleList.add(
					Quartet.with("p.company.id = ", "companyId", employee.getUser().getCompany().getId() + "", "Long"));
			List<Project> unAssProjects = (List) getByCriteria("SELECT p FROM Project p WHERE 1 = 1 ", paramTupleList,
					" ORDER BY p.title ");

			for (ProjectUser pu : assProjects) {
				unAssProjects.remove(pu.getProject());
			}

			employee.setUnAssignedProjects(unAssProjects);
			employee.setAssignedProjects(assProjects);
		}

		return employee;
	}
}
