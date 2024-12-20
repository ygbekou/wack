package com.wack.model;

import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.wack.model.authorization.*;
import com.wack.model.stock.ExpenseType;
import com.wack.model.stock.PaymentType;
import com.wack.model.stock.PrdCategory;
import com.wack.model.stock.PrdCategoryDesc;
import com.wack.model.website.*;
import com.wack.poll.Poll;
import com.wack.poll.PollChoice;
import com.wack.poll.PollDesc;
import com.wack.poll.PollQuestion;
import com.wack.poll.PollQuestionDesc;
import com.wack.poll.PollType;
import com.wack.poll.PollTypeDesc;
import com.wack.poll.Vote;

import javax.persistence.*;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@JsonTypeInfo(use = com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME, 
include = com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = Country.class, name = "Country"),     
    @JsonSubTypes.Type(value = ContactUsMessage.class, name = "ContactUsMessage"),
    @JsonSubTypes.Type(value = ExpenseType.class, name = "ExpenseType"),
    @JsonSubTypes.Type(value = Event.class, name = "Event"),
    @JsonSubTypes.Type(value = EventDesc.class, name = "EventDesc"),
    @JsonSubTypes.Type(value = User.class, name = "User"),
    @JsonSubTypes.Type(value = Company.class, name = "Company"),     
    @JsonSubTypes.Type(value = Employee.class, name = "Employee"),
    @JsonSubTypes.Type(value = Image.class, name = "Image"),    
    @JsonSubTypes.Type(value = JobAppli.class, name = "JobAppli"), 
    @JsonSubTypes.Type(value = JobPosition.class, name = "JobPosition"), 
    @JsonSubTypes.Type(value = JobPositionDesc.class, name = "JobPositionDesc"), 
    @JsonSubTypes.Type(value = UserGroup.class, name = "UserGroup"),    
    @JsonSubTypes.Type(value = MeetingReport.class, name = "MeetingReport"),     
    @JsonSubTypes.Type(value = MeetingReportDesc.class, name = "MeetingReportDesc"),     
    @JsonSubTypes.Type(value = MenuItem.class, name = "MenuItem"),     
    @JsonSubTypes.Type(value = Permission.class, name = "Permission"),
    @JsonSubTypes.Type(value = Resource.class, name = "Resource"),    
    @JsonSubTypes.Type(value = UserRole.class, name = "UserRole"),     
    @JsonSubTypes.Type(value = Testimony.class, name = "Testimony"),
    @JsonSubTypes.Type(value = SliderText.class, name = "SliderText"),
    @JsonSubTypes.Type(value = Slider.class, name = "Slider"),    
    @JsonSubTypes.Type(value = Setting.class, name = "Setting"),     
    @JsonSubTypes.Type(value = SectionItem.class, name = "SectionItem"),
    @JsonSubTypes.Type(value = Section.class, name = "Section"),     
    @JsonSubTypes.Type(value = News.class, name = "News"),     
    @JsonSubTypes.Type(value = MailingList.class, name = "MailingList"),
    @JsonSubTypes.Type(value = Feedback.class, name = "Feedback"),  
    @JsonSubTypes.Type(value = Location.class, name = "Location"),  
    @JsonSubTypes.Type(value = Faq.class, name = "Faq"),     
    @JsonSubTypes.Type(value = Comment.class, name = "Comment"),
    @JsonSubTypes.Type(value = Client.class, name = "Client"),
    @JsonSubTypes.Type(value = CategoryNews.class, name = "CategoryNews"),
    @JsonSubTypes.Type(value = Poll.class, name = "Poll"), 
    @JsonSubTypes.Type(value = PollDesc.class, name = "PollDesc"), 
    @JsonSubTypes.Type(value = PollChoice.class, name = "PollChoice"), 
    @JsonSubTypes.Type(value = PollQuestion.class, name = "PollQuestion"), 
    @JsonSubTypes.Type(value = PollQuestionDesc.class, name = "PollQuestionDesc"),
    @JsonSubTypes.Type(value = PollType.class, name = "PollType"), 
    @JsonSubTypes.Type(value = PollTypeDesc.class, name = "PollTypeDesc"), 
    @JsonSubTypes.Type(value = Country.class, name = "Quote"),
    @JsonSubTypes.Type(value = Video.class, name = "Video"),
    @JsonSubTypes.Type(value = Vote.class, name = "Vote"),
    @JsonSubTypes.Type(value = Position.class, name = "Position"), 
    @JsonSubTypes.Type(value = Project.class, name = "Project"), 
    @JsonSubTypes.Type(value = ProjectUser.class, name = "ProjectUser"), 
    @JsonSubTypes.Type(value = Publicity.class, name = "Publicity"), 
    @JsonSubTypes.Type(value = Regulation.class, name = "Regulation"), 
    @JsonSubTypes.Type(value = SessionHistory.class, name = "SessionHistory"), 
    @JsonSubTypes.Type(value = StatusText.class, name = "StatusText"), 
    @JsonSubTypes.Type(value = Contribution.class, name = "Contribution"), 
    @JsonSubTypes.Type(value = PaymentType.class, name = "PaymentType"), 
    @JsonSubTypes.Type(value = PaymentHistory.class, name = "PaymentHistory"), 
    @JsonSubTypes.Type(value = Transaction.class, name = "Transaction"), 
    @JsonSubTypes.Type(value = Category.class, name = "Category"), 
    @JsonSubTypes.Type(value = PrdCategory.class, name = "PrdCategory"), 
    @JsonSubTypes.Type(value = PrdCategoryDesc.class, name = "PrdCategoryDesc") 
}) 
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass
public abstract class BaseEntity {


	@Column(name = "CREATE_DATE")
	private Date createDate = new Date();
	
	@Column(name = "MOD_DATE")
	private Date modDate = new Date();

	@Column(name = "MOD_BY")
	private Long modifiedBy;
	
	@Transient
	private List<String> errors;
	@Transient
	public String customValidator = "";
	@Transient
	private List<String> fileNames;
	@Transient
	private List<String> remainingFileNames;
	@Transient
	private List<String> childEntities = new ArrayList<>();
	@Transient
	private Boolean useId = true;
	@Transient
	private int useIdAsFileName;
	@Transient
	private String lang;
	
	public abstract Long getId() ;

	public Date getModDate() {
		return modDate;
	}

	public void setModDate(Date modDate) {
		this.modDate = modDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	
	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	
	public void addError(String error ) {
		if (this.errors == null) {
			this.errors = new ArrayList<String>();
		}
		this.errors.add(error);
	}
	
	public void clearErrors() {
		this.errors = null;
	}
	
	public String getCustomValidator() {
		return customValidator;
	}

	public void setCustomValidator(String customValidator) {
		this.customValidator = customValidator;
	}

	public List<String> getFileNames() {
		return fileNames;
	}

	public void setFileNames(List<String> fileNames) {
		this.fileNames = fileNames;
	}

	public List<String> getRemainingFileNames() {
		return remainingFileNames;
	}

	public void setRemainingFileNames(List<String> remainingFileNames) {
		this.remainingFileNames = remainingFileNames;
	}

	public Boolean getUseId() {
		return useId;
	}

	public void setUseId(Boolean useId) {
		this.useId = useId;
	}

	public List<String> getChildEntities() {
		return childEntities;
	}

	public void setChildEntities(List<String> childEntities) {
		this.childEntities = childEntities;
	}
	
	public void setGeneratedFields(BCryptPasswordEncoder encoder) {
		// Todo
	}

	public int getUseIdAsFileName() {
		return useIdAsFileName;
	}

	public void setUseIdAsFileName(int useIdAsFileName) {
		this.useIdAsFileName = useIdAsFileName;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	@Override
	public String toString() {
		return "BaseEntity [createDate=" + createDate + ", modDate=" + modDate
				+ ", modifiedBy=" + modifiedBy + "]";
	}

}
