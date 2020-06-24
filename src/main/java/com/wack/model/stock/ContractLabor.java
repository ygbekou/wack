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
import com.wack.model.User;


@Entity
@Table(name="CONTRACT_LABOR")
public class ContractLabor extends BaseEntity {
	
	@Id
	@Column(name ="CONTRACT_LABOR_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "CONTRACTOR_ID")
	private Employee contractor;
	
	@ManyToOne
	@JoinColumn(name = "QUOTE_ID")
	private Quote quote;
	
	@Column(name = "CONTRACT_DATE")
	private Date contractDate;
	
	private String name;
	
	private Double amount;
	
	private Double paid;
	
	private Double balance;
	
	private String image;
	
	private String description;

	private int status;
	
	@ManyToOne
	@JoinColumn(name = "MOD_BY", referencedColumnName = "USER_ID", insertable=false, updatable=false)
	private User modifier;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Employee getContractor() {
		return contractor;
	}

	public void setContractor(Employee contractor) {
		this.contractor = contractor;
	}

	public Quote getQuote() {
		return quote;
	}

	public void setQuote(Quote quote) {
		this.quote = quote;
	}

	public Date getContractDate() {
		return contractDate;
	}

	public void setContractDate(Date contractDate) {
		this.contractDate = contractDate;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getPaid() {
		return paid;
	}

	public void setPaid(Double paid) {
		this.paid = paid;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getLabel() {
		return this.getName() + " - " + this.getAmount();
	}
	
	public String getContractorName() {
		return this.getContractor().getName();
	}
	
	public String getQuoteName() {
		return this.getQuote().getName();
	}

	public User getModifier() {
		return modifier;
	}

	public void setModifier(User modifier) {
		this.modifier = modifier;
	}
	
	public String getModifierName() {
		return modifier != null ? modifier.getName() : "";
	}
	
	public String getStatusDesc() {
		return this.status == 0 ? "Actif" : "Inactif";
	}
	
	public boolean isActive() {
		return this.status == 0;
	}
}
