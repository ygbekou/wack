package com.softenza.emarket.controller;

import com.softenza.emarket.util.Constants;

public class BaseController {

	protected Class getClass(String entity) throws ClassNotFoundException {
		return Class.forName(entity.contains(".") ? entity : Constants.PACKAGE_NAME + entity);
	}
	
	protected String convertEntity(String entity) {
		return entity.replaceAll("_", ".");
	}

	protected String getExtraWhereClause(String entity) {
		String extraWhereClause = "";
		
		if (entity.contains("Category c, Product")) {
			extraWhereClause = " AND c = e.category ";
		}
		
		if (entity.contains("PatientSale ps, PatientSaleProduct")) {
			extraWhereClause = " AND ps = e.patientSale ";
		}
		
		return extraWhereClause;
	}
}
