package com.qkcare.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "BED_ASSIGNMENT")
public class BedAssignment extends BaseEntity {
	
	@Id
	@Column(name = "BED_ASSIGNMENT_ID")
	@GeneratedValue
	private Long id;
	@ManyToOne
	@JoinColumn(name = "ADMISSION_ID")
	private Admission admission;
	@ManyToOne
	@JoinColumn(name = "BED_ID")
	private Bed bed;
	@Column(name = "START_DATE")
	private Timestamp startDate;
	@Column(name = "END_DATE")
	private Timestamp endDate;
	
	@Transient
	private Bed transferBed;
	@Transient
	private Timestamp transferDate;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Admission getAdmission() {
		return admission;
	}
	public void setAdmission(Admission admission) {
		this.admission = admission;
	}
	public Bed getBed() {
		return bed;
	}
	public void setBed(Bed bed) {
		this.bed = bed;
	}
	public Timestamp getStartDate() {
		return startDate;
	}
	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}
	public Timestamp getEndDate() {
		return endDate;
	}
	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}
	
	public Bed getTransferBed() {
		return transferBed;
	}
	public void setTransferBed(Bed transferBed) {
		this.transferBed = transferBed;
	}
	public Timestamp getTransferDate() {
		return transferDate;
	}
	public void setTransferDate(Timestamp transferDate) {
		this.transferDate = transferDate;
	}

	
}
