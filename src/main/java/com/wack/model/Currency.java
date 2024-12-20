package com.wack.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "CURRENCY")
public class Currency extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CURRENCY_ID")
	private Long id;

	private String code;

	@Column(name = "DECIMAL_PLACE")
	private int decimalPlace;

	private int status;

	@Column(name = "SYMBOL_LEFT")
	private String symbolLeft;

	@Column(name = "SYMBOL_RIGHT")
	private String symbolRight;

	private String title;

	private double value;

	public Currency() {
	}
	
	public Currency(Long id) {
		this.id = id;
	}


	public String getCode() {
		return this.code;
	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getStatus() {
		return this.status;
	}

	public String getSymbolLeft() {
		return this.symbolLeft == null ? "" : this.symbolLeft;
	}

	public String getSymbolRight() {
		return this.symbolRight == null ? "" : this.symbolRight;
	}

	public String getTitle() {
		return this.title;
	}

	public double getValue() {
		return this.value;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setSymbolLeft(String symbolLeft) {
		this.symbolLeft = symbolLeft;
	}

	public void setSymbolRight(String symbolRight) {
		this.symbolRight = symbolRight;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public int getDecimalPlace() {
		return decimalPlace;
	}

	public void setDecimalPlace(int decimalPlace) {
		this.decimalPlace = decimalPlace;
	}

}