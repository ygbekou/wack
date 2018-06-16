package com.qkcare.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "BILL")
public class Bill extends BaseEntity {
	
	@Id
	@Column(name = "BILL_ID")
	@GeneratedValue
	private Long id;
	@OneToOne
	@JoinColumn(name = "PATIENT_ADMISSION_ID")
	private PatientAdmission patientAdmission;
	@OneToOne
	@JoinColumn(name = "APPOINTMENT_ID")
	private Appointment appointment;
	@Column(name = "SUB_TOTAL")
	private Double subTotal;
	private Double taxes;
	private Double discount;
	@Column(name = "GRAND_TOTAL")
	private Double grandTotal;
	private Double paid;
	private Double due;
	@Column(name = "BILL_DATE")
	private Date billDate;
	private int status;
	
	@Transient
	List<BillService> billServices;
	@Transient
	List<BillPayment> billPayments;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public PatientAdmission getPatientAdmission() {
		return patientAdmission;
	}
	public void setPatientAdmission(PatientAdmission patientAdmission) {
		this.patientAdmission = patientAdmission;
	}
	public Double getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(Double subTotal) {
		this.subTotal = subTotal;
	}
	public Double getTaxes() {
		return taxes;
	}
	public void setTaxes(Double taxes) {
		this.taxes = taxes;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public Double getGrandTotal() {
		return grandTotal;
	}
	public void setGrandTotal(Double grandTotal) {
		this.grandTotal = grandTotal;
	}
	public Double getPaid() {
		return paid;
	}
	public void setPaid(Double paid) {
		this.paid = paid;
	}
	public Double getDue() {
		return due;
	}
	public void setDue(Double due) {
		this.due = due;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Appointment getAppointment() {
		return appointment;
	}
	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}
	public Date getBillDate() {
		return billDate;
	}
	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}
	public List<BillService> getBillServices() {
		return billServices;
	}
	public void setBillServices(List<BillService> billServices) {
		this.billServices = billServices;
	}
	public List<BillPayment> getBillPayments() {
		return billPayments;
	}
	public void setBillPayments(List<BillPayment> billPayments) {
		this.billPayments = billPayments;
	}
	
	// Transient attributes
	
	public String getPatientId() {
		return this.getAppointment().getPatient().getMatricule();
	}
	
	public String getPatientName() {
		return this.getAppointment().getPatient().getName();
	}
}
