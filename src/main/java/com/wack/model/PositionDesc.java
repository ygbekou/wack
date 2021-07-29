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


/**
 * The persistent class for the position_desc database table.
 * 
 */
@Entity
@Table(name = "POSITION_DESC")
@NamedQuery(name = "PositionDesc.findAll", query = "SELECT i FROM PositionDesc i")
public class PositionDesc extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "POSITION_DESC_ID") 
	private Long id;

	private String description;

	private String language;

	private String name;

	@ManyToOne
	@JoinColumn(name = "POSITION_ID") 
	private Position position;
	

	public PositionDesc() {}


	public String getDescription() {
		return this.description;
	}

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

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}