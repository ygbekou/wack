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

@Entity
@Table(name = "RECEIVE_ORDER")
public class ReceiveOrder extends BaseEntity {
	
	@Id
	@Column(name = "RECEIVE_ORDER_ID")
	@GeneratedValue
	private Long id;
	@ManyToOne
	@JoinColumn(name = "PURCHASE_ORDER_ID")
	private PurchaseOrder purchaseOrder;
	@Column(name = "DELIVERY_NOTE")
	private String deliveryNote;
	@Column(name = "DELIVERY_DATE")
	private Date deliveryDate;
	@Column(name = "PACKAGING_SLIP")
	private String packagingSlip;
	@Column(name = "AP_REFERENCE")
	private String apReferene;
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
	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}
	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}
	public String getDeliveryNote() {
		return deliveryNote;
	}
	public void setDeliveryNote(String deliveryNote) {
		this.deliveryNote = deliveryNote;
	}
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public String getPackagingSlip() {
		return packagingSlip;
	}
	public void setPackagingSlip(String packagingSlip) {
		this.packagingSlip = packagingSlip;
	}
	public String getApReferene() {
		return apReferene;
	}
	public void setApReferene(String apReferene) {
		this.apReferene = apReferene;
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
