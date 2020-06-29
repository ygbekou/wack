package com.wack.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the location database table.
 * 
 */
@Entity
@Table(name = "LOCATION")
public class Location extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "LOCATION_ID")
	private Long locationId;

	@Column(name ="ADDRESS")
	private String address;

	@Column(name = "COMMENT")
	private String comment;

	@Column(name ="FAX")
	private String fax;

	@Column(name ="GEOCODE")
	private String geocode;

	@Column(name ="IMAGE")
	private String image;

	@Column(name ="NAME")
	private String name;

	@Column(name ="OPEN")
	private String open;

	@Column(name ="TELEPHONE")
	private String telephone;
	
	@Column(name ="`RANK`")
	private Integer rank;	

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public Location() {
	}

	public String getAddress() {
		return this.address;
	}

	public String getComment() {
		return this.comment;
	}

	public String getFax() {
		return this.fax;
	}

	public String getGeocode() {
		return this.geocode;
	}

	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return locationId;
	}

	public String getImage() {
		return this.image;
	}

	public Long getLocationId() {
		return this.locationId;
	}

	public String getName() {
		return this.name;
	}

	public String getOpen() {
		return this.open;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public void setGeocode(String geocode) {
		this.geocode = geocode;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

}