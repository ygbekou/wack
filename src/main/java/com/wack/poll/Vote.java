package com.wack.poll;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.wack.model.BaseEntity;
import com.wack.model.User;

@Entity
@Table(name = "VOTE")
public class Vote extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "VOTE_ID")
	private Long id; 
	
	@ManyToOne
	@JoinColumn(name = "POLL_CHOICE_ID")
	private PollChoice pollChoice;
	
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user; 
	
	@Column(name = "STATUS")
	private Integer status = 1;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public Long getId() {
		return id;
	}
 
	public void setId(Long id) {
		this.id = id;
	}

	public PollChoice getPollChoice() {
		return pollChoice;
	}

	public void setPollChoice(PollChoice pollChoice) {
		this.pollChoice = pollChoice;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
