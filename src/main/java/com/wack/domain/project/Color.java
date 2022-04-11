package com.wack.domain.project;

public class Color {
	String borderColor = "#283593";
	String backgroundColor = "#283593";
	String hoverBackgroundColor = "#283593";
	String hoverBorderColor = "#283593";
	Integer borderWidth = 3;
	boolean fill = false;
	Double lineTension = 0.4;
	String pointBackgroundColor = "#FFFFFF";
	String pointBorderColor = "#283593";
	Integer pointBorderWidth = 2;
	Integer pointRadius = 2;

	public Color(String borderColor, String pointBackgroundColor, String pointBorderColor, String backgroundColor,
			String hoverBackgroundColor, String hoverBorderColor) {
		this.borderColor = borderColor;
		this.pointBackgroundColor = pointBackgroundColor;
		this.pointBorderColor = pointBorderColor;
		this.backgroundColor = backgroundColor;
		this.hoverBackgroundColor = hoverBackgroundColor;
		this.hoverBorderColor = hoverBorderColor;
	}

	public String getBorderColor() {
		return borderColor;
	}

	public String getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public String getHoverBackgroundColor() {
		return hoverBackgroundColor;
	}

	public void setHoverBackgroundColor(String hoverBackgroundColor) {
		this.hoverBackgroundColor = hoverBackgroundColor;
	}

	public String getHoverBorderColor() {
		return hoverBorderColor;
	}

	public void setHoverBorderColor(String hoverBorderColor) {
		this.hoverBorderColor = hoverBorderColor;
	}

	public void setBorderColor(String borderColor) {
		this.borderColor = borderColor;
	}

	public Integer getBorderWidth() {
		return borderWidth;
	}

	public void setBorderWidth(Integer borderWidth) {
		this.borderWidth = borderWidth;
	}

	public boolean isFill() {
		return fill;
	}

	public void setFill(boolean fill) {
		this.fill = fill;
	}

	public Double getLineTension() {
		return lineTension;
	}

	public void setLineTension(Double lineTension) {
		this.lineTension = lineTension;
	}

	public String getPointBackgroundColor() {
		return pointBackgroundColor;
	}

	public void setPointBackgroundColor(String pointBackgroundColor) {
		this.pointBackgroundColor = pointBackgroundColor;
	}

	public String getPointBorderColor() {
		return pointBorderColor;
	}

	public void setPointBorderColor(String pointBorderColor) {
		this.pointBorderColor = pointBorderColor;
	}

	public Integer getPointBorderWidth() {
		return pointBorderWidth;
	}

	public void setPointBorderWidth(Integer pointBorderWidth) {
		this.pointBorderWidth = pointBorderWidth;
	}

	public Integer getPointRadius() {
		return pointRadius;
	}

	public void setPointRadius(Integer pointRadius) {
		this.pointRadius = pointRadius;
	}

}
