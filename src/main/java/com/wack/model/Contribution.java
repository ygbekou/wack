package com.wack.model;

import java.util.Date;

public class Contribution {
	private Date date;
	private String project;
	private String member;
	private Double amount;
	private Double fee;
	private Short anonymous=0;	
	private Long tranId;

	
	public Double getFee() {
		return fee;
	}


	public void setFee(Double fee) {
		this.fee = fee;
	}


	public Long getTranId() {
		return tranId;
	}


	public void setTranId(Long tranId) {
		this.tranId = tranId;
	}


	public boolean getAnonymous() {
		return anonymous==1?true:false;
	}


	public void setAnonymous(boolean anonymous) {
		this.anonymous = (short) (anonymous==true?1:0);
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getMember() {
		return member;
	}

	public void setMember(String member) {
		this.member = member;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

}
