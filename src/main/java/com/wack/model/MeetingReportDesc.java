package com.wack.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name = "MEETING_REPORT_DESC")
public class MeetingReportDesc extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MEETING_REPORT_DESC_ID") 
	private Long id;

	private String message;

	private String language;

	private String title;

	@ManyToOne
	@JoinColumn(name = "MEETING_REPORT_ID") 
	private MeetingReport meetingReport;
	

	public MeetingReportDesc() {}


	@Override
	public Long getId() {
		return this.id;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public MeetingReport getMeetingReport() {
		return meetingReport;
	}

	public void setMeetingReport(MeetingReport meetingReport) {
		this.meetingReport = meetingReport;
	}
	
	public String getAuthorName() {
		return (this.meetingReport != null && this.meetingReport.getAuthor() != null) ? this.meetingReport.getAuthor().getName() : "";
	}
}