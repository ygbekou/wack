package com.qkcare.controller;

import com.qkcare.util.Constants;

public class BaseController {

	public Class getClass(String entity) throws ClassNotFoundException {
		return Class.forName(entity.contains(".") ? entity : Constants.PACKAGE_NAME + entity);
	}
}
