package com.wack.poll;

import java.io.Serializable;
import java.util.Arrays;
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

import com.wack.model.BaseEntity;

@Entity
@Table(name = "POLL")
public class Poll extends BaseEntity   implements Comparable<Object>, Serializable  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "POLL_ID")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "POLL_TYPE_ID")
	private PollType pollType;

	@Column(name = "STATUS")
	private Integer status = 1;

	@Transient
	private Integer pollTypeId;

	@Transient
	private Long userId;

	@Transient
	private String error;
	
	@Transient
	List<PollDesc> pollDescs;

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

	public Integer getStatus() {
		return status;
	}

	public Integer getPollTypeId() {
		return pollTypeId;
	}

	public void setPollTypeId(Integer pollTypeId) {
		this.pollTypeId = pollTypeId;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PollType getPollType() {
		return pollType;
	}

	public void setPollType(PollType pollType) {
		this.pollType = pollType;
	}
	
	public List<PollDesc> getPollDescs() {
		return pollDescs;
	}

	public void setPollDescs(List<PollDesc> pollDescs) {
		this.pollDescs = pollDescs;
	}

	@Override
	public List<String> getChildEntities() {
		return Arrays.asList("pollDescs");
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
