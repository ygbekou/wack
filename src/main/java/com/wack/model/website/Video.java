package com.wack.model.website;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.wack.model.BaseEntity;
import com.wack.model.Project;
 
@Entity
@Table(name="VIDEO")
public class Video extends BaseEntity {	
	@Id
	@Column(name ="VIDEO_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "NEWS_ID")
	private News news;	
	
	@ManyToOne
	@JoinColumn(name = "PROJECT_ID")
	private Project project;
	
	private String name;
	private String link;
	private int status;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "VIDEO_DATE")
	private Date videoDate;

	@Column(name = "RANK")
	private Long rank; 
	
	@Column(name = "VOTE")
	private Short vote = 1;
	
	@Column(name = "VOTE_COUNT")
	private Integer voteCount = 0;
	
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
	public Long getRank() {
		return rank;
	}
	public void setRank(Long rank) {
		this.rank = rank;
	}
	public Short getVote() {
		return vote;
	}
	public void setVote(Short vote) {
		this.vote = vote;
	}
	public Integer getVoteCount() {
		return voteCount;
	}
	public void setVoteCount(Integer voteCount) {
		this.voteCount = voteCount;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public News getNews() {
		return news;
	}
	public void setNews(News news) {
		this.news = news;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
