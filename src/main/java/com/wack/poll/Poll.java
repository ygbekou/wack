package com.wack.poll;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.wack.model.BaseEntity;

@Entity
@Table(name = "POLL")
public class Poll extends BaseEntity   implements Comparable<Object>, Serializable  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "POLL_ID")
	private Long id;

	@Column(name = "TITLE")
	private String title;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "END_NOTE")
	private String endNote;

	@ManyToOne
	@JoinColumn(name = "POLL_TYPE_ID")
	private PollType pollType;

	@Column(name = "STATUS")
	private Short status = 1;

	@Transient
	private Integer pollTypeId;

	@Transient
	private Long userId;

	@Transient
	private String error;

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Override
	public Long getId() {
		return id;
	}

	public boolean getStatus() {
		return status == 1 ? true : false;
	}

	public String getEndNote() {
		return endNote;
	}

	public void setEndNote(String endNote) {
		this.endNote = endNote;
	}

	public Integer getPollTypeId() {
		return pollTypeId;
	}

	public void setPollTypeId(Integer pollTypeId) {
		this.pollTypeId = pollTypeId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setStatus(boolean status) {
		this.status = (short) (status == true ? 1 : 0);
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

	public PollType getPollType() {
		return pollType;
	}

	public void setPollType(PollType pollType) {
		this.pollType = pollType;
	}
	
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		if (this.id != null && o != null){
			if(this.id<((Poll) (o)).getId()){
				return 1;
			}else{
				return -1;
			} 
		}
		return 0;
	}


}
