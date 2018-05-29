package com.qkcare.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "INSURANCE")
public class Insurance extends BaseEntity {
	
	@Id
	@Column(name = "INSURANCE_ID")
	@GeneratedValue
	private Long id;
	private String name;
	@Column(name = "INSURANCE_NUMBER")
	private String insuranceNumber;
	@Column(name = "INSURANCE_CODE")
	private String insuranceCode;
	@Column(name = "SERVICE_TAX")
	private Double serviceTax;
	private Double discount;
	private String remark;
	@Column(name = "HOSPITAL_RATE")
	private Double hospitalRate;
	@Column(name = "INSURANCE_RATE")
	private Double insuranceRate;
	private Double total;
	private int status;
	
	
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
	public String getInsuranceNumber() {
		return insuranceNumber;
	}
	public void setInsuranceNumber(String insuranceNumber) {
		this.insuranceNumber = insuranceNumber;
	}
	public String getInsuranceCode() {
		return insuranceCode;
	}
	public void setInsuranceCode(String insuranceCode) {
		this.insuranceCode = insuranceCode;
	}
	public Double getServiceTax() {
		return serviceTax;
	}
	public void setServiceTax(Double serviceTax) {
		this.serviceTax = serviceTax;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Double getHospitalRate() {
		return hospitalRate;
	}
	public void setHospitalRate(Double hospitalRate) {
		this.hospitalRate = hospitalRate;
	}
	public Double getInsuranceRate() {
		return insuranceRate;
	}
	public void setInsuranceRate(Double insuranceRate) {
		this.insuranceRate = insuranceRate;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
