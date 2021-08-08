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

import com.wack.model.BaseEntity;


/**
 * The persistent class for the poll_type_desc database table.
 * 
 */
@Entity
@Table(name = "POLL_TYPE_DESC")
@NamedQuery(name = "PollTypeDesc.findAll", query = "SELECT i FROM PollTypeDesc i")
public class PollTypeDesc extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "POLL_TYPE_DESC_ID") 
	private Long id;

	private String language;

	private String name;

	@ManyToOne
	@JoinColumn(name = "POLL_TYPE_ID") 
	private PollType pollType;
	

	public PollTypeDesc() {}


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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PollType getPollType() {
		return pollType;
	}

	public void setPollType(PollType pollType) {
		this.pollType = pollType;
	}

	public void setId(Long id) {
		this.id = id;
	}

}