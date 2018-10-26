package com.qkcare.domain;

import java.io.Serializable;
import java.util.List;

import com.qkcare.model.mis.Parameter;


public class RunReportVO implements Serializable{
	
	private String reportName;
	
	private Long reportId;
	
	private List<Parameter> parameters;
	
	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public List<Parameter> getParameters() {
		return parameters;
	}

	public void setParameters(List<Parameter> parameters) {
		this.parameters = parameters;
	}

	public Long getReportId() {
		return reportId;
	}

	public void setReportId(Long reportId) {
		this.reportId = reportId;
	}

}
