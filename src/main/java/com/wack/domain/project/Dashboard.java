package com.wack.domain.project;
import java.util.*;
public class Dashboard {
	
	Double budget;
	Double contribution;
	Double expenses;
	Integer nbrContributions;
	Integer nbrExpenses;
	Double maxContribution;
	Double minContribution;
	Double avgContribution;
	Double duration;
	Double maxExpense;
	Double minExpense;
	
	List<DataSet> dataSet;

	
	public Double getBudget() {
		return budget;
	}

	public void setBudget(Double budget) {
		this.budget = budget;
	}

	public Double getContribution() {
		return contribution;
	}

	public void setContribution(Double contribution) {
		this.contribution = contribution;
	}

	public Double getExpenses() {
		return expenses;
	}

	public void setExpenses(Double expenses) {
		this.expenses = expenses;
	}

	public Integer getNbrContributions() {
		return nbrContributions;
	}

	public void setNbrContributions(Integer nbrContributions) {
		this.nbrContributions = nbrContributions;
	}

	public Integer getNbrExpenses() {
		return nbrExpenses;
	}

	public void setNbrExpenses(Integer nbrExpenses) {
		this.nbrExpenses = nbrExpenses;
	}

	public Double getMaxContribution() {
		return maxContribution;
	}

	public void setMaxContribution(Double maxContribution) {
		this.maxContribution = maxContribution;
	}

	public Double getMinContribution() {
		return minContribution;
	}

	public void setMinContribution(Double minContribution) {
		this.minContribution = minContribution;
	}

	public Double getAvgContribution() {
		return avgContribution;
	}

	public void setAvgContribution(Double avgContribution) {
		this.avgContribution = avgContribution;
	}

	public Double getDuration() {
		return duration;
	}

	public void setDuration(Double duration) {
		this.duration = duration;
	}

	public Double getMaxExpense() {
		return maxExpense;
	}

	public void setMaxExpense(Double maxExpense) {
		this.maxExpense = maxExpense;
	}

	public Double getMinExpense() {
		return minExpense;
	}

	public void setMinExpense(Double minExpense) {
		this.minExpense = minExpense;
	}

	public List<DataSet> getDataSet() {
		return dataSet;
	}

	public void setDataSet(List<DataSet> dataSet) {
		this.dataSet = dataSet;
	}

}
