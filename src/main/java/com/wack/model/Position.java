package com.wack.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "POSITION")
public class Position extends BaseEntity implements Comparable<Object>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "POSITION_ID")
	private Long id;

	@Column(name = "RANK")
	private Integer rank;

	@Column(name = "NAME")
	private String name;

	@Column(name = "LEADERSHIP")
	private Short leadership=0;

	public boolean getLeadership() {
		return leadership == 1 ? true : false;
	}

	public void setLeadership(boolean leadership) {
		this.leadership = leadership ? (short) 1 : 0;
	}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return this.rank.compareTo(((Position) o).getRank());
	}

}
