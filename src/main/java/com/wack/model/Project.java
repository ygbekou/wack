package com.wack.model;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import com.wack.domain.ChartData;
import com.wack.domain.PieChartData;
import com.wack.util.Utils;

@Entity
@Table(name = "PROJECT")
public class Project extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PROJECT_ID")
	private Long id;

	@Column(name = "TITLE")
	private String title;

	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "SPONSORS")
	private String sponsors;

	@Column(name = "BUDGET")
	private Double budget;
	
	@Column(name = "CONTRIBUTION")
	private Double contribution;

	@Column(name = "PROJECTED_START_DATE")
	private Date projectedStartDate;

	@Column(name = "PROJECTED_END_DATE")
	private Date projectedEndDate;

	@Column(name = "START_DATE")
	private Date startDate;

	@Column(name = "END_DATE")
	private Date endDate;

	@Column(name = "STATUS")
	private Integer status = 0;

	@Column(name = "OBJECTIF")
	private String objectif;

	@Column(name = "LOCATION")
	private String location;

	@Column(name = "INOVATION")
	private String inovation;

	@Column(name = "EXISTANT")
	private String existant;

	@Column(name = "RESOURCE")
	private String resource;

	@Column(name = "EXECUTION")
	private String execution;

	@Column(name = "CONSTRAINTS")
	private String constraints;

	@Column(name = "FEASIBILITY")
	private String feasibility;

	@Column(name = "BUDGET_LINE")
	private String budgetLine;

	@Column(name = "RESULT")
	private String result;

	@Column(name = "DURATION")
	private String duration;

	@Column(name = "VIEW_COUNT")
	private int viewCount = 0;

	@Column(name = "RATING_COUNT")
	private int ratingCount = 0;

	private int rating = 0;

	private String picture = "default.jpeg";

	private int featured;
	
	@Transient
	private boolean hasPhoto;

	@Transient
	private List<Contribution> contributions;

	@Transient
	private List<Transaction> expenses;

	@Transient
	private Double totalContributions;

	@Transient
	private Double totalExpenses;

	@Transient
	ChartData data;

	@Transient
	PieChartData pdata;

	@Transient
	Double minContribution;

	@Transient
	Double maxContribution;

	@Transient
	Double totalFees;

	@Transient
	private List<Video> videos;

	public List<Video> getVideos() {
		return videos;
	}

	public int getFeatured() {
		return featured;
	}

	public void setFeatured(int featured) {
		this.featured = featured;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public int getRatingCount() {
		return ratingCount;
	}

	public void setRatingCount(int ratingCount) {
		this.ratingCount = ratingCount;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}

	public String getObjectif() {
		return objectif;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public void setObjectif(String objectif) {
		this.objectif = objectif;
	}

	public String getLocation() {
		return location;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getInovation() {
		return inovation;
	}

	public void setInovation(String inovation) {
		this.inovation = inovation;
	}

	public String getExistant() {
		return existant;
	}

	public void setExistant(String existant) {
		this.existant = existant;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public String getExecution() {
		return execution;
	}

	public void setExecution(String execution) {
		this.execution = execution;
	}

	public String getConstraints() {
		return constraints;
	}

	public void setConstraints(String constraints) {
		this.constraints = constraints;
	}

	public String getFeasibility() {
		return feasibility;
	}

	public void setFeasibility(String feasibility) {
		this.feasibility = feasibility;
	}

	public String getBudgetLine() {
		return budgetLine;
	}

	public void setBudgetLine(String budgetLine) {
		this.budgetLine = budgetLine;
	}

	public Double getContribution() {
		return contribution;
	}

	public void setContribution(Double contribution) {
		this.contribution = contribution;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Double getTotalFees() {
		return totalFees;
	}

	public void setTotalFees(Double totalFees) {
		this.totalFees = totalFees;
	}

	public Double getMinContribution() {
		return minContribution;
	}

	public void setMinContribution(Double minContribution) {
		this.minContribution = minContribution;
	}

	public Double getMaxContribution() {
		return maxContribution;
	}

	public void setMaxContribution(Double maxContribution) {
		this.maxContribution = maxContribution;
	}

	public PieChartData getPdata() {
		return pdata;
	}

	public void setPdata(PieChartData pdata) {
		this.pdata = pdata;
	}

	public ChartData getData() {
		return data;
	}

	public void setData(ChartData data) {
		this.data = data;
	}

	public Double getTotalContributions() {
		return totalContributions;
	}

	public void setTotalContributions(Double totalContributions) {
		this.totalContributions = totalContributions;
	}

	public Double getTotalExpenses() {
		return totalExpenses;
	}

	public void setTotalExpenses(Double totalExpenses) {
		this.totalExpenses = totalExpenses;
	}

	public List<Transaction> getExpenses() {
		return expenses;
	}

	public void setExpenses(List<Transaction> expenses) {
		this.expenses = expenses;
	}

	public List<Contribution> getContributions() {
		return contributions;
	}

	public void setContributions(List<Contribution> contributions) {
		this.contributions = contributions;
	}

	@Transient
	public String getStatusDescription() {
		String desc = "";
		if (status == 0)
			desc = "Pas Commencee";
		else if (status == 1)
			desc = "En cours";
		else if (status == 2)
			desc = "Annulee";
		else if (status == 3)
			desc = "Terminee";
		else if (status == 4)
			desc = "Reportee";

		return desc;
	}

	public boolean isHasPhoto() {
		return hasPhoto;
	}

	public void setHasPhoto(boolean hasPhoto) {
		this.hasPhoto = hasPhoto;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSponsors() {
		return sponsors;
	}

	public void setSponsors(String sponsors) {
		this.sponsors = sponsors;
	}

	public Double getBudget() {
		return budget;
	}

	public void setBudget(Double budget) {
		this.budget = budget;
	}

	public Date getProjectedStartDate() {
		return projectedStartDate;
	}

	public void setProjectedStartDate(Date projectedStartDate) {
		this.projectedStartDate = projectedStartDate;
	}

	public Date getProjectedEndDate() {
		return projectedEndDate;
	}

	public void setProjectedEndDate(Date projectedEndDate) {
		this.projectedEndDate = projectedEndDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<String> getChildEntities() {
		return Arrays.asList("videos");
	}
	
	@Transient
	public String getShortDescription() {
		return description != null && description.length() > 200 ? Utils.truncateHTML(description, 200, null)
				: description;
	}
	
	
	@Transient
	public String getShortMessage() {
		return description != null && description.length() > 100 ? Utils.truncateHTML(description,100,null) : description;
	}

	@Transient
	public String getMediumMessage() {
		return description != null && description.length() > 200 ? Utils.truncateHTML(description,200,null) : description;
	}
	
	@Transient
	public String getShortTitle() {
		return title != null && title.length() > 50 ? Utils.truncateString(title,50) : title;
	}
	
}
