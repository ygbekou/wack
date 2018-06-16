package com.qkcare.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	public static Date addOneDay(Date date) {
		return addDays(date, 1);
	}

	public static Date addDays(Date date, int numberOfDays) {
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(date); 
		cal.add(Calendar.DATE, numberOfDays);
		return cal.getTime();
	}
	
	public static String formatDate(Date date, String formatString) {
		SimpleDateFormat dt1 = new SimpleDateFormat(formatString);
        return dt1.format(date);
	}
}
