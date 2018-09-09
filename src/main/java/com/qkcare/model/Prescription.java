package com.qkcare.model;

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

@Entity
@Table(name = "PRESCRIPTION")
public class Prescription extends BaseEntity {
	
	@Id
	@Column(name = "PRESCRIPTION_ID")
	@GeneratedValue
	private Long id;
	@ManyToOne
	@JoinColumn(name = "ADMISSION_ID")
	private Admission admission;
	@ManyToOne
	@JoinColumn(name = "VISIT_ID")
	private Visit visit;
	@Column(name = "PRESCRIPTION_DATETIME")
	private Timestamp prescriptionDatetime;
	@Column(name = "PRESCRIPTION_TYPE")
	private Integer prescriptionType;
	@Column(name = "IS_DISCHARGE")
	private String isDischarge;
	private String notes;
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
	public Admission getAdmission() {
		return admission;
	}
	public void setAdmission(Admission admission) {
		this.admission = admission;
	}
	public Visit getVisit() {
		return visit;
	}
	public void setVisit(Visit visit) {
		this.visit = visit;
	}
	public Timestamp getPrescriptionDatetime() {
		return prescriptionDatetime;
	}
	public void setPrescriptionDatetime(Timestamp prescriptionDatetime) {
		this.prescriptionDatetime = prescriptionDatetime;
	}
	public Integer getPrescriptionType() {
		return prescriptionType;
	}
	public void setPrescriptionType(Integer prescriptionType) {
		this.prescriptionType = prescriptionType;
	}
	public String getIsDischarge() {
		return isDischarge;
	}
	public void setIsDischarge(String isDischarge) {
		this.isDischarge = isDischarge;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
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
