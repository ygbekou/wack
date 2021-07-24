package com.wack.domain;
import java.util.List;
import java.util.Set;
public class ChartData {
	Set<String> labels;
	List<DataSet> datasets; 	
	public Set<String> getLabels() {
		return labels;
	}
	public void setLabels(Set<String> labels) {
		this.labels = labels;
	}

	public List<DataSet> getDatasets() {
		return datasets;
	}

	public void setDatasets(List<DataSet> datasets) {
		this.datasets = datasets;
	}


}
