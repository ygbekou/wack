package com.qkcare.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "MEDICALHISTORY")
public class MedicalHistory extends BaseEntity {
	
	@Id
	@Column(name = "MEDICALHISTORY_ID")
	@GeneratedValue
	private Long id;
	private String name;
	private String description;
	private int status;
	
	public MedicalHistory() {}

	public MedicalHistory(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
	// Transient fields for UI
	
	public String getStatusDesc() {
		return status == 0 ? "Active" : "Inactive";
	}
}
