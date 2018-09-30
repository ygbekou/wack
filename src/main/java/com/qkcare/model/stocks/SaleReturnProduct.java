package com.qkcare.model.stocks;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.qkcare.model.BaseEntity;
import com.qkcare.model.Product;

@Entity
@Table(name = "SALE_RETURN_PRODUCT")
public class SaleReturnProduct extends BaseEntity {
	
	@Id
	@Column(name = "SALE_RETURN_PRODUCT_ID")
	@GeneratedValue
	private Long id;
	@ManyToOne
	@JoinColumn(name = "SALE_RETURN_ID")
	private SaleReturn saleReturn;
	@ManyToOne
	@JoinColumn(name = "PRODUCT_ID")
	private Product product;
	@Column(name = "QUANTITY")
	private int quantity;
	private int status;
	
	@Transient
	private int originalQuantity;
	
	public SaleReturnProduct() {}
	
	public SaleReturnProduct(Long id, SaleReturn saleReturn, Product product, Integer originalQuantity, Integer quantity) {
		this.id = id;
		this.saleReturn = saleReturn;
		this.product = product;
		this.originalQuantity = originalQuantity;
		this.quantity = quantity;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public SaleReturn getSaleReturn() {
		return saleReturn;
	}
	public void setSaleReturn(SaleReturn saleReturn) {
		this.saleReturn = saleReturn;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

	
	// Transients
	public int getOriginalQuantity() {
		return originalQuantity;
	}
	public void setOriginalQuantity(int originalQuantity) {
		this.originalQuantity = originalQuantity;
	}
	public String getProductName() {
		return this.product != null ? this.product.getName() : "";
	}
	
	public String getProductDescription() {
		return this.product != null ? this.product.getDescription() : "";
	}
	
}
