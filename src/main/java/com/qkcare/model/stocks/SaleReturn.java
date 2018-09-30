package com.qkcare.model.stocks;

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
	private int status;
	
	
	// Transient
	@Transient
	private List<ReceiveOrderProduct> receiveOrderProducts;
	
	
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public List<ReceiveOrderProduct> getReceiveOrderProducts() {
		return receiveOrderProducts;
	}
	public void setReceiveOrderProducts(List<ReceiveOrderProduct> receiveOrderProducts) {
		this.receiveOrderProducts = receiveOrderProducts;
	}
	
	
}
