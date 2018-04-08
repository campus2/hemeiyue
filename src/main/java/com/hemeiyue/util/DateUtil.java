package com.hemeiyue.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	/**
	 * 时间戳转日期（yyyy-MM-dd）
	 * @return
	 */
	public static String dateToString(Timestamp date) {
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    return formatter.format(date);
	}
	
	/**
	 * 时间戳转小时分（HH:mm）
	 * @param date
	 * @return
	 */
	public static String timeToString(Timestamp beginTime, Timestamp endTime) {
	    SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
	    return formatter.format(beginTime)+"-"+formatter.format(endTime);
	}
	
	/**
	 * 日期转星期
	 * @param dateTime
	 * @return
	 */
	public static String dateToWeek(String dateTime) {
		String[] weekdays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		//获取日历
		Calendar cal = Calendar.getInstance();
		Date datet = null;
		try {
			datet = formatter.parse(dateTime);
			cal.setTime(datet);
		} catch (ParseException e) {
			System.out.println("日期转化失败");
			e.printStackTrace();
		}
		//一个星期中的某天
		int w = cal.get(Calendar.DAY_OF_WEEK)-1;
		if(w < 0) w = 0;
		return weekdays[w];
	}
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
	
	/**
	 * 返回HH:mm的时间戳
	 * @param time
	 * @return
	 */
	public static Timestamp time2stamp(String time) {
		if(time==null || time.isEmpty()) {
			throw new IllegalArgumentException("时间戳为空");
		}
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
	    try {
			return new Timestamp(formatter.parse(time).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String time() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
	    Date currentTime = new Date();
	    String dateString = formatter.format(currentTime);
	    return dateString;
	}
}