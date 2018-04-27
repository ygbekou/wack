package com.qkcare.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
	private String contact;
	@Column(name="CONTACT_PHONE")
	private String contactPhone;
	@Column(name="MEDICAL_HISTORY")
	private String medicalHistory;
	private int status;
	
	public Patient() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
	
	
	// TRansient fields for UI
	
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
	public String getPhone() {
		return this.user.getPhone();
	}
	public String getAddress() {
		return this.user.getAddress();
	}
	public String getSex() {
		return this.user.getSex();
	}
	
}
