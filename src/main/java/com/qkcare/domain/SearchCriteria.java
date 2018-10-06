package com.qkcare.domain;

import java.io.Serializable;

public class SearchCriteria implements Serializable {
	
	Long id;
	String lastName;
	String firstName;
	String birthDate;
	Long departmentId;
	Long hospitalLocationId;
	Long doctorId;
	
	public SearchCriteria() {}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	public Long getHospitalLocationId() {
		return hospitalLocationId;
	}
	public void setHospitalLocationId(Long hospitalLocationId) {
		this.hospitalLocationId = hospitalLocationId;
	}
	public Long getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}
	
	public boolean hasDoctorId() {
		return getDoctorId() != null && getDoctorId() > 0;
	}
	
	public boolean hasHospitalLocationId() {
		return getHospitalLocationId() != null && getHospitalLocationId() > 0;
	}
	
	public boolean hasDepartmentId() {
		return getDepartmentId() != null && getDepartmentId() > 0;
	}
}
