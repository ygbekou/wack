package com.wack.domain;


public class GenericSearchCriteria {
	private Long id;
	private Long prdCategoryId;
	private String language;
	private Integer status;
	
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Long getPrdCategoryId() {
		return prdCategoryId;
	}
	public void setPrdCategoryId(Long prdCategoryId) {
		this.prdCategoryId = prdCategoryId;
	}
}
