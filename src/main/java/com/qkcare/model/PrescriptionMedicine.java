package com.qkcare.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PRESCRIPTION_MEDICINE")
public class PrescriptionMedicine extends BaseEntity {
	
	@Id
	@Column(name = "PRESCRIPTION_MEDICINE_ID")
	@GeneratedValue
	private Long id;
	@ManyToOne
	@JoinColumn(name = "PRESCRIPTION_ID")
	private Prescription prescription;
	@ManyToOne
	@JoinColumn(name = "MEDICINE_ID")
	private Medicine medicine;
	private String dosage;
	private Integer quantity;
	private String frequency;
	@Column(name = "NUMBER_OF_DAYS")
	private Integer numberOfDays;
	
	
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
	public String getDosage() {
		return dosage;
	}
	public void setDosage(String dosage) {
		this.dosage = dosage;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getFrequency() {
		return frequency;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	public Integer getNumberOfDays() {
		return numberOfDays;
	}
	public void setNumberOfDays(Integer numberOfDays) {
		this.numberOfDays = numberOfDays;
	}
	
}
