package com.wack.model;
import javax.persistence.*;

@Table(name = "MAIL")
@Entity
public class Mail extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MAIL_ID")
	private Long id;

	@Column(name = "SUBJECT")
	private String subject;

	@ManyToOne
	@JoinColumn(name = "SENDER_ID")
	private User sender;  
	
	@Column(name = "STATUS")
	private Short status;  

	@Column(name = "BODY")
	private String body;  
	
	@Column(name = "SEND_EMAIL")
	private Integer sendEmail;  
	
	@Column(name = "SEND_SMS")
	private Integer sendSms;

	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Integer getSendEmail() {
		return sendEmail;
	}

	public void setSendEmail(Integer sendEmail) {
		this.sendEmail = sendEmail;
	}

	public Integer getSendSms() {
		return sendSms;
	}

	public void setSendSms(Integer sendSms) {
		this.sendSms = sendSms;
	}  

	public boolean isSendEmail() {
		return 1 == sendEmail;
	}
	
	public boolean isSendSms() {
		return 1 == sendSms;
	}
}
