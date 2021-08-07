package com.wack.poll;

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

import com.wack.model.BaseEntity;


/**
 * The persistent class for the poll_desc database table.
 * 
 */
@Entity
@Table(name = "POLL_CHOICE_DESC")
@NamedQuery(name = "PollChoiceDesc.findAll", query = "SELECT i FROM PollChoiceDesc i")
public class PollChoiceDesc extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "POLL_CHOICE_DESC_ID") 
	private Long id;

	private String language;
	
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "POLL_CHOICE_ID") 
	private PollChoice pollChoice;
	
	@Transient
	private PollQuestionDesc pollQuestionDesc;
	

	public PollChoiceDesc() {}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public PollChoice getPollChoice() {
		return pollChoice;
	}

	public void setPollChoice(PollChoice pollChoice) {
		this.pollChoice = pollChoice;
	}

	public PollQuestionDesc getPollQuestionDesc() {
		return pollQuestionDesc;
	}

	public void setPollQuestionDesc(PollQuestionDesc pollQuestionDesc) {
		this.pollQuestionDesc = pollQuestionDesc;
	}

	
}