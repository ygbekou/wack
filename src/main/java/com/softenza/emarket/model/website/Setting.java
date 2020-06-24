package com.softenza.emarket.model.website;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.softenza.emarket.model.BaseEntity;
 
@Entity
@Table(name="SETTING")
public class Setting extends BaseEntity {
	
	@Id
	@Column(name ="SETTING_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int fixedMenu;
	private int leftToRight;
	private int headerTextPosition;
	private String headerImageType;
	private String themeColor;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getFixedMenu() {
		return fixedMenu;
	}
	public void setFixedMenu(int fixedMenu) {
		this.fixedMenu = fixedMenu;
	}
	public int getLeftToRight() {
		return leftToRight;
	}
	public void setLeftToRight(int leftToRight) {
		this.leftToRight = leftToRight;
	}
	public int getHeaderTextPosition() {
		return headerTextPosition;
	}
	public void setHeaderTextPosition(int headerTextPosition) {
		this.headerTextPosition = headerTextPosition;
	}
	public String getHeaderImageType() {
		return headerImageType;
	}
	public void setHeaderImageType(String headerImageType) {
		this.headerImageType = headerImageType;
	}
	public String getThemeColor() {
		return themeColor;
	}
	public void setThemeColor(String themeColor) {
		this.themeColor = themeColor;
	};
}
