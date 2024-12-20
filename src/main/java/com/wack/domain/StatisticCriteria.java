package com.wack.domain;


public class StatisticCriteria {
	private Long projectId;
	private String grpBy;
	private String period;
	
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	public String getGrpBy() {
		return grpBy;
	}
	public void setGrpBy(String grpBy) {
		this.grpBy = grpBy;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
}
