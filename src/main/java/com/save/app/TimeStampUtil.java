package com.save.app;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Logger;

public class TimeStampUtil {

	final static Logger logger = Logger.getLogger(TimeStampUtil.class);

	public static String getCurrentTimeStamp() {

		String date = null;
		try {
			DateFormat dateFormat = new SimpleDateFormat(WebConstants.DATE_FORMAT);
			Calendar cal = Calendar.getInstance();
			date = dateFormat.format(cal.getTime());
			// logger.info("returning date: " + date);
		} catch (Exception e) {

			logger.error("Exception whyle getting date: " + e.getMessage());
		}

		return date;
	}
}
