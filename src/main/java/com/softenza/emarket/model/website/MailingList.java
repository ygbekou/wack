package com.softenza.emarket.model.website;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.softenza.emarket.model.BaseEntity;
 
@Entity
@Table(name="MAILING_LIST")
public class MailingList extends BaseEntity {
	
	@Id
	@Column(name ="MAILING_LIST_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String email;
	private int status;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
