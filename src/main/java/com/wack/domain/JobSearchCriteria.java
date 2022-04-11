package com.wack.domain;


public class JobSearchCriteria {
	private Long jobPositionId;
	private Long jobPositionDescId;
	private String language;
	private Integer status;
	public Long getJobPositionId() {
		return jobPositionId;
	}
	public void setJobPositionId(Long jobPositionId) {
		this.jobPositionId = jobPositionId;
	}
	public Long getJobPositionDescId() {
		return jobPositionDescId;
	}
	public void setJobPositionDescId(Long jobPositionDescId) {
		this.jobPositionDescId = jobPositionDescId;
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
	
	
}
