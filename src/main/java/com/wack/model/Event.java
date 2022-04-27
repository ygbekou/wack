package com.wack.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "EVENT")
public class Event extends BaseEntity   implements Comparable<Object>, Serializable  {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "COORDINATOR_ID")
	private User coordinator;
	
	private String location;
	
	private Integer rank = 1;

	private String picture;
	
	private Integer status = 1;
	
	@Column(name = "BEGIN_DATE")
	private Date beginDate;

	@Column(name = "END_DATE")
	private Date endDate;
	
	
	@Transient
	List<EventDesc> eventDescs;

	public Event() {}
	
	public Event(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public User getCoordinator() {
		return coordinator;
	}

	public void setCoordinator(User coordinator) {
		this.coordinator = coordinator;
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

	public List<EventDesc> getEventDescs() {
		return eventDescs;
	}

	public void setEventDescs(List<EventDesc> eventDescs) {
		this.eventDescs = eventDescs;
	}

	@Override
	public List<String> getChildEntities() {
		return Arrays.asList("eventDescs");
	}
	
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		if (this.id != null && o != null){
			if(this.id<((Event) (o)).getId()){
				return 1;
			}else{
				return -1;
			} 
		}
		return 0;
	}


}
