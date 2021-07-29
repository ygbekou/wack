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
@Table(name = "POSITION")
public class Position extends BaseEntity implements Comparable<Object>, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "POSITION_ID")
	private Long id;

	@Column(name = "RANK")
	private Integer rank;

	@Column(name = "LEADERSHIP")
	private int leadership = 0;
	
	private int status;
	
	@Transient
	List<PositionDesc> positionDescs;
	

	@Override
	public Long getId() {
		return id;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getLeadership() {
		return leadership;
	}

	public void setLeadership(int leadership) {
		this.leadership = leadership;
	}

	public List<PositionDesc> getPositionDescs() {
		return positionDescs;
	}

	public void setPositionDescs(List<PositionDesc> positionDescs) {
		this.positionDescs = positionDescs;
	}

	@Override
	public List<String> getChildEntities() {
		return Arrays.asList("positionDescs");
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return this.rank.compareTo(((Position) o).getRank());
	}

}
