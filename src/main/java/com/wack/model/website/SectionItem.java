package com.wack.model.website;

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
import com.wack.util.Utils;

@Entity
@Table(name = "SECTION_ITEM")
public class SectionItem extends BaseEntity {

	@Id
	@Column(name = "SECTION_ITEM_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "SECTION_ID")
	private Section section;

	private String title;

	private String name;

	private String description;

	@Column(name = "PICTURE")
	private String picture;

	private String summary;

	private Integer status;
	
	@Column(name ="`RANK`")
	private Integer rank;

	private String language;
	
	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
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

	public String getSectionName() {
		return this.getSection().getName();
	}

	public String getStatusDesc() {
		return status == 0 ? "Actif" : "Inactif";
	}

	// Transient
	public String getText1() {
		String[] texts = description == null ? null : description.split("\\|");

		return texts != null && texts.length > 0 ? texts[0] : "";
	}

	public String getText2() {
		String[] texts = description == null ? null : description.split("\\|");

		return texts != null && texts.length > 1 ? texts[1] : "";
	}

	public String getText3() {
		String[] texts = description == null ? null : description.split("\\|");

		return texts != null && texts.length > 2 ? texts[2] : "";
	}

	@Transient
	public String getShortMessage() {
		return description != null && description.length() > 100 ? Utils.truncateHTML(description, 100, null)
				: description;
	}

	@Transient
	public String getMediumMessage() {
		return description != null && description.length() > 200 ? Utils.truncateHTML(description, 200, null)
				: description;
	}
}
