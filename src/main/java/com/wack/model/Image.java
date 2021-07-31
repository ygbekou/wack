package com.wack.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table (name="IMAGE")
@Entity
public class  Image extends BaseEntity implements Comparable<Object>, Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IMAGE_ID")
	private Long id;

	@Column(name = "PIC")
	private String pic;
	
	@Column(name = "TITLE")
	private String title; 
	
	@Column(name = "DESCRIPTION")
	private String description; 
	
	@Column(name="RANK")
	private Long rank;
	
	@Column(name="STATUS")
	private Short status=1;
	

	public Long getRank() {
		return rank;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setRank(Long rank) {
		this.rank = rank;
	}

	public boolean getStatus() {
		return status==1?true:false;
	}

	public void setStatus(boolean status) {
		this.status = (short) (status==true?1:0);
	}

	@Override
	public int compareTo(Object obj) { 
		Image aVideo = (Image)obj;
		return this.rank<aVideo.getRank()?1:this.rank>aVideo.getRank()?-1:0;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

 
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	} 
 
}
