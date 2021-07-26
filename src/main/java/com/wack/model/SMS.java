package com.wack.model;


import javax.persistence.*;
@Table(name = "SMS")
@Entity
public class SMS extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SMS_ID")
	private Long id;

	@Column(name = "BODY")
	private String body;

	@Column(name = "RESPONSE")
	private String response;
	
	@Column(name = "PHONE")
	private String phone;
	
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "SMPP_ID")
	private SMPP smpp;
	
	@Column(name="STATUS")
	private Short status=0;
	

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public SMPP getSmpp() {
		return smpp;
	}

	public void setSmpp(SMPP smpp) {
		this.smpp = smpp;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return id;
	}  
	
	
}
