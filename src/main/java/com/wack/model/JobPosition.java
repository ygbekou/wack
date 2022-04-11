package com.wack.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "JOB_POSITION")
public class JobPosition extends BaseEntity   implements Comparable<Object>, Serializable  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	private String location;
	
	private Integer rank = 1;

	private Integer status = 1;
	
	@Transient
	List<JobPositionDesc> jobPositionDescs;

	public JobPosition() {}
	
	public JobPosition(Long id) {
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

	public List<JobPositionDesc> getJobPositionDescs() {
		return jobPositionDescs;
	}

	public void setJobPositionDescs(List<JobPositionDesc> jobPositionDescs) {
		this.jobPositionDescs = jobPositionDescs;
	}

	@Override
	public List<String> getChildEntities() {
		return Arrays.asList("jobPositionDescs");
	}
	
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		if (this.id != null && o != null){
			if(this.id<((JobPosition) (o)).getId()){
				return 1;
			}else{
				return -1;
			} 
		}
		return 0;
	}


}
