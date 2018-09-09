package com.qkcare.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "DISCHARGE")
public class Discharge extends BaseEntity {
	
	@Id
	@Column(name = "DISCHARGE_ID")
	@GeneratedValue
	private Long id;
	@OneToOne
	@JoinColumn(name = "VISIT_ID")
	private Visit visit;
	@OneToOne
	@JoinColumn(name = "ADMISSION_ID")
	private Admission admission;
	@ManyToOne
	@JoinColumn(name = "DOCTOR_ID")
	private Employee doctor;
	@ManyToOne
	@JoinColumn(name = "DISCHARGE_REASON_ID")
	private DischargeReason dischargeReason;
	@Column(name = "DISCHARGE_DATETIME")
	private Timestamp dischargeDatetime;
	@Column(name="ADMITTANCE_REASON")
	private String admittanceReason;
	@Column(name = "TREATMENT_SUMMARY")
	private String treatmentSummary;
	@Column(name = "PHYSICIAN_APPROVED")
	private String physicianApproved;
	@Column(name = "FURTHER_TREATMENT_PLAN")
	private String furtherTreatmentPlan;
	@Column(name = "NEXT_CHECKUP_DATE")
	private Date nextCheckupDate;
	@Column(name = "CLIENT_CONSENT_APPROVAL")
	private String clientConsentApproval;
	private String notes;
	
	
	
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
	public DischargeReason getDischargeReason() {
		return dischargeReason;
	}
	public void setDischargeReason(DischargeReason dischargeReason) {
		this.dischargeReason = dischargeReason;
	}
	public Timestamp getDischargeDatetime() {
		return dischargeDatetime;
	}
	public void setDischargeDatetime(Timestamp dischargeDatetime) {
		this.dischargeDatetime = dischargeDatetime;
	}
	public String getAdmittanceReason() {
		return admittanceReason;
	}
	public void setAdmittanceReason(String admittanceReason) {
		this.admittanceReason = admittanceReason;
	}
	public String getTreatmentSummary() {
		return treatmentSummary;
	}
	public void setTreatmentSummary(String treatmentSummary) {
		this.treatmentSummary = treatmentSummary;
	}
	public String getPhysicianApproved() {
		return physicianApproved;
	}
	public void setPhysicianApproved(String physicianApproved) {
		this.physicianApproved = physicianApproved;
	}
	public String getFurtherTreatmentPlan() {
		return furtherTreatmentPlan;
	}
	public void setFurtherTreatmentPlan(String furtherTreatmentPlan) {
		this.furtherTreatmentPlan = furtherTreatmentPlan;
	}
	public Date getNextCheckupDate() {
		return nextCheckupDate;
	}
	public void setNextCheckupDate(Date nextCheckupDate) {
		this.nextCheckupDate = nextCheckupDate;
	}
	public String getClientConsentApproval() {
		return clientConsentApproval;
	}
	public void setClientConsentApproval(String clientConsentApproval) {
		this.clientConsentApproval = clientConsentApproval;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
}
