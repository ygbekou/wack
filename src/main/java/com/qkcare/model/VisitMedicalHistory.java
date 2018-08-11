package com.qkcare.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "VISIT_MEDICALHISTORY")
public class VisitMedicalHistory extends BaseEntity {
	
	@Id
	@Column(name = "VISIT_MEDICALHISTORY_ID")
	@GeneratedValue
	private Long id;
	@ManyToOne
	@JoinColumn(name = "VISIT_ID")
	private Visit visit;
	@ManyToOne
	@JoinColumn(name = "MEDICALHISTORY_ID")
	private MedicalHistory medicalHistory;
	
	public VisitMedicalHistory() {}
	
	public VisitMedicalHistory(Long visitId, Long medicalHistoryId) {
		this.visit = new Visit(visitId);
		this.medicalHistory = new MedicalHistory(medicalHistoryId);
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
	public MedicalHistory getMedicalHistory() {
		return medicalHistory;
	}
	public void setMedicalHistory(MedicalHistory medicalHistory) {
		this.medicalHistory = medicalHistory;
	}
}
