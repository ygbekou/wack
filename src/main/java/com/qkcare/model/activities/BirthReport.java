package com.qkcare.model.activities;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.qkcare.model.Admission;
import com.qkcare.model.BaseEntity;

@Entity
@Table(name = "BIRTH_REPORT")
public class BirthReport extends BaseEntity {
	
	@Id
	@Column(name = "BIRTH_REPORT_ID")
	@GeneratedValue
	private Long id;
	@ManyToOne(optional = true)
	@JoinColumn(name = "ADMISSION_ID", nullable = true)
	private Admission admission;
	@Column(name = "BIRTH_DATETIME")
	private Timestamp birthDatetime;
	private String comments;
	@Column(name = "LAST_NAME")
	private String lastName;
	@Column(name = "FIRST_NAME")
	private String firstName;
	@Column(name = "MIDDLE_NAME")
	private String middleName;
	@Column(name = "FATHER_LAST_NAME")
	private String fatherLastName;
	@Column(name = "FATHER_FIRST_NAME")
	private String fatherFirstName;
	@Column(name = "FATHER_MIDDLE_NAME")
	private String fatherMiddleName;
	@Column(name = "MOTHER_LAST_NAME")
	private String motherLastName;
	@Column(name = "MOTHER_FIRST_NAME")
	private String motherFirstName;
	@Column(name = "MOTHER_MIDDLE_NAME")
	private String motherMiddleName;
	private int status;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Admission getAdmission() {
		return admission;
	}
	public void setAdmission(Admission admission) {
		this.admission = admission;
	}
	public Timestamp getBirthDatetime() {
		return birthDatetime;
	}
	public void setBirthDatetime(Timestamp birthDatetime) {
		this.birthDatetime = birthDatetime;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getFatherLastName() {
		return fatherLastName;
	}
	public void setFatherLastName(String fatherLastName) {
		this.fatherLastName = fatherLastName;
	}
	public String getFatherFirstName() {
		return fatherFirstName;
	}
	public void setFatherFirstName(String fatherFirstName) {
		this.fatherFirstName = fatherFirstName;
	}
	public String getFatherMiddleName() {
		return fatherMiddleName;
	}
	public void setFatherMiddleName(String fatherMiddleName) {
		this.fatherMiddleName = fatherMiddleName;
	}
	public String getMotherLastName() {
		return motherLastName;
	}
	public void setMotherLastName(String motherLastName) {
		this.motherLastName = motherLastName;
	}
	public String getMotherFirstName() {
		return motherFirstName;
	}
	public void setMotherFirstName(String motherFirstName) {
		this.motherFirstName = motherFirstName;
	}
	public String getMotherMiddleName() {
		return motherMiddleName;
	}
	public void setMotherMiddleName(String motherMiddleName) {
		this.motherMiddleName = motherMiddleName;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	// Transients
	public String getPatientName() {
		return this.admission.getPatientName();
	}
	
	public String getName() {
		return this.getFirstName() + (this.getMiddleName() != null ? " " + this.getMiddleName() + " ": " ") + this.getLastName();
	}
	
	public String getFatherName() {
		return this.getFatherFirstName() + (this.getFatherMiddleName() != null ? " " + this.getFatherMiddleName() + " ": " ") + this.getFatherLastName();
	}
	
	public String getMotherName() {
		return this.getMotherFirstName() + (this.getMotherMiddleName() != null ? " " + this.getMotherMiddleName() + " ": " ") + this.getMotherLastName();
	}
}
