package com.qkcare.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PRESCRIPTION_MEDICINE")
public class PrescriptionMedicine extends BaseEntity {
	
	@Id
	@Column(name = "PRESCRIPTION_MEDICINE_ID")
	@GeneratedValue
	private Long id;
	@OneToOne
	@JoinColumn(name = "PRESCRIPTION_ID")
	private Prescription prescription;
	@OneToOne
	@JoinColumn(name = "MEDICINE_ID")
	private Medicine medicine;
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
	public Medicine getMedicine() {
		return medicine;
	}
	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}
	public String getInstructions() {
		return instructions;
	}
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}
}
