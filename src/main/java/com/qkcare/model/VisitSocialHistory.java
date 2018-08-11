package com.qkcare.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "VISIT_SOCIALHISTORY")
public class VisitSocialHistory extends BaseEntity {
	
	@Id
	@Column(name = "VISIT_SOCIALHISTORY_ID")
	@GeneratedValue
	private Long id;
	@ManyToOne
	@JoinColumn(name = "VISIT_ID")
	private Visit visit;
	@ManyToOne
	@JoinColumn(name = "SOCIALHISTORY_ID")
	private SocialHistory socialHistory;
	
	public VisitSocialHistory() {}
	
	public VisitSocialHistory(Long visitId, Long socialHistoryId) {
		this.visit = new Visit(visitId);
		this.socialHistory = new SocialHistory(socialHistoryId);
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Visit getVisit() {
		return visit;
	}
	public void setVisit(Visit visit) {
		this.visit = visit;
	}

	public SocialHistory getSocialHistory() {
		return socialHistory;
	}

	public void setSocialHistory(SocialHistory socialHistory) {
		this.socialHistory = socialHistory;
	}
}
