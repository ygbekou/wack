package com.qkcare.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "VITAL_SIGN")
public class VitalSign extends BaseEntity {
	
	@Id
	@Column(name = "VITAL_SIGN_ID")
	@GeneratedValue
	private Long id;
	@OneToOne
	@JoinColumn(name = "VISIT_ID")
	private Visit visit;
	@Column(name = "VITAL_SIGN_DATETIME")
	private Timestamp vitalSignDatetime;
	private Double temperature;
	private String pulse;
	private String respiration;
	@Column(name = "BLOOD_PRESSURE")
	private String bloodPressure;
	@Column(name = "BLOOD_SUGAR")
	private Double bloodSugar;
	private String pain;
	private Double weight;
	private Double height;
	private Double bmi;
	

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Visit getVisit() {
		return visit;
	}
	public void setVisit(Visit visit) {
		this.visit = visit;
	}
	public Timestamp getVitalSignDatetime() {
		return vitalSignDatetime;
	}
	public void setVitalSignDatetime(Timestamp vitalSignDatetime) {
		this.vitalSignDatetime = vitalSignDatetime;
	}
	public Double getTemperature() {
		return temperature;
	}
	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}
	public String getPulse() {
		return pulse;
	}
	public void setPulse(String pulse) {
		this.pulse = pulse;
	}
	public String getRespiration() {
		return respiration;
	}
	public void setRespiration(String respiration) {
		this.respiration = respiration;
	}
	public String getBloodPressure() {
		return bloodPressure;
	}
	public void setBloodPressure(String bloodPressure) {
		this.bloodPressure = bloodPressure;
	}
	public Double getBloodSugar() {
		return bloodSugar;
	}
	public void setBloodSugar(Double bloodSugar) {
		this.bloodSugar = bloodSugar;
	}
	public String getPain() {
		return pain;
	}
	public void setPain(String pain) {
		this.pain = pain;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public Double getHeight() {
		return height;
	}
	public void setHeight(Double height) {
		this.height = height;
	}
	public Double getBmi() {
		return bmi;
	}
	public void setBmi(Double bmi) {
		this.bmi = bmi;
	}
	
	// Transient attributes
	public String getPatientId() {
		return (this.getVisit() != null && this.getVisit().getPatient() != null) ? this.getVisit().getPatient().getMatricule() : null;
	}
	
	public String getPatientName() {
		return (this.getVisit() != null && this.getVisit().getPatient() != null) ? this.getVisit().getPatient().getName() : null;
	}

}
