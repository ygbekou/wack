package com.qkcare.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PATIENT_ADMISSION")
public class PatientAdmission extends BaseEntity {
	
	@Id
	@Column(name = "PATIENT_ADMISSION_ID")
	@GeneratedValue
	private Long id;
	@ManyToOne
	@JoinColumn(name = "PATIENT_ID")
	private Patient patient;
	@ManyToOne
	@JoinColumn(name = "PACKAGE_ID")
	private com.qkcare.model.Package pckage;
	@ManyToOne
	@JoinColumn(name = "DOCTOR_ID")
	private Employee doctor;
	@ManyToOne
	@JoinColumn(name = "INSURANCE_ID")
	private Insurance insurance;
	@Column(name = "POLICY_NUMBER")
	private String policyNumber;
	@Column(name = "CONTACT_NAME")
	private String contatName;
	@Column(name = "CONTACT_RELATION")
	private String contactRelation;
	@Column(name = "CONTACT_PHONE")
	private String contactPhone;
	@Column(name = "CONTACT_ADDRESS")
	private String contactAddress;
	private int status;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public com.qkcare.model.Package getPckage() {
		return pckage;
	}
	public void setPckage(com.qkcare.model.Package pckage) {
		this.pckage = pckage;
	}
	public Employee getDoctor() {
		return doctor;
	}
	public void setDoctor(Employee doctor) {
		this.doctor = doctor;
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
	public String getContatName() {
		return contatName;
	}
	public void setContatName(String contatName) {
		this.contatName = contatName;
	}
	public String getContactRelation() {
		return contactRelation;
	}
	public void setContactRelation(String contactRelation) {
		this.contactRelation = contactRelation;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	public String getContactAddress() {
		return contactAddress;
	}
	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
