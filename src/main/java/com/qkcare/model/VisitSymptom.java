package com.qkcare.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "VISIT_SYMPTOM")
public class VisitSymptom extends BaseEntity {
	
	@Id
	@Column(name = "VISIT_SYMPTOM_ID")
	@GeneratedValue
	private Long id;
	@ManyToOne
	@JoinColumn(name = "VISIT_ID")
	private Visit visit;
	@ManyToOne
	@JoinColumn(name = "SYMPTOM_ID")
	private Symptom symptom;
	
	public VisitSymptom() {}
	
	public VisitSymptom(Long visitId, Long symptomId) {
		this.visit = new Visit(visitId);
		this.symptom = new Symptom(symptomId);
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
	public Symptom getSymptom() {
		return symptom;
	}
	public void setSymptom(Symptom symptom) {
		this.symptom = symptom;
	}
	
	
}
