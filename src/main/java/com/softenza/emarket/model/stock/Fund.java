package com.softenza.emarket.model.stock;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.softenza.emarket.model.BaseEntity;
import com.softenza.emarket.model.Employee;
import com.softenza.emarket.model.User;


@Entity
@Table(name="FUND")
public class Fund extends BaseEntity {
	
	@Id
	@Column(name ="FUND_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "RECEIVER_ID")
	private Employee receiver;
	
	@Column(name ="RECEPTION_DATE")
	private Date receptionDate;
	
	private Double amount;
	
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
	
	public Employee getReceiver() {
		return receiver;
	}

	public void setReceiver(Employee receiver) {
		this.receiver = receiver;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Date getReceptionDate() {
		return receptionDate;
	}

	public void setReceptionDate(Date receptionDate) {
		this.receptionDate = receptionDate;
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

	public String getReceiverName() {
		return this.receiver.getName();
	}
	
	public String getModifierName() {
		return this.modifier != null ? this.modifier.getName() : "";
	}
	
	public String getStatusDesc() {
		return this.status == 0 ? "Actif" : "Inactif";
	}
}
