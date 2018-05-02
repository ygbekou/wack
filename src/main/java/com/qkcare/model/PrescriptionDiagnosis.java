package com.qkcare.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PRESCRIPTION_DIAGNOSIS")
public class PrescriptionDiagnosis extends BaseEntity {
	
	@Id
	@Column(name = "PRESCRIPTION_DIAGNOSIS_ID")
	@GeneratedValue
	private Long id;
	@OneToOne
	@JoinColumn(name = "PRESCRIPTION_ID")
	private Prescription prescription;
	private String diagnosis;
	private String instructions;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Prescription getPrescription() {
		return prescription;
	}
	public void setPrescription(Prescription prescription) {
		this.prescription = prescription;
	}
	public String getDiagnosis() {
		return diagnosis;
	}
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	public String getInstructions() {
		return instructions;
	}
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}
}
