package com.qkcare.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "ENQUIRY")
public class Enquiry extends BaseEntity {
	
	@Id
	@Column(name = "ENQUIRY_ID")
	@GeneratedValue
	private Long id;
	@ManyToOne
	@JoinColumn(name = "CHECKED_BY")
	private User checkedBy;
	@Column(name = "ENQUIRY_DATETIME")
	private Timestamp enquiryDatetime;
	@Column(name="FIRST_NAME")
	private String firstName;
	@Column(name="LAST_NAME")
	private String lastName;
	@Column(name="MIDDLE_NAME")
	private String middleName;
	@Column(name="IS_READ")
	private String read;
	private String email;
	private String phone;
	private String notes;
	
	private int status;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getCheckedBy() {
		return checkedBy;
	}
	public void setCheckedBy(User checkedBy) {
		this.checkedBy = checkedBy;
	}
	public Timestamp getEnquiryDatetime() {
		return enquiryDatetime;
	}
	public void setEnquiryDatetime(Timestamp enquiryDatetime) {
		this.enquiryDatetime = enquiryDatetime;
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
	public String getRead() {
		return read;
	}
	public void setRead(String read) {
		this.read = read;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
	// Transient variables
	public String getName() {
		return this.getCheckedBy() == null ? "" : this.getCheckedBy().getName();
	}
	
}
