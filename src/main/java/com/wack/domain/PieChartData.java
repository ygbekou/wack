package com.wack.domain;

import java.util.List;
import java.util.Set;

public class PieChartData {

	Set<String> labels;
	List<PieDataset> datasets;
	public Set<String> getLabels() {
		return labels;
	}
	public void setLabels(Set<String> labels) {
		this.labels = labels;
	}
	public List<PieDataset> getDatasets() {
		return datasets;
	}
	public void setDatasets(List<PieDataset> datasets) {
		this.datasets = datasets;
	} 
	
	
}
