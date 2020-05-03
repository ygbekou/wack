package com.wack.model.website;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.wack.model.BaseEntity;
 
@Entity
@Table(name="FAQ")
public class Faq extends BaseEntity {
	
	@Id
	@Column(name ="FAQ_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String language;
	private String question;
	private String answer;
	@Column(name ="NO_COUNT")
	private int noCount;
	@Column(name ="YES_COUNT")
	private int yesCount;
	private int status;
	
	
	
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
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public int getNoCount() {
		return noCount;
	}
	public void setNoCount(int noCount) {
		this.noCount = noCount;
	}
	public int getYesCount() {
		return yesCount;
	}
	public void setYesCount(int yesCount) {
		this.yesCount = yesCount;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
