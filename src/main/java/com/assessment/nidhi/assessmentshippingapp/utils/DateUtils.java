package com.assessment.nidhi.assessmentshippingapp.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	
	public static String convertTimeToStringFormat(Long epochTime) {
        Date date = new Date(epochTime);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return format.format(date);
    }
}
