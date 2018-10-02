package com.qkcare.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.qkcare.model.enums.BloodGroup;

@Entity
@Table(name = "PATIENT")
public class Patient extends BaseEntity {
	
	@Id
	@Column(name = "PATIENT_ID")
	@GeneratedValue
	private Long id;
	private String matricule;
	@OneToOne
	@JoinColumn(name = "USER_ID")
	private User user;
	@ManyToOne
	@JoinColumn(name = "RELIGION_ID")
	private Religion religion;
	@ManyToOne
	@JoinColumn(name = "NATIONALITY_ID")
	private Country nationality;
	@ManyToOne
	@JoinColumn(name = "PAYER_TYPE_ID")
	private PayerType payerType;
	@ManyToOne
	@JoinColumn(name = "MARITAL_STATUS_ID")
	private MaritalStatus maritalStatus;
	private String employer;
	@Column(name = "AUTHORIZATION_LETTER_NUMBER")
	private String authorizationLetterNumber;
	@Column(name = "EXPIRY_DATE")
	private Date expiryDate;
	@Column(name = "EMPLOYEE_ID")
	private String employeeId;
	@ManyToOne
	@JoinColumn(name = "INSURANCE_ID")
	private Insurance insurance;
	@Column(name = "POLICY_NUMBER")
	private String policyNumber;
	@Column(name = "INSURANCE_EXPIRY_DATE")
	private Date insuranceExpiryDate;
	@ManyToOne
	@JoinColumn(name = "OCCUPATION_ID")
	private Occupation occupation;
	private String contact;
	@Column(name="CONTACT_PHONE")
	private String contactPhone;
	private String referral;
	@Column(name="REFERRAL_PHONE")
	private String referralPhone;
	@Column(name="MEDICAL_HISTORY")
	private String medicalHistory;
	@Column(name="BLOOD_GROUP")
	private BloodGroup bloodGroup;
	private int status;
	
	public Patient() {}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMatricule() {
		return matricule;
	}
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Religion getReligion() {
		return religion;
	}
	public void setReligion(Religion religion) {
		this.religion = religion;
	}
	public Occupation getOccupation() {
		return occupation;
	}
	public void setOccupation(Occupation occupation) {
		this.occupation = occupation;
	}
	public Country getNationality() {
		return nationality;
	}
	public void setNationality(Country nationality) {
		this.nationality = nationality;
	}
	public PayerType getPayerType() {
		return payerType;
	}
	public void setPayerType(PayerType payerType) {
		this.payerType = payerType;
	}
	public String getEmployer() {
		return employer;
	}
	public void setEmployer(String employer) {
		this.employer = employer;
	}
	public String getAuthorizationLetterNumber() {
		return authorizationLetterNumber;
	}
	public void setAuthorizationLetterNumber(String authorizationLetterNumber) {
		this.authorizationLetterNumber = authorizationLetterNumber;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public Insurance getInsurance() {
		return insurance;
	}
	public void setInsurance(Insurance insurance) {
		this.insurance = insurance;
	}
	public String getPolicyNumber() {
		return policyNumber;
	}
	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}
	public Date getInsuranceExpiryDate() {
		return insuranceExpiryDate;
	}
	public void setInsuranceExpiryDate(Date insuranceExpiryDate) {
		this.insuranceExpiryDate = insuranceExpiryDate;
	}
	public BloodGroup getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(BloodGroup bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	public String getReferral() {
		return referral;
	}
	public void setReferral(String referral) {
		this.referral = referral;
	}
	public String getReferralPhone() {
		return referralPhone;
	}
	public void setReferralPhone(String referralPhone) {
		this.referralPhone = referralPhone;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	public String getMedicalHistory() {
		return medicalHistory;
	}
	public void setMedicalHistory(String medicalHistory) {
		this.medicalHistory = medicalHistory;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public MaritalStatus getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(MaritalStatus maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	
	// Transient fields for UI

	public String getMaritalStatusName() {
		return this.maritalStatus != null ? this.maritalStatus.getName() : "";
	}
	public String getReligionName() {
		return this.religion != null ? this.religion.getName() : "";
	}
	public String getOccupationName() {
		return this.occupation != null ? this.occupation.getName() : "";
	}
	public String getNationalityName() {
		return this.religion != null ? this.nationality.getName() : "";
	}
	public String getPayerTypeName() {
		return this.payerType != null ? this.payerType.getName() : "";
	}
	public String getFirstName() {
		return this.user.getFirstName();
	}
	public String getLastName() {
		return this.user.getLastName();
	}
	public String getMiddleName() {
		return this.user.getMiddleName();
	}
	public String getEmail() {
		return this.user.getEmail();
	}
	public String getHomePhone() {
		return this.user.getHomePhone();
	}
	public String getAddress() {
		return this.user.getAddress();
	}
	public String getSex() {
		return this.user.getSex();
	}
	public Date getBirthDate() {
		return this.user.getBirthDate();
	}
	
	public String getName() {
		return this.user.getFirstName() + " " + this.user.getLastName();
	}
	
}
