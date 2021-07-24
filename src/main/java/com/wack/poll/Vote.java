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
	private PollQuestion pollChoice;
	
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user; 

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

	public PollQuestion getPollChoice() {
		return pollChoice;
	}

	public void setPollChoice(PollQuestion pollChoice) {
		this.pollChoice = pollChoice;
	}

}
