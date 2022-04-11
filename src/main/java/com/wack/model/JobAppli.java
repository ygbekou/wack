package com.wack.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "JOB_APPLI")
public class JobAppli extends BaseEntity   implements Comparable<Object>, Serializable  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "JOB_POSITION_ID") 
	private JobPosition jobPosition;
	
	@ManyToOne
	@JoinColumn(name = "APPLICANT_ID") 
	private User applicant;
	
	@Column(name = "FIRST_NAME") 
	private String firstName;
	
	@Column(name = "LAST_NAME") 
	private String lastName;
	
	@Column(name = "MIDDLE_NAME") 
	private String middleName;
	
	private String phone;
	
	private String email;
	
	private String notes;
	
	private String doc;
	
	private Integer status = 1;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public JobPosition getJobPosition() {
		return jobPosition;
	}

	public void setJobPosition(JobPosition jobPosition) {
		this.jobPosition = jobPosition;
	}

	public User getApplicant() {
		return applicant;
	}

	public void setApplicant(User applicant) {
		this.applicant = applicant;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getDoc() {
		return doc;
	}

	public void setDoc(String doc) {
		this.doc = doc;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public List<String> getChildEntities() {
		return Collections.EMPTY_LIST;
	}
	
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		if (this.id != null && o != null){
			if(this.id<((JobAppli) (o)).getId()){
				return 1;
			}else{
				return -1;
			} 
		}
		return 0;
	}


}
