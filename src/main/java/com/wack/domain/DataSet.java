package com.wack.domain;

import java.util.ArrayList;
import java.util.List;

public class DataSet {
	private String label;
	private String backgroundColor;
    private String borderColor;
    private List<Double> data;
    private Double total = 0d;
    
    public DataSet() {
    	
    }
    
    public DataSet(String label, String backgroundColor, String borderColor) {
    	this.label = label;
    	this.backgroundColor = backgroundColor;
    	this.borderColor = borderColor;
    }

	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getBackgroundColor() {
		return backgroundColor;
	}
	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	public String getBorderColor() {
		return borderColor;
	}
	public void setBorderColor(String borderColor) {
		this.borderColor = borderColor;
	}
	public List<Double> getData() {
		return data;
	}
	public void setData(List<Double> data) {
		this.data = data;
	}
	
	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public void addDataItem(Double dataItem) {
		if (data == null) {
			this.data = new ArrayList<Double>();
		}
		this.data.add(dataItem);
		this.total += dataItem;
	}
}
