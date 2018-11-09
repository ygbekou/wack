package com.qkcare.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "BILL_SERVICE")
public class BillService extends BaseEntity {
	
	@Id
	@Column(name = "BILL_SERVICE_ID")
	@GeneratedValue
	private Long id;
	@ManyToOne
	@JoinColumn(name = "BILL_ID")
	private Bill bill;
	@ManyToOne
	@JoinColumn(name = "SERVICE_ID")
	private Service service;
	@ManyToOne
	@JoinColumn(name = "DOCTOR_ID")
	private Employee doctor;
	@Column(name = "SERVICE_DATE")
	private Date serviceDate;
	private String description;
	private int quantity;
	@Column(name = "UNIT_AMOUNT")
	private Double unitAmount;
	@Column(name = "TOTAL_AMOUNT")
	private Double totalAmount;
	@Column(name = "DISCOUNT_PERCENTAGE")
	private Double discountPercentage;
	@Column(name = "DISCOUNT_AMOUNT")
	private Double discountAmount;
	@Column(name = "NET_AMOUNT")
	private Double netAmount;
	@Column(name = "PAYER_AMOUNT")
	private Double payerAmount;
	@Column(name = "PATIENT_AMOUNT")
	private Double patientAmount;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Bill getBill() {
		return bill;
	}
	public void setBill(Bill bill) {
		this.bill = bill;
	}
	public Service getService() {
		return service;
	}
	public void setService(Service service) {
		this.service = service;
	}
	public Employee getDoctor() {
		return doctor;
	}
	public void setDoctor(Employee doctor) {
		this.doctor = doctor;
	}
	public Date getServiceDate() {
		return serviceDate;
	}
	public void setServiceDate(Date serviceDate) {
		this.serviceDate = serviceDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Double getUnitAmount() {
		return unitAmount;
	}
	public void setUnitAmount(Double unitAmount) {
		this.unitAmount = unitAmount;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Double getDiscountPercentage() {
		return discountPercentage;
	}
	public void setDiscountPercentage(Double discountPercentage) {
		this.discountPercentage = discountPercentage;
	}
	public Double getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(Double discountAmount) {
		this.discountAmount = discountAmount;
	}
	public Double getNetAmount() {
		return netAmount;
	}
	public void setNetAmount(Double netAmount) {
		this.netAmount = netAmount;
	}
	public Double getPayerAmount() {
		return payerAmount;
	}
	public void setPayerAmount(Double payerAmount) {
		this.payerAmount = payerAmount;
	}
	public Double getPatientAmount() {
		return patientAmount;
	}
	public void setPatientAmount(Double patientAmount) {
		this.patientAmount = patientAmount;
	}
	
	public void setDefaultService(DoctorOrder doctorOrder) {
		this.setDoctor(doctorOrder.getDoctor());
	}
}
