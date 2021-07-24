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
import com.wack.model.Position;

@Entity
@Table(name = "POLL_QUESTION")
public class PollQuestion extends BaseEntity implements Comparable<Object>, Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "POLL_QUESTION_ID")
	private Long id;

	@Column(name = "RANK")
	private Integer rank;

	@Column(name = "DESCRIPTION")
	private String description;

	@ManyToOne
	@JoinColumn(name = "POLL_ID")
	private Poll poll;
	
	@Transient
	private String error;

	@ManyToOne
	@JoinColumn(name = "POSITION_ID")
	private Position position;

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Poll getPoll() {
		return poll;
	}

	public void setPoll(Poll poll) {
		this.poll = poll;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
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

	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		if (this.rank != null && arg0 != null)
			return this.rank.compareTo(((PollQuestion) (arg0)).getRank());
		return 0;
	}

}
