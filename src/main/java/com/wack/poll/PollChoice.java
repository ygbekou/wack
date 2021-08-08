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
import com.wack.model.User;

@Entity
@Table(name = "POLL_CHOICE")
public class PollChoice extends BaseEntity  implements Comparable<Object>, Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "POLL_CHOICE_ID")
	private Long id;

	@Column(name = "RANK")
	private Integer rank;

	@Column(name = "VOTE_COUNT")
	private Integer voteCount = 0;
	
//	@Column(name = "URL")
//	private String url;

	@ManyToOne
	@JoinColumn(name = "POLL_QUESTION_ID")
	private PollQuestion pollQuestion;

	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;
	
	@Transient
	private boolean winner;
	
	@Transient
	private String error;
	
	@Transient
	List<PollChoiceDesc> pollChoiceDescs;
	
	public boolean isWinner() {
		return winner;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

//	public String getUrl() {
//		return url;
//	}
//
//	public void setUrl(String url) {
//		this.url = url;
//	}

	public void setWinner(boolean winner) {
		this.winner = winner;
	}

	public Integer getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(Integer voteCount) {
		this.voteCount = voteCount;
	}

	public PollQuestion getPollQuestion() {
		return pollQuestion;
	}

	public void setPollQuestion(PollQuestion pollQuestion) {
		this.pollQuestion = pollQuestion;
	}

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

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public List<PollChoiceDesc> getPollChoiceDescs() {
		return pollChoiceDescs;
	}

	public void setPollChoiceDescs(List<PollChoiceDesc> pollChoiceDescs) {
		this.pollChoiceDescs = pollChoiceDescs;
	}

	@Override
	public List<String> getChildEntities() {
		return Arrays.asList("pollChoiceDescs");
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		if (this.rank != null && o != null)
			return this.rank.compareTo(((PollChoice) (o)).getRank());
		return 0;
	}

}
