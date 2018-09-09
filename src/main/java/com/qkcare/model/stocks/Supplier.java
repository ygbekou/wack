package com.qkcare.model.stocks;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.qkcare.model.BaseEntity;
import com.qkcare.model.Country;


@Entity
@Table(name="SUPPLIER")
public class Supplier extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SUPPLIER_ID")
	private Long id;
		
	//@NotNull
	//@Length(max = 75)
	@Column(name = "NAME")
	private String name;

	//@Length(max = 100)
	@Column(name = "CONTACT_NAME")
	private String contactName;
	
	//@Length(max = 75)
	@Column(name = "ADDRESS")
	private String address;
	
	@Column(name = "CITY")
	private String city;
	
	@ManyToOne
	@JoinColumn(name = "COUNTRY_ID")
	private Country country;
	
	//@Length(max = 50)
	@Column(name = "EMAIL")
	private String email;

	@Column(name = "HOME_PHONE")
	private String homePhone;
	
	@Column(name = "MOBILE_PHONE")
	private String mobilePhone;
	
	@Column(name = "FAX")
	private String fax;

	
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

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

}
