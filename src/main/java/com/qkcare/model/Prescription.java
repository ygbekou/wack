package com.qkcare.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "PRESCRIPTION")
public class Prescription extends BaseEntity {
	
	@Id
	@Column(name = "PRESCRIPTION_ID")
	@GeneratedValue
	private Long id;
	@OneToOne
	@JoinColumn(name = "PATIENT_ID")
	private Patient patient;
	@OneToOne
	@JoinColumn(name = "APPOINTMENT_ID")
	private Appointment appointment;
	private Double weight;
	@Column(name = "BLOOD_PRESSURE")
	private String bloodPressure;
	private String reference;
	@Column(name = "PRESCRIPTION_TYPE")
	private Integer prescriptionType;
	@Column(name = "CHIEF_COMPLAIN")
	private String chiefComplain;
	private int status;
	
	@Transient
	List<PrescriptionMedicine> prescriptionMedicines;
	
	@Transient
	List<PrescriptionDiagnosis> prescriptionDiagnoses;
	
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
	public Appointment getAppointment() {
		return appointment;
	}
	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public String getBloodPressure() {
		return bloodPressure;
	}
	public void setBloodPressure(String bloodPressure) {
		this.bloodPressure = bloodPressure;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public Integer getPrescriptionType() {
		return prescriptionType;
	}
	public void setPrescriptionType(Integer prescriptionType) {
		this.prescriptionType = prescriptionType;
	}
	public String getChiefComplain() {
		return chiefComplain;
	}
	public void setChiefComplain(String chiefComplain) {
		this.chiefComplain = chiefComplain;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public List<PrescriptionMedicine> getPrescriptionMedicines() {
		return prescriptionMedicines;
	}
	public void setPrescriptionMedicines(List<PrescriptionMedicine> prescriptionMedicines) {
		this.prescriptionMedicines = prescriptionMedicines;
	}
	public List<PrescriptionDiagnosis> getPrescriptionDiagnoses() {
		return prescriptionDiagnoses;
	}
	public void setPrescriptionDiagnoses(List<PrescriptionDiagnosis> prescriptionDiagnoses) {
		this.prescriptionDiagnoses = prescriptionDiagnoses;
	}
}
