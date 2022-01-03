package com.wack.model;
import java.util.Date;
import javax.persistence.*; 

@Entity
@Table(name = "TRANSACTION")
public class Transaction extends BaseEntity {

	public Transaction() {
		super();
	}

	public Transaction(PaymentHistory pay) {
		io = 1;
		amount = pay.getAmount();
		comment = pay.getDescription();
		rebate = 0D;
		user = pay.getUser();
		project = pay.getProject();
		fee = pay.getFee();
		ref_nbr= pay.getPaymentId();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TRANSACTION_ID")
	private Long id;

	@Column(name = "AMOUNT")
	private Double amount=0.0;

	@Column(name = "FEE")
	private Double fee=0.0;

	@Column(name = "REBATE")
	private Double rebate =0.0;

	@Column(name = "IO")
	private Integer io;

	@Column(name = "COMMENT")
	private String comment;

	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;

	@ManyToOne
	@JoinColumn(name = "PROJECT_ID")
	private Project project; 
	
	@Column(name = "REF_NBR")
	private String ref_nbr;
	
	@Column(name = "TRAN_DATE")
	private Date tranDate = new Date();
	
	private String phone;
	
	@Column(name = "PAYMENT_METHOD")
	private String paymentMethod;
	

	@Column(name = "FAILURE_REASON")
	private String failureReason;
	
	private int status;
	
	@Transient
	private String paygateGlobalPaymentUrl;
	

	public Date getTranDate() {
		return tranDate;
	}

	public void setTranDate(Date tranDate) {
		this.tranDate = tranDate==null?getCreateDate():tranDate;
	}

	public String getRef_nbr() {
		return ref_nbr;
	}

	@Column(name = "ANONYMOUS")
	private Short anonymous=0;
	
	@Transient
	private String currencyCode="XOF";

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public boolean getAnonymous() {
		return anonymous==1?true:false;
	}


	public void setAnonymous(boolean anonymous) {
		this.anonymous = (short) (anonymous==true?1:0);
	}
	
	public void setRef_nbr(String ref_nbr) {
		this.ref_nbr = ref_nbr;
	}

	public Double getFee() {
		return fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Integer getIo() {
		return io;
	}

	public void setIo(Integer io) {
		this.io = io;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getRebate() {
		return rebate;
	}

	public void setRebate(Double rebate) {
		this.rebate = rebate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getMemberName() {
		return this.user != null ? this.user.getName() : "";
	}
	
	public String getProjectTitle() {
		return this.project != null ? this.project.getTitle() : "";
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getPaygateGlobalPaymentUrl() {
		return paygateGlobalPaymentUrl;
	}

	public void setPaygateGlobalPaymentUrl(String paygateGlobalPaymentUrl) {
		this.paygateGlobalPaymentUrl = paygateGlobalPaymentUrl;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getFailureReason() {
		return failureReason;
	}

	public void setFailureReason(String failureReason) {
		this.failureReason = failureReason;
	}
	
	
}
