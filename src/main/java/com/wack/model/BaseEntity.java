package com.wack.model;

import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.wack.model.authorization.*;
import com.wack.model.website.*;

import javax.persistence.*;

@JsonTypeInfo(use = com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME, 
include = com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = Country.class, name = "Country"),     
    @JsonSubTypes.Type(value = ContactUsMessage.class, name = "ContactUsMessage"),
    @JsonSubTypes.Type(value = User.class, name = "User"),
    @JsonSubTypes.Type(value = Company.class, name = "Company"),     
    @JsonSubTypes.Type(value = Employee.class, name = "Employee"),
    @JsonSubTypes.Type(value = UserGroup.class, name = "UserGroup"),    
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
    
    @JsonSubTypes.Type(value = NewsVideo.class, name = "NewsVideo"),    
    @JsonSubTypes.Type(value = News.class, name = "News"),     
    @JsonSubTypes.Type(value = MailingList.class, name = "MailingList"),
    @JsonSubTypes.Type(value = Feedback.class, name = "Feedback"),
    @JsonSubTypes.Type(value = NewsVideo.class, name = "NewsVideo"),  
    
    @JsonSubTypes.Type(value = Faq.class, name = "Faq"),     
    @JsonSubTypes.Type(value = Comment.class, name = "Comment"),
    @JsonSubTypes.Type(value = Client.class, name = "Client"),
    @JsonSubTypes.Type(value = CategoryNews.class, name = "CategoryNews"),     
    @JsonSubTypes.Type(value = Category.class, name = "Category") 
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

	@Override
	public String toString() {
		return "BaseEntity [createDate=" + createDate + ", modDate=" + modDate
				+ ", modifiedBy=" + modifiedBy + "]";
	}

}
