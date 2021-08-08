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
@Table(name = "POLL_QUESTION_DESC")
@NamedQuery(name = "PollQuestionDesc.findAll", query = "SELECT i FROM PollQuestionDesc i")
public class PollQuestionDesc extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "POLL_QUESTION_DESC_ID") 
	private Long id;

	private String language;
	
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "POLL_QUESTION_ID") 
	private PollQuestion pollQuestion;
	
	@Transient
	private PollDesc pollDesc;
	

	public PollQuestionDesc() {}

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

	public PollQuestion getPollQuestion() {
		return pollQuestion;
	}

	public void setPollQuestion(PollQuestion pollQuestion) {
		this.pollQuestion = pollQuestion;
	}

	public PollDesc getPollDesc() {
		return pollDesc;
	}

	public void setPollDesc(PollDesc pollDesc) {
		this.pollDesc = pollDesc;
	}
}