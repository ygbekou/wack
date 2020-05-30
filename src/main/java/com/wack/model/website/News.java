package com.wack.model.website;

import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.wack.model.BaseEntity;
import com.wack.model.User;
 
@Entity
@Table(name="NEWS")
public class News extends BaseEntity {
	
	@Id
	@Column(name ="NEWS_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "AUTHOR_ID")
	private User author;
	private String language;
	private String title;
	@Lob
	private String content;
	@Column(name ="PUBLICATION_DATETIME")
	private Timestamp publicationDatetime;
	@Column(name ="VIEW_COUNT")
	private int viewCount;
	@Column(name ="RATING_COUNT")
	private int ratingCount;
	private int rating;
	private String picture = "default.jpeg";
	private int status;
	
	@Transient
	private List<NewsVideo> newsVideos;
	
	public int getRatingCount() {
		return ratingCount;
	}
	public void setRatingCount(int ratingCount) {
		this.ratingCount = ratingCount;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getPublicationDatetime() {
		return publicationDatetime;
	}
	public void setPublicationDatetime(Timestamp publicationDatetime) {
		this.publicationDatetime = publicationDatetime;
	}
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}		
 
 
	public List<NewsVideo> getNewsVideos() {
		return newsVideos;
	}
	public void setNewsVideos(List<NewsVideo> newsVideos) {
		this.newsVideos = newsVideos;
	}
	public List<String> getChildEntities() {
		return Arrays.asList("newsVideos");
	}
}
