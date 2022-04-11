package com.wack.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.wack.util.Utils;


/**
 * The persistent class for the poll_desc database table.
 * 
 */
@Entity
@Table(name = "JOB_POSITION_DESC")
@NamedQuery(name = "JobPositionDesc.findAll", query = "SELECT j FROM JobPositionDesc j")
public class JobPositionDesc extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID") 
	private Long id;

	private String language;

	private String title;
	
	private String description;

	@ManyToOne
	@JoinColumn(name = "JOB_POSITION_ID") 
	private JobPosition jobPosition;
	

	@Transient
	private int jobAppliCnt;
	
	@Override
	public Long getId() {
		return this.id;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public void setId(Long id) {
		this.id = id;
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

	public JobPosition getJobPosition() {
		return jobPosition;
	}

	public void setJobPosition(JobPosition jobPosition) {
		this.jobPosition = jobPosition;
	}

	public int getJobAppliCnt() {
		return jobAppliCnt;
	}

	public void setJobAppliCnt(int jobAppliCnt) {
		this.jobAppliCnt = jobAppliCnt;
	}

	@Transient
	public String getShortDesc() {
		return description != null && description.length() > 200 ? Utils.truncateHTML(description, 200, null)
				: description;
	}
	
}