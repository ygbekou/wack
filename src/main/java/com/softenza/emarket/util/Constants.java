package com.softenza.emarket.util;

import java.util.Locale;

public class Constants {
	public final static String PACKAGE_NAME = "com.softenza.emarket.model.";
	public final static String VALIDATOR_PACKAGE_NAME = "com.softenza.emarket.validator.";
	
	/**
	 * LOCAL	 
	public static String DOC_FOLDER = "C:\\My Projects\\batandbel\\src\\assets\\docs";
	public static String PIC_FOLDER = "C:\\My Projects\\batandbel\\src\\assets\\images";
	public static String REPORT_RESULT_FOLDER = "C:\\My Projects\\batandbel\\src\\assets\\reports";  
	 */
	
	/**
	 * PROD	
	 *  
	 **/ 
	public static String REPORT_RESULT_FOLDER = "/home/panawe/apps/apache-tomcat-9.0.0.M22/webapps/ROOT/assets/reports"; 
	public static String PIC_FOLDER="/home/panawe/apps/apache-tomcat-9.0.0.M22/webapps/ROOT/assets/images";
	public static String DOC_FOLDER = "/home/panawe/apps/apache-tomcat-9.0.0.M22/webapps/ROOT/assets/docs"; 
	
	
	public static Locale LOCALE = new Locale( "fr", "FR" );		
	public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 5000*60*60;
    public static final String SIGNING_KEY = "devglan123r";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}
