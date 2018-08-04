package com.qkcare.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "VISIT")
public class Visit extends BaseEntity {

	@Id
	@Column(name = "VISIT_ID")
	@GeneratedValue
	private Long id;
	@ManyToOne
	@JoinColumn(name = "PATIENT_ID")
	private Patient patient;
	@Column(name = "VISIT_DATETIME")
	private Timestamp visitDatetime;
	private int status;
	
	// Transient
	@Transient
	private VitalSign vitalSign;
	
	

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
	public Timestamp getVisitDatetime() {
		return visitDatetime;
	}
	public void setVisitDatetime(Timestamp visitDatetime) {
		this.visitDatetime = visitDatetime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
	
	
	public VitalSign getVitalSign() {
		return vitalSign;
	}
	public void setVitalSign(VitalSign vitalSign) {
		this.vitalSign = vitalSign;
	}
	
	
	
	
}
