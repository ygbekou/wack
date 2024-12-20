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

	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "COMPANY_ID")
	private Company company;

	@Column(name = "BUDGET")
	private Double budget;
	
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
	
	@Transient
	List<ProjectDesc> projectDescs;

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

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
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

	public Double getBudget() {
		return budget;
	}

	public void setBudget(Double budget) {
		this.budget = budget;
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
		return Arrays.asList("videos", "projectDescs");
	}

	
	public List<ProjectDesc> getProjectDescs() {
		return projectDescs;
	}

	public void setProjectDescs(List<ProjectDesc> projectDescs) {
		this.projectDescs = projectDescs;
	}

	@Transient
	public Date getRealStartDate() {
		return startDate != null ? startDate: projectedStartDate;
	}
	
	@Transient
	public Date getRealEndDate() {
		return endDate != null ? endDate: projectedEndDate;
	}
}
