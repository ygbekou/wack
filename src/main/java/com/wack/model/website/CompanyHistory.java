package com.wack.model.website;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.wack.model.BaseEntity;
import com.wack.util.Utils;

@Entity
@Table(name="COMPANY_HISTORY")
public class CompanyHistory extends BaseEntity {
	
	@Id
	@Column(name ="COMPANY_HISTORY_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer year;
	
	private String title;
	
	private String description;
	
	private String picture;
	
	private Integer status;
	
	private String language;

	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getStatusDesc() {
		return status == 0 ? "Actif" : "Inactif";
	}
	
	@Transient
	public String getShortMessage() {
		return description != null && description.length() > 100 ? Utils.truncateHTML(description,100,null) : description;
	}

	@Transient
	public String getMediumMessage() {
		return description != null && description.length() > 200 ? Utils.truncateHTML(description,200,null) : description;
	}
	
}
