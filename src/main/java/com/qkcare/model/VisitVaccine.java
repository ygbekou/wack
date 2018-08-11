package com.qkcare.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "VISIT_VACCINE")
public class VisitVaccine extends BaseEntity {
	
	@Id
	@Column(name = "VISIT_VACCINE_ID")
	@GeneratedValue
	private Long id;
	@ManyToOne
	@JoinColumn(name = "VISIT_ID")
	private Visit visit;
	@ManyToOne
	@JoinColumn(name = "VACCINE_ID")
	private Vaccine vaccine;
	@Column(name = "GIVEN_DATE")
	private Date givenDate;
	
	public VisitVaccine() {}
	
	public VisitVaccine(Long visitId, Long vaccineId) {
		this.visit = new Visit(visitId);
		this.vaccine = new Vaccine(vaccineId);
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Visit getVisit() {
		return visit;
	}
	public void setVisit(Visit visit) {
		this.visit = visit;
	}
	public Vaccine getVaccine() {
		return vaccine;
	}
	public void setVaccine(Vaccine vaccine) {
		this.vaccine = vaccine;
	}
	public Date getGivenDate() {
		return givenDate;
	}
	public void setGivenDate(Date givenDate) {
		this.givenDate = givenDate;
	}
	
	
}
