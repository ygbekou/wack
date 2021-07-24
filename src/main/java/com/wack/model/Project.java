package com.wack.model;
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

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "SPONSORS")
	private String sponsors;

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
	private Short status=1;

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
	ChartData data ;
	
	@Transient 
	PieChartData pdata ;
	
	@Transient
	Double minContribution;
	
	@Transient
	Double maxContribution;

	@Transient
	Double totalFees;

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

	public boolean getStatus() {
		return status==1?true:false;
	}


	public void setStatus(boolean status) {
		this.status = (short) (status==true?1:0);
	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

	@Transient
	public String getShortDescription() {
		return description != null && description.length() > 100 ? Utils.truncateHTML(description,100,null) : description;
	}
}
