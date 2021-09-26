package com.wack.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CONTACT_US_MESSAGE")
public class ContactUsMessage extends BaseEntity {
	
	@Id
	@Column(name = "CONTACTUS_MESSAGE_ID")
	@GeneratedValue
	private Long id;
	private String name;
	private String email;
	private String phone;
	private String message;
	
	@ManyToOne
	@JoinColumn(name = "NEWS_ID")
	private News news;
	
	@ManyToOne
	@JoinColumn(name = "PROJECT_ID")
	private Project project;
	

	public ContactUsMessage() {}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
