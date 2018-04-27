package com.qkcare.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

@Entity
@Table(name = "DOCUMENT")
public class Document extends BaseEntity {
	
	@Id
	@Column(name = "DOCUMENT_ID")
	@GeneratedValue
	private Long id;
	@ManyToOne
	@JoinColumn(name = "PATIENT_ID")
	private Patient patient;
	@ManyToOne
	@JoinColumn(name = "DOCTOR_ID")
	private Employee doctor;
	@Column(name = "FILE_LOCATION")
	private String fileLocation;
	private String description;
	
	public Document() {}

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

	public Employee getDoctor() {
		return doctor;
	}

	public void setDoctor(Employee doctor) {
		this.doctor = doctor;
	}

	public String getFileLocation() {
		return fileLocation;
	}

	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	// TRansient fields for UI
	
	public String getDoctorName() {
		return this.doctor.getFirstName() + " " + toValue(this.doctor.getMiddleName() + " ") + this.doctor.getLastName();
	}
	public String getPatientName() {
		return this.patient.getFirstName() + " " + toValue(this.patient.getMiddleName() + " ") + this.patient.getLastName();
	}
	
	
	private String toValue(String value) {
		return StringUtils.isEmpty(value) ? "" : value;
	}
}
