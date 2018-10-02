package com.qkcare.controller;

import com.qkcare.util.Constants;

public class BaseController {

	protected Class getClass(String entity) throws ClassNotFoundException {
		return Class.forName(entity.contains(".") ? entity : Constants.PACKAGE_NAME + entity);
	}
	
	protected String convertEntity(String entity) {
		return entity.replaceAll("_", ".");
	}
}
