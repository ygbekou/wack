package com.qkcare.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT")
public class Product extends BaseEntity {
	
	@Id
	@Column(name = "PRODUCT_ID")
	@GeneratedValue
	private Long id;
	@OneToOne
	@JoinColumn(name = "CATEGORY_ID")
	private Category category;
	@ManyToOne
	@JoinColumn(name = "MANUFACTURER_ID")
	private Manufacturer manufacturer;
	@ManyToOne
	@JoinColumn(name = "BRAND_ID")
	private Brand brand;
	private String name;
	private String description;
	private Double price;
	private int status;
	
	public Product() {}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Manufacturer getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}
	public Brand getBrand() {
		return brand;
	}
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
	// TRansient fields for UI
	
	public String getCategoryName() {
		return this.category.getName();
	}
	public String getManufacturerName() {
		return this.manufacturer.getName();
	}
}
