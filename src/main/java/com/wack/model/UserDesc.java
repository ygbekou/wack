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
@Table(name = "USER_DESC")
@NamedQuery(name = "UserDesc.findAll", query = "SELECT e FROM UserDesc e")
public class UserDesc extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID") 
	private Long id;

	private String language;

	private String resume;
	
	@Column(name = "SHORT_RESUME")
	private String shortResume;

	@ManyToOne
	@JoinColumn(name = "USER_ID") 
	private User user;
	
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

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public String getShortResume() {
		return shortResume;
	}

	public void setShortResume(String shortResume) {
		this.shortResume = shortResume;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}	
}