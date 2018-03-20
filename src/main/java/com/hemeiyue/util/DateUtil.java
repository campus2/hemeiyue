package com.hemeiyue.util;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	/**
	 * 返回  yyyy-MM-dd HH:mm:ss 格式的时间
	 * @return
	 * @throws ParseException
	 */
	public static  Date dateTime() throws ParseException {
		Date date = new Date();//获得系统时间.
        SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
        String nowTime = sdf.format(date);
        Date time = sdf.parse( nowTime );
		return time;
	}
	
	public static Date date() throws ParseException{
		Date currentTime = new Date();
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    String dateString = formatter.format(currentTime);
	    ParsePosition pos = new ParsePosition(8);
	    Date currentTime_2 = formatter.parse(dateString, pos);
	    return currentTime_2;
	}
	
	public static String time() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
	    Date currentTime = new Date();
	    String dateString = formatter.format(currentTime);
	    return dateString;

	}
}