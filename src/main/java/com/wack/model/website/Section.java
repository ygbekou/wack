package com.wack.model.website;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.wack.model.BaseEntity;

@Entity
@Table(name="SECTION")
public class Section extends BaseEntity {
	
	@Id
	@Column(name ="SECTION_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String title;
	
	private String description;
	
	@Column(name ="PICTURE")
	private String picture;
	
	private Integer status;
	
	@Column(name="SHOW_IN_MENU")
	private String showInMenu;
	
	private String language;

	
	
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

	public String getShowInMenu() {
		return showInMenu;
	}

	public void setShowInMenu(String showInMenu) {
		this.showInMenu = showInMenu;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
	
	public String getSectionLabel() {
		return language + " - " + title;
	}
	
	public String getStatusDesc() {
		return status == 0 ? "Actif" : "Inactif";
	}
	
}
