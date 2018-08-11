package com.qkcare.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "VISIT_DIAGNOSIS")
public class VisitDiagnosis extends BaseEntity {
	
	@Id
	@Column(name = "VISIT_DIAGNOSIS_ID")
	@GeneratedValue
	private Long id;
	@ManyToOne
	@JoinColumn(name = "VISIT_ID")
	private Visit visit;
	@ManyToOne
	@JoinColumn(name = "DIAGNOSIS_ID")
	private Diagnosis diagnosis;
	private String instructions;

	
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
	public Diagnosis getDiagnosis() {
		return diagnosis;
	}
	public void setDiagnosis(Diagnosis diagnosis) {
		this.diagnosis = diagnosis;
	}
	public String getInstructions() {
		return instructions;
	}
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

}
