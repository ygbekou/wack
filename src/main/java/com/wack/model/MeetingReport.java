package com.wack.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "MEETING_REPORT")
public class MeetingReport extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MEETING_REPORT_ID")
	private Long id;

	@Column(name = "MEETING_DATE")
	private Date meetingDate;

	@OneToOne
	@JoinColumn(name = "AUTHOR_ID")
	private User author;
	
	private int status;
	
	@Transient
	List<MeetingReportDesc> meetingReportDescs;
	

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getMeetingDate() {
		return meetingDate;
	}

	public void setMeetingDate(Date meetingDate) {
		this.meetingDate = meetingDate;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public List<MeetingReportDesc> getMeetingReportDescs() {
		return meetingReportDescs;
	}

	public void setMeetingReportDescs(List<MeetingReportDesc> meetingReportDescs) {
		this.meetingReportDescs = meetingReportDescs;
	}
	
	@Override
	public List<String> getChildEntities() {
		return Arrays.asList("meetingReportDescs");
	}
}
