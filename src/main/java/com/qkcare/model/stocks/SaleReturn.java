package com.qkcare.model.stocks;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.qkcare.model.BaseEntity;

@Entity
@Table(name = "SALE_RETURN")
public class SaleReturn extends BaseEntity {
	
	@Id
	@Column(name = "SALE_RETURN_ID")
	@GeneratedValue
	private Long id;
	@ManyToOne
	@JoinColumn(name = "PATIENT_SALE_ID")
	private PatientSale patientSale;
	private String comments;
	@Column(name = "RETURN_DATETIME")
	private Timestamp returnDatetime;
	@Column(name = "SUB_TOTAL")
	private Double subTotal;
	private Double taxes;
	private Double discount;
	@Column(name = "GRAND_TOTAL")
	private Double grandTotal;
	private int status;
	
	
	// Transient
	@Transient
	private List<SaleReturnProduct> saleReturnProducts;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public PatientSale getPatientSale() {
		return patientSale;
	}
	public void setPatientSale(PatientSale patientSale) {
		this.patientSale = patientSale;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Timestamp getReturnDatetime() {
		return returnDatetime;
	}
	public void setReturnDatetime(Timestamp returnDatetime) {
		this.returnDatetime = returnDatetime;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	// Transient
	public List<SaleReturnProduct> getSaleReturnProducts() {
		return saleReturnProducts;
	}
	public void setSaleReturnProducts(List<SaleReturnProduct> saleReturnProducts) {
		this.saleReturnProducts = saleReturnProducts;
	}
	
	public Long getPatientSaleId() {
		return this.getPatientSale().getId();
	}
	public Timestamp getPatientSaleDatetime() {
		return this.getPatientSale().getSaleDatetime();
	}
	
}
