package com.wack.model;


import java.util.Date;
import javax.persistence.*;



@Entity
@Table(name = "PUBLICITY")
public class Publicity extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PUBLICITY_ID")
	private Long id; 
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name="LINK")
	private String link;
	
	@Column(name="PIC")
	private String picture;

	@Column(name = "STATUS")
	private Integer status;
	
	@Column(name = "BEGIN_DATE")
	private Date beginDate;

	@Column(name = "END_DATE")
	private Date endDate;
	
	@Column(name = "RANK")
	private Integer rank;
	
	@Column(name = "COST")
	private Integer cost;
 

	public boolean getStatus() {
		return status == 1 ? true : false;
	}


	public Long getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCost() {
		return cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
