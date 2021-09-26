package com.wack.model.website;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.wack.model.BaseEntity;
import com.wack.model.News;

@Entity
@Table(name = "CATEGORY_NEWS")
public class CategoryNews extends BaseEntity {
	
	@Id
	@GeneratedValue
	@Column(name = "CATEGORY_ID")
	private Long id;	
	@ManyToOne
	@JoinColumn(name = "CATEGORY_ID", insertable = false, updatable = false)
	private Category category;
	@ManyToOne
	@JoinColumn(name = "NEWS_ID")
	private News news;
	private int status;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public News getNews() {
		return news;
	}
	public void setNews(News news) {
		this.news = news;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	// Transient
	public String getStatusDesc() {
		return status == 0 ? "Active" : "Inactive";
	}
}
