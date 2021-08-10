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
@Table(name = "POLL_DESC")
@NamedQuery(name = "PollDesc.findAll", query = "SELECT i FROM PollDesc i")
public class PollDesc extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "POLL_DESC_ID") 
	private Long id;

	private String language;

	private String title;
	
	private String description;
	
	@Column(name = "END_NOTE") 
	private String endNote;

	@ManyToOne
	@JoinColumn(name = "POLL_ID") 
	private Poll poll;
	
	@Transient
	private PollTypeDesc pollTypeDesc;

	public PollDesc() {}

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


	public String getEndNote() {
		return endNote;
	}


	public void setEndNote(String endNote) {
		this.endNote = endNote;
	}


	public Poll getPoll() {
		return poll;
	}


	public void setPoll(Poll poll) {
		this.poll = poll;
	}

	public PollTypeDesc getPollTypeDesc() {
		return pollTypeDesc;
	}

	public void setPollTypeDesc(PollTypeDesc pollTypeDesc) {
		this.pollTypeDesc = pollTypeDesc;
	}

	public String getPollTypeDescName() {
		return pollTypeDesc != null ? pollTypeDesc.getName() : "";
	}
}