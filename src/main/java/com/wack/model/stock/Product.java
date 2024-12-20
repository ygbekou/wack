package com.wack.model.stock;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.wack.model.BaseEntity;
import com.wack.model.User;

@Entity
@Table(name="PRODUCT")
public class Product extends BaseEntity {
	
	@Id
	@Column(name ="PRODUCT_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(optional = true)
	@JoinColumn(name = "PRD_CATEGORY_ID")
	private PrdCategory prdCategory;
	
	private int status;
	
	@ManyToOne
	@JoinColumn(name = "MOD_BY", referencedColumnName = "USER_ID", insertable=false, updatable=false)
	private User modifier;
	
	@OneToMany 
	@JoinColumn(name = "PRODUCT_ID", updatable=false )
	@Fetch(FetchMode.JOIN)
	private Set<ProductDesc> productDescs = new HashSet<>();
	
	@Transient
	String name;
	
	@Transient
	String description;
	
	@Transient
	String categoryName;
	
	@Transient
	String parentCategoryName;
	
	@Transient
	PrdCategoryDesc prdCategoryDesc;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PrdCategory getPrdCategory() {
		return prdCategory;
	}

	public void setPrdCategory(PrdCategory prdCategory) {
		this.prdCategory = prdCategory;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Set<ProductDesc> getProductDescs() {
		return productDescs;
	}

	public void setProductDescs(Set<ProductDesc> productDescs) {
		this.productDescs = productDescs;
	}

	public String getModifierName() {
		return this.modifier != null ? this.modifier.getName() : "";
	}
	
	public String getStatusDesc() {
		return this.status == 0 ? "Actif" : "Inactif";
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

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getParentCategoryName() {
		return parentCategoryName;
	}

	public void setParentCategoryName(String parentCategoryName) {
		this.parentCategoryName = parentCategoryName;
	}

	public PrdCategoryDesc getPrdCategoryDesc() {
		return prdCategoryDesc;
	}

	public void setPrdCategoryDesc(PrdCategoryDesc prdCategoryDesc) {
		this.prdCategoryDesc = prdCategoryDesc;
	}
}
