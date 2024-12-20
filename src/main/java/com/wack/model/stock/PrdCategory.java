package com.wack.model.stock;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.wack.model.BaseEntity;


@Entity
@Table(name = "PRD_CATEGORY")
public class PrdCategory extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PRD_CATEGORY_ID")
	private Long id;

	private String image;

	@ManyToOne(optional = true)
	@JoinColumn(name = "PARENT_ID")
	private PrdCategory parent;

	@Column(name = "SORT_ORDER")
	private Integer sortOrder;

	private int status = 0;

	@OneToMany 
	@JoinColumn(name = "PRD_CATEGORY_ID", updatable=false )
	@Fetch(FetchMode.JOIN)
	private List<PrdCategoryDesc> prdCategoryDescs = new ArrayList<>();

	public PrdCategory() {
	}

	public PrdCategory getParent() {
		return parent;
	}

	public void setParent(PrdCategory parent) {
		this.parent = parent;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImage() {
		return this.image;
	}

	public Integer getSortOrder() {
		return this.sortOrder;
	}

	public int getStatus() {
		return this.status;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<PrdCategoryDesc> getPrdCategoryDescs() {
		return prdCategoryDescs;
	}

	public void setPrdCategoryDescs(List<PrdCategoryDesc> prdCategoryDescs) {
		this.prdCategoryDescs = prdCategoryDescs;
	}

}