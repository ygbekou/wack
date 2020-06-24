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
@Table(name="PAYMENT")
public class Payment extends BaseEntity {
	
	@Id
	@Column(name ="PAYMENT_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "PAYMENT_TYPE_ID")
	private PaymentType paymentType;
	
	@ManyToOne
	@JoinColumn(name = "RECEIVER_ID")
	private Employee receiver;
	
	@ManyToOne
	@JoinColumn(name = "CONTRACT_LABOR_ID")
	private ContractLabor contractLabor;
	
	@ManyToOne
	@JoinColumn(name = "PAID_BY")
	private Employee payer;
	
	@Column(name ="PAYMENT_DATE")
	private Date paymentDate;
	
	@Column(name ="SALARY_YEAR")
	private Integer salaryYear;
	
	@ManyToOne
	@JoinColumn(name = "SALARY_MONTH")
	private Month salaryMonth;
	
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

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public Employee getReceiver() {
		return receiver;
	}

	public void setReceiver(Employee receiver) {
		this.receiver = receiver;
	}
	
	public ContractLabor getContractLabor() {
		return contractLabor;
	}

	public void setContractLabor(ContractLabor contractLabor) {
		this.contractLabor = contractLabor;
	}

	public Employee getPayer() {
		return payer;
	}

	public void setPayer(Employee payer) {
		this.payer = payer;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getPaymentTypeName() {
		return this.getPaymentType() != null ? this.getPaymentType().getName() : "";
	}
	
	public String getPayerName() {
		return this.getPayer().getName();
	}
	
	public String getReceiverName() {
		return this.getReceiver().getName();
	}
	
	public String getContractLaborLabel() {
		return this.getContractLabor() != null ? this.getContractLabor().getLabel() : "";
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	

	public Integer getSalaryYear() {
		return salaryYear;
	}

	public void setSalaryYear(Integer salaryYear) {
		this.salaryYear = salaryYear;
	}
	
	public Month getSalaryMonth() {
		return salaryMonth;
	}

	public void setSalaryMonth(Month salaryMonth) {
		this.salaryMonth = salaryMonth;
	}

	public User getModifier() {
		return modifier;
	}

	public void setModifier(User modifier) {
		this.modifier = modifier;
	}

	public String getModifierName() {
		return this.modifier != null ? this.modifier.getName() : "";
	}
	
	public String getSalaryMonthName() {
		return this.salaryMonth != null ? this.salaryMonth.getName() : "";
	}
	
	public String getStatusDesc() {
		return this.status == 0 ? "Actif" : "Inactif";
	}
}
