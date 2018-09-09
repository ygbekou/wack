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
	@JoinColumn(name = "ADMISSION_ID")
	private Admission admission;
	@OneToOne
	@JoinColumn(name = "VISIT_ID")
	private Visit visit;
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
	public Admission getAdmission() {
		return admission;
	}
	public void setAdmission(Admission admission) {
		this.admission = admission;
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
	public Visit getVisit() {
		return visit;
	}
	public void setVisit(Visit visit) {
		this.visit = visit;
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
		if (this.visit != null) {
			return this.getVisit().getPatient().getMatricule();
		}
		if (this.admission != null) {
			return this.getAdmission().getPatient().getMatricule();
		}
		return "";
		
	}
	
	public String getPatientName() {
		if (this.visit != null) {
			return this.getVisit().getPatient().getName();
		}
		if (this.admission != null) {
			return this.getAdmission().getPatient().getName();
		}
		return "";
	}
}
