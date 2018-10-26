package com.qkcare.model.mis;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.qkcare.model.BaseEntity;

@Entity
@Table(name="PARAMETER")
public class Parameter extends BaseEntity {

	@Id
	@Column(name = "PARAMETER_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@Column(name = "DISPLAY_NAME")
	private String displayName;

	@Column(name = "DATA_TYPE")
	private String dataType;

	@Column(name = "INPUT_TYPE")
	private String inputType;

	@Column(name = "PARAMETER_SQL")
	private String parameterSql;

	@Column(name = "PARAMETER_VALUES")
	private String parameterValues;

	private String size;

	@Column(name = "MAX_LENGTH")
	private String maxLength;

	@Transient
	private String value;
	
	@Transient
	private List<Reference> values;

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getInputType() {
		return inputType;
	}

	public void setInputType(String inputType) {
		this.inputType = inputType;
	}

	public String getParameterSql() {
		return parameterSql;
	}

	public void setParameterSql(String parameterSql) {
		this.parameterSql = parameterSql;
	}

	public String getParameterValues() {
		return parameterValues;
	}

	public void setParameterValues(String parameterValues) {
		this.parameterValues = parameterValues;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}


	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(String maxLength) {
		this.maxLength = maxLength;
	}

	public List<Reference> getValues() {
		return values;
	}

	public void setValues(List<Reference> values) {
		this.values = values;
	}
}
