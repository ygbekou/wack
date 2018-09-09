package com.qkcare.model.stocks;

import java.sql.Date;
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
import com.qkcare.model.Employee;
import com.qkcare.model.InvestigationTest;

@Entity
@Table(name = "PURCHASE_ORDER")
public class PurchaseOrder extends BaseEntity {
	
	@Id
	@Column(name = "PURCHASE_ORDER_ID")
	@GeneratedValue
	private Long id;
	@ManyToOne
	@JoinColumn(name = "SUPPLIER_ID")
	private Supplier supplier;
	@ManyToOne
	@JoinColumn(name = "REQUESTOR_ID")
	private Employee requestor;
	@ManyToOne
	@JoinColumn(name = "SHIP_TO")
	private Employee shipTo;
	@Column(name = "PURCHASE_ORDER_DATE")
	private Date purchaseOrderDate;
	private String comments;
	private int status;
	
	// Transient
	@Transient
	private List<InvestigationTest> investigationTests;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Supplier getSupplier() {
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	public Employee getRequestor() {
		return requestor;
	}
	public void setRequestor(Employee requestor) {
		this.requestor = requestor;
	}
	public Employee getShipTo() {
		return shipTo;
	}
	public void setShipTo(Employee shipTo) {
		this.shipTo = shipTo;
	}
	public Date getPurchaseOrderDate() {
		return purchaseOrderDate;
	}
	public void setPurchaseOrderDate(Date purchaseOrderDate) {
		this.purchaseOrderDate = purchaseOrderDate;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
	public List<InvestigationTest> getInvestigationTests() {
		return investigationTests;
	}
	public void setInvestigationTests(List<InvestigationTest> investigationTests) {
		this.investigationTests = investigationTests;
	}
	
	
	
}
