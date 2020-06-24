package com.wack.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GenericChartDto implements Serializable {
	
	private List<String> labels;
	private List<DataSet> datasets;

	
	public GenericChartDto() {}
	
	public List<String> getLabels() {
		return labels;
	}

	public void setLabels(List<String> labels) {
		this.labels = labels;
	}

	public List<DataSet> getDatasets() {
		return datasets;
	}

	public void setDatasets(List<DataSet> datasets) {
		this.datasets = datasets;
	}

	public void addDataset(DataSet dataSet) {
		if (datasets == null) {
			this.datasets = new ArrayList<DataSet>();
		}
		
		this.datasets.add(dataSet);
	}
	
	public void addLabel(String label) {
		if (labels == null) {
			this.labels = new ArrayList<String>();
		}
		
		this.labels.add(label);
	}
	
}
