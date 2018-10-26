package com.qkcare.model.mis;

import java.util.ArrayList;
import java.util.List;

public class ReportTree  {
	
	private String label;
	private String data;
	private String expandedIcon;
	private String collapsedIcon;
	private List<Child> children = new ArrayList<>();
	
	public ReportTree(String label, String data, String expandedIcon, String collapsedIcon) {
		this.label = label;
		this.data = data;
		this.expandedIcon = expandedIcon;
		this.collapsedIcon = collapsedIcon;
	}
	
	public void addChild(String label, String icon, String data) {
		children.add(new Child(label, icon, data));
	}
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getExpandedIcon() {
		return expandedIcon;
	}
	public void setExpandedIcon(String expandedIcon) {
		this.expandedIcon = expandedIcon;
	}
	public String getCollapsedIcon() {
		return collapsedIcon;
	}
	public void setCollapsedIcon(String collapsedIcon) {
		this.collapsedIcon = collapsedIcon;
	}
	public List<Child> getChildren() {
		return children;
	}
	public void setChildren(List<Child> children) {
		this.children = children;
	}

	private class Child {
		private String label;
		private String icon;
		private String data;
		
		public Child(String label, String icon, String data) {
			this.label = label;
			this.icon = icon;
			this.data = data;
		}
		
		public String getLabel() {
			return label;
		}
		public void setLabel(String label) {
			this.label = label;
		}
		public String getIcon() {
			return icon;
		}
		public void setIcon(String icon) {
			this.icon = icon;
		}
		public String getData() {
			return data;
		}
		public void setData(String data) {
			this.data = data;
		}
	}

	
}
