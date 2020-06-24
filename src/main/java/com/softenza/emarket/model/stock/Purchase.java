package com.softenza.emarket.model.stock;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.softenza.emarket.model.BaseEntity;
import com.softenza.emarket.model.Employee;
import com.softenza.emarket.model.User;


@Entity
@Table(name="PURCHASE")
public class Purchase extends BaseEntity {
	
	@Id
	@Column(name ="PURCHASE_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "PRODUCT_ID")
	private Product product;
	
	@ManyToOne
	@JoinColumn(name = "PRIMARY_PURCHASER_ID")
	private Employee primaryPurchaser;
	
	@ManyToOne
	@JoinColumn(name = "SECONDARY_PURCHASER_ID")
	private Employee secondaryPurchaser;
	
	@ManyToOne
	@JoinColumn(name = "SUPPLIER_ID")
	private Supplier supplier;
	
	@Column(name = "PURCHASE_DATE")
	private Date purchaseDate;
	
	@Column(name = "UNIT_AMOUNT")
	private Double unitAmount;
	
	private Integer quantity;;
	
	@Column(name = "TOTAL_AMOUNT")
	private Double totalAmount;
	
	private String receipt1;
	
	private String receipt2;
	
	private String receipt3;
	
	private String description;

	private int status;
	
	@ManyToOne
	@JoinColumn(name = "MOD_BY", referencedColumnName = "USER_ID", insertable=false, updatable=false)
	private User modifier;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Employee getPrimaryPurchaser() {
		return primaryPurchaser;
	}

	public void setPrimaryPurchaser(Employee primaryPurchaser) {
		this.primaryPurchaser = primaryPurchaser;
	}

	public Employee getSecondaryPurchaser() {
		return secondaryPurchaser;
	}

	public void setSecondaryPurchaser(Employee secondaryPurchaser) {
		this.secondaryPurchaser = secondaryPurchaser;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public Double getUnitAmount() {
		return unitAmount;
	}

	public void setUnitAmount(Double unitAmount) {
		this.unitAmount = unitAmount;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getReceipt1() {
		return receipt1;
	}

	public void setReceipt1(String receipt1) {
		this.receipt1 = receipt1;
	}

	public String getReceipt2() {
		return receipt2;
	}

	public void setReceipt2(String receipt2) {
		this.receipt2 = receipt2;
	}

	public String getReceipt3() {
		return receipt3;
	}

	public void setReceipt3(String receipt3) {
		this.receipt3 = receipt3;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getProductName() {
		return this.getProduct().getName();
	}
	
	public String getPrimaryPurchaserName() {
		return this.getPrimaryPurchaser().getName();
	}
	
	public String getSecondaryPurchaserName() {
		return this.getSecondaryPurchaser().getName();
	}
	
	public String getSupplierName() {
		return this.getSupplier().getName();
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getModifierName() {
		return this.modifier != null ? this.modifier.getName() : "";
	}
	
	public String getStatusDesc() {
		return this.status == 0 ? "Actif" : "Inactif";
	}
}
