package com.wack.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="COUNTRY")
public class Country extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COUNTRY_ID")
	private Long id;

	@Column(name = "ADDRESS_FORMAT")
	private String addressFormat;

	@Column(name = "ISO_CODE_2")
	private String isoCode2;

	@Column(name = "ISO_CODE_3")
	private String isoCode3;
	 
	private String code;

	private String name;

	@Column(name = "POSTCODE_REQUIRED")
	private int postcodeRequired;

	private int status;
	
	public Country(){}
	public Country(Country country) {
		// TODO Auto-generated constructor stub
		this.name=country.getName();
	}

	public String getAddressFormat() {
		return addressFormat;
	}
	public void setAddressFormat(String addressFormat) {
		this.addressFormat = addressFormat;
	}
	public String getIsoCode2() {
		return isoCode2;
	}
	public void setIsoCode2(String isoCode2) {
		this.isoCode2 = isoCode2;
	}
	public String getIsoCode3() {
		return isoCode3;
	}
	public void setIsoCode3(String isoCode3) {
		this.isoCode3 = isoCode3;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getPostcodeRequired() {
		return postcodeRequired;
	}
	public void setPostcodeRequired(int postcodeRequired) {
		this.postcodeRequired = postcodeRequired;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
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
	@Override
	public String toString() {
		return "Country [name=" + name + "]";
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return id.intValue();
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

}
