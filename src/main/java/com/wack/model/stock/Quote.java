package com.wack.model.stock;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.wack.model.BaseEntity;
import com.wack.model.Employee;


@Entity
@Table(name="QUOTE")
public class Quote extends BaseEntity {
	
	@Id
	@Column(name ="QUOTE_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "QUOTER_ID")
	private Employee quoter;
	
	private String name;
	
	@Column(name = "QUOTE_DATE")
	private Date quoteDate;
	
	private String description;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Employee getQuoter() {
		return quoter;
	}

	public void setQuoter(Employee quoter) {
		this.quoter = quoter;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getQuoteDate() {
		return quoteDate;
	}

	public void setQuoteDate(Date quoteDate) {
		this.quoteDate = quoteDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getQuoterName() {
		return this.getQuoter().getName();
	}
}
