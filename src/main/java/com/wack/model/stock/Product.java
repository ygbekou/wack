package com.wack.model.stock;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.wack.model.BaseEntity;
import com.wack.model.User;

@Entity
@Table(name="PRODUCT")
public class Product extends BaseEntity {
	
	@Id
	@Column(name ="PRODUCT_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
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
