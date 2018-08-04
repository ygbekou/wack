package com.qkcare.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "DOCTOR_ASSIGNMENT")
public class DoctorAssignment extends BaseEntity {
	
	@Id
	@Column(name = "DOCTOR_ASSIGNMENT_ID")
	@GeneratedValue
	private Long id;
	@ManyToOne
	@JoinColumn(name = "ADMISSION_ID")
	private Admission admission;
	@ManyToOne
	@JoinColumn(name = "DOCTOR_ID")
	private Employee doctor;
	@Column(name = "START_DATE")
	private Timestamp startDate;
	@Column(name = "END_DATE")
	private Timestamp endDate;
	
	@Transient
	private Employee transferDoctor;
	@Transient
	private Timestamp transferDate;
	
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
	public Employee getDoctor() {
		return doctor;
	}
	public void setDoctor(Employee doctor) {
		this.doctor = doctor;
	}
	public Timestamp getStartDate() {
		return startDate;
	}
	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}
	public Timestamp getEndDate() {
		return endDate;
	}
	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}
	public Employee getTransferDoctor() {
		return transferDoctor;
	}
	public void setTransferDoctor(Employee transferDoctor) {
		this.transferDoctor = transferDoctor;
	}
	public Timestamp getTransferDate() {
		return transferDate;
	}
	public void setTransferDate(Timestamp transferDate) {
		this.transferDate = transferDate;
	}
	
}
