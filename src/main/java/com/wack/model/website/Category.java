package com.wack.model.website;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.wack.model.BaseEntity;

@Entity
@Table(name = "CATEGORY")
public class Category extends BaseEntity {
	
	@Id
	@GeneratedValue
	@Column(name = "CATEGORY_ID")
	private Long id;	
	@ManyToOne
	@JoinColumn(name = "PARENT_CATEGORY_ID", nullable = true)
	private Category parent;
	private String language;
	private String name;
	private String description;
	private int status;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Category getParent() {
		return parent;
	}
	public void setParent(Category parent) {
		this.parent = parent;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
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
	
	// Transient
	public String getStatusDesc() {
		return status == 0 ? "Active" : "Inactive";
	}
}
