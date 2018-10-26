package com.qkcare.model.mis; 
import java.io.Serializable;
import java.util.Date; 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.qkcare.model.BaseEntity;  

@Table (name="REPORT")
@Entity
public class Report extends BaseEntity implements Comparable<Object>, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "REPORT_ID")
	private Long id;

	@Column(name = "NAME")
	private String name;
	
	@Column(name = "CATEGORY")
	private String category;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name="STATUS")
	private Short status=1;

	public boolean getStatus() {
		return status==1?true:false;
	}

	public void setStatus(boolean status) {
		this.status = (short) (status==true?1:0);
	}

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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setStatus(Short status) {
		this.status = status;
	}
	
	@Override
	public int compareTo(Object obj) { 
		Report aReport = (Report)obj;
		return this.name.compareTo(aReport.getName());
	}

}
