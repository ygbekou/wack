package com.qkcare.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "CASE_STUDY")
public class CaseStudy extends BaseEntity {
	
	@Id
	@Column(name = "CASE_STUDY_ID")
	@GeneratedValue
	private Long id;
	@ManyToOne
	@JoinColumn(name = "PATIENT_ID")
	private Patient patient;
	@Column(name = "FOOD_ALLERGIES")
	private String foodAllergies;
	@Column(name = "TENDENCY_BLEED")
	private String tendencyBleed;
	@Column(name = "HEART_DISEASES")
	private String heartDisease;
	@Column(name = "HIGH_BLOOD_PRESSURE")
	private String highBloodPressure;
	private String diabetic;
	private String surgery;
	private String accident;
	private String others;
	@Column(name = "FAMILY_MEDICAL_HISTORY")
	private String familyMedicalHistory;
	@Column(name = "CURRENT_MEDICATION")
	private String currentMedication;
	@Column(name = "FEMALE_PREGNANCY")
	private String femalePregnancy;
	@Column(name = "BREAST_FEEDING")
	private String breastFeeding;
	@Column(name = "HEALTH_INSURANCE")
	private String healthInsurance;
	@Column(name = "LOW_INCOME")
	private String lowIncome;
	private String reference;
	private int status;
	
	public CaseStudy() {}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public String getFoodAllergies() {
		return foodAllergies;
	}
	public void setFoodAllergies(String foodAllergies) {
		this.foodAllergies = foodAllergies;
	}
	public String getTendencyBleed() {
		return tendencyBleed;
	}
	public void setTendencyBleed(String tendencyBleed) {
		this.tendencyBleed = tendencyBleed;
	}
	public String getHeartDisease() {
		return heartDisease;
	}
	public void setHeartDisease(String heartDisease) {
		this.heartDisease = heartDisease;
	}
	public String getHighBloodPressure() {
		return highBloodPressure;
	}
	public void setHighBloodPressure(String highBloodPressure) {
		this.highBloodPressure = highBloodPressure;
	}
	public String getDiabetic() {
		return diabetic;
	}
	public void setDiabetic(String diabetic) {
		this.diabetic = diabetic;
	}
	public String getSurgery() {
		return surgery;
	}
	public void setSurgery(String surgery) {
		this.surgery = surgery;
	}
	public String getAccident() {
		return accident;
	}
	public void setAccident(String accident) {
		this.accident = accident;
	}
	public String getOthers() {
		return others;
	}
	public void setOthers(String others) {
		this.others = others;
	}
	public String getFamilyMedicalHistory() {
		return familyMedicalHistory;
	}
	public void setFamilyMedicalHistory(String familyMedicalHistory) {
		this.familyMedicalHistory = familyMedicalHistory;
	}
	public String getCurrentMedication() {
		return currentMedication;
	}
	public void setCurrentMedication(String currentMedication) {
		this.currentMedication = currentMedication;
	}
	public String getFemalePregnancy() {
		return femalePregnancy;
	}
	public void setFemalePregnancy(String femalePregnancy) {
		this.femalePregnancy = femalePregnancy;
	}
	public String getBreastFeeding() {
		return breastFeeding;
	}
	public void setBreastFeeding(String breastFeeding) {
		this.breastFeeding = breastFeeding;
	}
	public String getHealthInsurance() {
		return healthInsurance;
	}
	public void setHealthInsurance(String healthInsurance) {
		this.healthInsurance = healthInsurance;
	}
	public String getLowIncome() {
		return lowIncome;
	}
	public void setLowIncome(String lowIncome) {
		this.lowIncome = lowIncome;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

	}
