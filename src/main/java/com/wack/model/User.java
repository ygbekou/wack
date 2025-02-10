package com.wack.model;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.wack.model.authorization.Role;
import com.wack.model.stock.ProductDesc;


@Entity
@Table(name = "USERS")
public class User extends BaseEntity {

	@Id
	@Column(name = "USER_ID")
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "USER_GROUP_ID")
	private UserGroup userGroup;
	
	@ManyToOne
	@JoinColumn(name = "COMPANY_ID")
	private Company company;
	
	@ManyToOne
	@JoinColumn(name = "POSITION_ID")
	private Position position;
	
	@Column(name = "USER_NAME")
	private String userName;
	private String password;
	@Column(name = "FIRST_NAME")
	private String firstName;
	@Column(name = "LAST_NAME")
	private String lastName;
	@Column(name = "MIDDLE_NAME")
	private String middleName;
	@Column(name = "PIC")
	private String picture;
	
	@Column(name = "E_MAIL")
	private String email;
	private String sex;
	@Column(name = "BIRTH_DATE")
	private Date birthDate;
	@Column(name = "HOME_PHONE")
	private String homePhone;
	@Column(name = "MOBILE_PHONE")
	private String mobilePhone;
	private String address;
	private String city;
	
	@ManyToOne
	@JoinColumn(name = "COUNTRY_ID")
	private Country country;
	@Column(name = "ZIP_CODE")
	private String zipCode;
	private int status;
	@Column(name="FIRST_TIME_LOGIN")
	private String firstTimeLogin="Y";
	
	@Column(name="COMPANY_NAME")
	private String companyName;
	private String facebook;
	private String twitter;
	private String linkedin;
	private String instagram;
	private String website;
	
	@Column(name = "PHONE")
	private String phone;
	
	@Column(name="RECEIVE_NEWSLETTER")
	private boolean receiveNewsletter;
	
	@Column(name="RESTRICT_EMP_TAB")
	private boolean restrictEmpTab;
	
	@Column(name="RESTRICT_SAL_TAB")
	private boolean restrictSalTab;
	
	@Column(name="RESTRICT_FUND_TAB")
	private boolean restrictFundTab;
	
	@Column(name="USER_TYPE")
	private int userType;
	
	@Column(name="USE_COMPANY_CONTACT")
	private boolean useCompanyContact;
	
	@OneToMany(mappedBy="user", fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	List<UserDesc> userDescs;
	
	// Transient
	@Transient
	private List<Role> roles;
	@Transient
	private List<Employee> employees;
	@Transient
	private List<Video> videos;
	@Transient
	List<Project> assignedProjects;
	@Transient
	List<Project> unAssignedProjects;
	
	
	public List<Video> getVideos() {
		return videos;
	}

	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}

	public String getFirstTimeLogin() {
		return firstTimeLogin;
	}

	public void setFirstTimeLogin(String firstTimeLogin) {
		this.firstTimeLogin = firstTimeLogin;
	}

	public User() {
	}
	
	public User(Long id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserGroup getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName == null ? "" : middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	// Transient

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public String getLinkedin() {
		return linkedin;
	}

	public void setLinkedin(String linkedin) {
		this.linkedin = linkedin;
	}

	public String getInstagram() {
		return instagram;
	}

	public void setInstagram(String instagram) {
		this.instagram = instagram;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getName() {
		return this.getFirstName() + (this.getMiddleName() != null ? (" " + this.getMiddleName() + " ") : " ")
				+ this.getLastName();
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public boolean isReceiveNewsletter() {
		return receiveNewsletter;
	}

	public void setReceiveNewsletter(boolean receiveNewsletter) {
		this.receiveNewsletter = receiveNewsletter;
	}

	public boolean isRestrictEmpTab() {
		return restrictEmpTab;
	}

	public void setRestrictEmpTab(boolean restrictEmpTab) {
		this.restrictEmpTab = restrictEmpTab;
	}

	public boolean isRestrictSalTab() {
		return restrictSalTab;
	}

	public void setRestrictSalTab(boolean restrictSalTab) {
		this.restrictSalTab = restrictSalTab;
	}

	public boolean isRestrictFundTab() {
		return restrictFundTab;
	}

	public void setRestrictFundTab(boolean restrictFundTab) {
		this.restrictFundTab = restrictFundTab;
	}

	public boolean isUseCompanyContact() {
		return useCompanyContact;
	}

	public void setUseCompanyContact(boolean useCompanyContact) {
		this.useCompanyContact = useCompanyContact;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	
	public List<Project> getAssignedProjects() {
		return assignedProjects;
	}

	public void setAssignedProjects(List<Project> assignedProjects) {
		this.assignedProjects = assignedProjects;
	}

	public List<Project> getUnAssignedProjects() {
		return unAssignedProjects;
	}

	public void setUnAssignedProjects(List<Project> unAssignedProjects) {
		this.unAssignedProjects = unAssignedProjects;
	}

	public List<UserDesc> getUserDescs() {
		return userDescs;
	}

	public void setUserDescs(List<UserDesc> userDescs) {
		this.userDescs = userDescs;
	}

	public void setGeneratedFields(BCryptPasswordEncoder encoder) {
		this.setPassword(encoder.encode(this.password));
	}
	
	public List<String> getChildEntities() {
		return Arrays.asList("videos");
	}
	
	public String getRestrictions() {
		StringBuilder builder = new StringBuilder();
		if (isRestrictEmpTab()) {
			builder.append("RESTRICT_EMP_TAB");
		}
		if (isRestrictSalTab()) {
			builder.append("RESTRICT_SAL_TAB");
		}
		if (isRestrictFundTab()) {
			builder.append("RESTRICT_FUND_TAB");
		}
		
		return builder.toString();
	}
	
}
