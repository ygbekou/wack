package com.wack.poll;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.wack.model.BaseEntity;
import com.wack.model.PositionDesc;

@Entity
@Table(name = "POLL_TYPE")
public class PollType extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "POLL_TYPE_ID")
	private Long id;
	
	@Column(name = "STATUS")
	private Short status=1;
	
	@Transient
	List<PollTypeDesc> pollTypeDescs;
	

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public List<PollTypeDesc> getPollTypeDescs() {
		return pollTypeDescs;
	}

	public void setPollTypeDescs(List<PollTypeDesc> pollTypeDescs) {
		this.pollTypeDescs = pollTypeDescs;
	}
	
	@Override
	public List<String> getChildEntities() {
		return Arrays.asList("pollTypeDescs");
	}

}
