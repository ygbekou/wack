package com.qkcare.model.activities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.qkcare.model.Admission;
import com.qkcare.model.BaseEntity;

@Entity
@Table(name = "DEATH_REPORT")
public class DeathReport extends BaseEntity {
	
	@Id
	@Column(name = "DEATH_REPORT_ID")
	@GeneratedValue
	private Long id;
	@ManyToOne(optional = true)
	@JoinColumn(name = "ADMISSION_ID", nullable = true)
	private Admission admission;
	@Column(name = "DEATH_DATETIME")
	private Timestamp deathDatetime;
	private String comments;
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
	public Timestamp getDeathDatetime() {
		return deathDatetime;
	}
	public void setDeathDatetime(Timestamp deathDatetime) {
		this.deathDatetime = deathDatetime;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
	//Transients
	public String getPatientName() {
		return this.admission.getPatientName();
	}
	
}
