package com.wack.util;

import java.util.Locale;

public class Constants {
	public final static String PACKAGE_NAME = "com.wack.model.";
	public final static String VALIDATOR_PACKAGE_NAME = "com.wack.validator.";
	
	/**
	 * LOCAL - Panawe	 
	 * 
	public static String DOC_FOLDER = "C:\\My Projects\\batandbel\\src\\assets\\docs";
	public static String PIC_FOLDER = "C:\\My Projects\\batandbel\\src\\assets\\images";
	public static String REPORT_RESULT_FOLDER = "C:\\My Projects\\batandbel\\src\\assets\\reports";  
	public static String DATA_FOLDER = "C:\\My Projects\\batandbel\\src\\assets\\data";
	*/
	
	/**
	 * LOCAL	 
	*/
	public static String DOC_FOLDER = "C:\\Development\\arbamo\\src\\assets\\docs";
	public static String PIC_FOLDER = "C:\\Development\\arbamo\\src\\assets\\images";
	public static String REPORT_RESULT_FOLDER = "C:\\Development\\arbamo\\src\\assets\\reports"; 
	public static String DATA_FOLDER = "C:\\Development\\arbamo\\src\\assets\\data";
	 
	/**
	 * PROD	ROOT
	 *  
	 *
	public static String REPORT_RESULT_FOLDER = "/home/ec2-user/apps/apache-tomcat-9.0.0.M22/webapps/ROOT/assets/reports"; 
	public static String PIC_FOLDER="/home/ec2-user/apps/apache-tomcat-9.0.0.M22/webapps/ROOT/assets/images";
	public static String DOC_FOLDER = "/home/ec2-user/apps/apache-tomcat-9.0.0.M22/webapps/ROOT/assets/docs"; 
	public static String DATA_FOLDER = "/home/ec2-user/apps/apache-tomcat-9.0.0.M22/webapps/ROOT/assets/data";
	*/
	/**
	 * PROD	bidamaservices
	 *  
	 *
	public static String REPORT_RESULT_FOLDER = "/home/ec2-user/apps/apache-tomcat-9.0.0.M22/webapps/bidamaservices/assets/reports"; 
	public static String PIC_FOLDER="/home/ec2-user/apps/apache-tomcat-9.0.0.M22/webapps/bidamaservices/assets/images";
	public static String DOC_FOLDER = "/home/ec2-user/apps/apache-tomcat-9.0.0.M22/webapps/bidamaservices/assets/docs"; 
	public static String DATA_FOLDER = "/home/ec2-user/apps/apache-tomcat-9.0.0.M22/webapps/bidamaservices/assets/data";
	*/
	
	public static Locale LOCALE = new Locale( "fr", "FR" );		
	public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 5000*60*60;
    public static final String SIGNING_KEY = "devglan123r";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    
    public static String EMAIL_TEMPLATE_1 = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"><html xmlns=\"http://www.w3.org/1999/xhtml\"><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /><meta name=\"viewport\" content=\"width=device-width, initial-scale=1\" /><title>ORG_NAME</title><style type=\"text/css\" media=\"screen\">/* Force Hotmail to display emails at full width */ .ExternalClass { display: block !important; width: 100%; } /* Force Hotmail to display normal line spacing */ .ExternalClass, .ExternalClass p, .ExternalClass span, .ExternalClass font, .ExternalClass td, .ExternalClass div { line-height: 100%; } body, p, h1, h2, h3, h4, h5, h6 { margin: 0; padding: 0; } body, p, td { font-family: Arial, Helvetica, sans-serif; font-size: 15px; color: #333333; line-height: 1.5em; } h1 { font-size: 24px; font-weight: normal; line-height: 24px; } body, p { margin-bottom: 0; -webkit-text-size-adjust: none; -ms-text-size-adjust: none; } img { line-height: 100%; outline: none; text-decoration: none; -ms-interpolation-mode: bicubic; } a img { border: none; } .background { background-color: #343a40; } table.background { margin: 0; padding: 0; width: 100% !important; } .block-img { display: block; line-height: 0; } a { color: white; text-decoration: none; } a, a:link { color: #2A5DB0; text-decoration: underline; } table td { border-collapse: collapse; } td { vertical-align: top; text-align: left; } .wrap { width: 600px; } .wrap-cell { padding-top: 30px; padding-bottom: 30px; } .header-cell, .body-cell, .footer-cell { padding-left: 20px; padding-right: 20px; } .header-cell { background-color: #eeeeee; font-size: 24px; color: #ffffff; } .body-cell { background-color: #ffffff; padding-top: 30px; padding-bottom: 34px; } .footer-cell { background-color: ##343a40; text-align: center; font-size: 13px; padding-top: 30px; padding-bottom: 30px; } .card { width: 400px; margin: 0 auto; } .data-heading { text-align: right; padding: 10px; background-color: #ffffff; font-weight: bold; } .data-value { text-align: left; padding: 10px; background-color: #ffffff; } .force-full-width { width: 100% !important; }</style><style type=\"text/css\" media=\"only screen and (max-width: 600px)\">@media only screen and (max-width: 600px) { body[class*=\"background\"], table[class*=\"background\"], td[class*=\"background\"] { background: ##343a40 !important; } table[class=\"card\"] { width: auto !important; } td[class=\"data-heading\"], td[class=\"data-value\"] { display: block !important; } td[class=\"data-heading\"] { text-align: left !important; padding: 10px 10px 0; } table[class=\"wrap\"] { width: 100% !important; } td[class=\"wrap-cell\"] { padding-top: 0 !important; padding-bottom: 0 !important; } }</style></head><body bgcolor=\"#eeeeee\"><center><table cellpadding=\"0\" cellspacing=\"0\" class=\"force-full-width\"><tr><td height=\"60\" valign=\"top\" class=\"header-cell\"><img width=\"196\" height=\"60\" src=\"http://ORG_WEBSITE/assets/images/logo.png\" alt=\"logo\"></td></tr><tr><td valign=\"top\" class=\"body-cell\"><table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" bgcolor=\"#ffffff\"><tr><td valign=\"top\" style=\"padding-bottom:20px; background-color:#ffffff;\">EMAILHEADER</td></tr><tr><td><table cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" bgcolor=\"#ffffff\"><tr><td align=\"center\" style=\"padding:20px 0;\"><center><table cellspacing=\"0\" cellpadding=\"0\"  ><tr><td style=\"background-color:green; text-align:center; padding:10px; color:white; \">CONTENTHEADER</td></tr><tr><td style=\"border:1px solid green;\"><table cellspacing=\"0\" cellpadding=\"20\" width=\"100%\"><tr><td><table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" bgcolor=\"#ffffff\">CONTENTBODY</table></td></tr></table></td></tr></table></center></td></tr></table> </td></tr><tr><td style=\"padding-top:20px;background-color:#ffffff;\">Merci et bonne journee!<br>L'Equipe ORG_NAME</td></tr></table></td></tr><tr><td valign=\"top\" class=\"footer-cell\"> 	ORG_NAME<br>ORG_WEBSITE<br>ORG_ADDRESS</td></tr></table></center></body></html>";
	public static String EMAIL_TEMPLATE_2 = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"><html xmlns=\"http://www.w3.org/1999/xhtml\"><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /><meta name=\"viewport\" content=\"width=device-width, initial-scale=1\" /><title>ORG_NAME</title><style type=\"text/css\" media=\"screen\">/* Force Hotmail to display emails at full width */ .ExternalClass { display: block !important; width: 100%; } /* Force Hotmail to display normal line spacing */ .ExternalClass, .ExternalClass p, .ExternalClass span, .ExternalClass font, .ExternalClass td, .ExternalClass div { line-height: 100%; } body, p, h1, h2, h3, h4, h5, h6 { margin: 0; padding: 0; } body, p, td { font-family: Arial, Helvetica, sans-serif; font-size: 15px; color: #333333; line-height: 1.5em; } h1 { font-size: 24px; font-weight: normal; line-height: 24px; } body, p { margin-bottom: 0; -webkit-text-size-adjust: none; -ms-text-size-adjust: none; } img { line-height: 100%; outline: none; text-decoration: none; -ms-interpolation-mode: bicubic; } a img { border: none; } .background { background-color: #343a40; } table.background { margin: 0; padding: 0; width: 100% !important; } .block-img { display: block; line-height: 0; } a { color: white; text-decoration: none; } a, a:link { color: #2A5DB0; text-decoration: underline; } table td { border-collapse: collapse; } td { vertical-align: top; text-align: left; } .wrap { width: 600px; } .wrap-cell { padding-top: 30px; padding-bottom: 30px; } .header-cell, .body-cell, .footer-cell { padding-left: 20px; padding-right: 20px; } .header-cell { background-color: #eeeeee; font-size: 24px; color: #ffffff; } .body-cell { background-color: #ffffff; padding-top: 30px; padding-bottom: 34px; } .footer-cell { background-color: ##343a40; text-align: left; font-size: 13px; padding-top: 30px; padding-bottom: 30px; } .card { width: 400px; margin: 0 auto; } .data-heading { text-align: right; padding: 10px; background-color: #ffffff; font-weight: bold; } .data-value { text-align: left; padding: 10px; background-color: #ffffff; } .force-full-width { width: 100% !important; }</style><style type=\"text/css\" media=\"only screen and (max-width: 600px)\">@media only screen and (max-width: 600px) { body[class*=\"background\"], table[class*=\"background\"], td[class*=\"background\"] { background: ##343a40 !important; } table[class=\"card\"] { width: auto !important; } td[class=\"data-heading\"], td[class=\"data-value\"] { display: block !important; } td[class=\"data-heading\"] { text-align: left !important; padding: 10px 10px 0; } table[class=\"wrap\"] { width: 100% !important; } td[class=\"wrap-cell\"] { padding-top: 0 !important; padding-bottom: 0 !important; } }</style></head><body bgcolor=\"#eeeeee\"><center><table cellpadding=\"0\" cellspacing=\"0\" class=\"force-full-width\"><tr><td height=\"60\" valign=\"top\" class=\"header-cell\"><img width=\"196\" height=\"60\" src=\"http://ORG_WEBSITE/assets/images/logo.png\" alt=\"logo\"></td></tr><tr><td valign=\"top\" class=\"body-cell\"><table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" bgcolor=\"#ffffff\"><tr><td valign=\"top\" style=\"padding-bottom:20px; background-color:#ffffff;\">EMAIL_BODY</td></tr><tr><td style=\"padding-top:20px;background-color:#ffffff;\"><br>AUTHOR</td></tr></table></td></tr><tr><td valign=\"top\" class=\"footer-cell\"> 	ORG_NAME<br>ORG_WEBSITE<br>ORG_ADDRESS</td></tr></table></center></body></html>";
	public static String EMAIL_TEMPLATE_SMS = "EMAIL_BODY\r\n" +  
			"<br/><strong>AUTHOR</strong>\r\n" +  
			"<br/><strong>ORG_NAME</strong>\r\n" +  
			"<br/>ORG_WEBSITE\r\n"+
			"<br/>ORG_ADDRESS";
}
