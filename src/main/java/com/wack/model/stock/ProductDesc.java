package com.wack.model.stock;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.wack.model.BaseEntity;


@Entity
@Table(name = "PRODUCT_DESC")
public class ProductDesc extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PRODUCT_DESC_ID") 
	private Long id; 
	
	@ManyToOne
	@JoinColumn(name = "PRODUCT_ID") 
	private Product product;

	private String description;

	private String language;

	private String name;

	
	public ProductDesc() {
	}
 
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getDescription() {
		return this.description;
	}

	@Override
	public Long getId() {
		return this.id;
	}
 
	public String getName() {
		return this.name;
	} 
	
	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

}