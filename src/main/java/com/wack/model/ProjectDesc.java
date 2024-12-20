package com.wack.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.wack.util.Utils;


@Entity
@Table(name = "PROJECT_DESC")
@NamedQuery(name = "ProjectDesc.findAll", query = "SELECT e FROM ProjectDesc e")
public class ProjectDesc extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID") 
	private Long id;
	
	private String title;

	private String language;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "SPONSORS")
	private String sponsors;
	
	@Column(name = "CONTRIBUTION")
	private Double contribution;
	
	@Column(name = "OBJECTIF")
	private String objectif;

	@Column(name = "INOVATION")
	private String inovation;

	@Column(name = "EXISTANT")
	private String existant;

	@Column(name = "RESOURCE")
	private String resource;

	@Column(name = "EXECUTION")
	private String execution;

	@Column(name = "CONSTRAINTS")
	private String constraints;

	@Column(name = "FEASIBILITY")
	private String feasibility;

	@Column(name = "BUDGET_LINE")
	private String budgetLine;

	@Column(name = "RESULT")
	private String result;

	@Column(name = "DURATION")
	private String duration;

	@ManyToOne
	@JoinColumn(name = "PROJECT_ID") 
	private Project project;
	
	@Override
	public Long getId() {
		return this.id;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSponsors() {
		return sponsors;
	}

	public void setSponsors(String sponsors) {
		this.sponsors = sponsors;
	}
	public Double getContribution() {
		return contribution;
	}

	public void setContribution(Double contribution) {
		this.contribution = contribution;
	}

	public String getObjectif() {
		return objectif;
	}

	public void setObjectif(String objectif) {
		this.objectif = objectif;
	}

	public String getInovation() {
		return inovation;
	}

	public void setInovation(String inovation) {
		this.inovation = inovation;
	}

	public String getExistant() {
		return existant;
	}

	public void setExistant(String existant) {
		this.existant = existant;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public String getExecution() {
		return execution;
	}

	public void setExecution(String execution) {
		this.execution = execution;
	}

	public String getConstraints() {
		return constraints;
	}

	public void setConstraints(String constraints) {
		this.constraints = constraints;
	}

	public String getFeasibility() {
		return feasibility;
	}

	public void setFeasibility(String feasibility) {
		this.feasibility = feasibility;
	}

	public String getBudgetLine() {
		return budgetLine;
	}

	public void setBudgetLine(String budgetLine) {
		this.budgetLine = budgetLine;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@Transient
	public String getShortDesc() {
		return description != null && description.length() > 200 ? Utils.truncateHTML(description, 200, null)
				: description;
	}
	
	@Transient
	public String getShortDescription() {
		return description != null && description.length() > 200 ? Utils.truncateHTML(description, 200, null)
				: description;
	}
	
	@Transient
	public String getShortMessage() {
		return description != null && description.length() > 100 ? Utils.truncateHTML(description,100,null) : description;
	}

	@Transient
	public String getMediumMessage() {
		return description != null && description.length() > 200 ? Utils.truncateHTML(description,200,null) : description;
	}
	
	
}