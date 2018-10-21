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
	@Column(name = "UNIT_PRICE")
	private Double unitPrice;
	@Column(name = "DISCOUNT_PERCENTAGE")
	private Double discountPercentage;
	@Column(name = "DISCOUNT_AMOUNT")
	private Double discountAmount;
	@Column(name = "TOTAL_AMOUNT")
	private Double totalAmount;
	
	@Transient
	private int originalQuantity;
	
	public SaleReturnProduct() {}
	
	public SaleReturnProduct(Long id, SaleReturn saleReturn, Product product, 
			Integer originalQuantity, Integer quantity, Double unitPrice) {
		this.id = id;
		this.saleReturn = saleReturn;
		this.product = product;
		this.originalQuantity = originalQuantity;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
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
	public Double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
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
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
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
