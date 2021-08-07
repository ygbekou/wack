package com.wack.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "VIDEO")
@Entity
public class Video extends BaseEntity implements Comparable<Object>, Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "VIDEO_ID")
	private Long id;

	@Column(name = "LINK")
	private String link;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "VIDEO_DATE")
	private Date videoDate;

	@Column(name = "RANK")
	private Long rank;

	@Column(name = "STATUS")
	private Short status = 1;
	
	@Column(name = "VOTE")
	private Short vote = 1;
	
	@Column(name = "VOTE_COUNT")
	private Integer voteCount = 0;

	@Transient
	private String ip;
	
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public boolean getVote() {
		return vote == 1 ? true : false;
	}

	public void setVote(boolean vote) {
		this.vote = (short) (vote == true ? 1 : 0);
	}

	public Integer getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(Integer voteCount) {
		this.voteCount = voteCount;
	}

	public Long getRank() {
		return rank;
	}

	public void setRank(Long rank) {
		this.rank = rank;
	}

	public boolean getStatus() {
		return status == 1 ? true : false;
	}

	public void setStatus(boolean status) {
		this.status = (short) (status == true ? 1 : 0);
	}

	@Override
	public int compareTo(Object obj) {
		Video aVideo = (Video) obj;
		return this.rank < aVideo.getRank() ? 1 : this.rank > aVideo.getRank() ? -1 : 0;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rank == null) ? 0 : rank.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Video other = (Video) obj;
		if (rank == null) {
			if (other.rank != null)
				return false;
		} else if (!rank.equals(other.rank))
			return false;
		return true;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getVideoDate() {
		return videoDate;
	}

	public void setVideoDate(Date videoDate) {
		this.videoDate = videoDate;
	}

}
