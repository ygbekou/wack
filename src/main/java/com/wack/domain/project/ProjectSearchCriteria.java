package com.wack.domain.project;


public class ProjectSearchCriteria {
	
	private Long projectId;
	private String language;
	private Integer status;
	private Integer managing;
	
	
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
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
	public Integer getManaging() {
		return managing;
	}
	public void setManaging(Integer managing) {
		this.managing = managing;
	}
	
	
}
