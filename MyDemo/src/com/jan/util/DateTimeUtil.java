/**
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: 联动优势科技有限公司</p>
 * <p>2012-11-19下午03:33:05</p>
 * @author Zhang Wensheng
 * @version 1.0
 */
package com.jan.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/** 
 * desc:
 * <p>创建人：Zhang Wensheng 创建日期：2012-11-19 </p>
 * @version V1.0  
 */
public class DateTimeUtil{

	public static String date8(){
		return date8(new Timestamp(System.currentTimeMillis()));
	}
	public static String date8(Timestamp t){
		return formatTimestamp2String(t,"yyyyMMdd");
	}
	public static String time6(){
		return time6(new Timestamp(System.currentTimeMillis()));
	}
	public static String time6(Timestamp t){
		return formatTimestamp2String(t,"HHmmss");
	}
	public static String date6(){
		return date6(new Timestamp(System.currentTimeMillis()));
	}
	public static String date6(Timestamp t){
		return formatTimestamp2String(t, "yyyyMM");
	}
	
	/**
	 * 格式化时间戳为字符串
	 * @param date
	 * @param format
	 * @return
	 */
	public static String formatTimestamp2String(Object date,String format){
		if(null==date)return null;
		if(null==format||("").equals(format))return null;
		if((date instanceof Date)||(date instanceof Timestamp)){
			return new SimpleDateFormat(format).format(date);
		}else{
			return null;
		}
	}
	/**
	 * 格式化字符串为时间戳
	 * @param date
	 * @param format
	 * @return
	 */
	public static Timestamp formatString2Timestamp(String date,String format){
		if(null==date||("").equals(date))return null;
		if(null==format||("").equals(format))return null;
		return new Timestamp(formatStringToDate(date,format).getTime());
	}
	
	/**
	 * 将日期格式的字符串转换为日期
	 * @param date 源日期字符串
	 * @param format 源日期字符串格式
	 */
	public static Date formatStringToDate(String date,String format){
		if(null==date||("").equals(date))return null;
		if(null==format||("").equals(format))return null;
		SimpleDateFormat format2 = new SimpleDateFormat(format);
		try{
			Date newDate = format2.parse(date);
			return newDate;
		}catch(Exception ex){
			throw new RuntimeException(ex.getMessage());
		}
		
		
	}
	public static Timestamp addDay(Timestamp date,int day){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, day);
		return new Timestamp(cal.getTimeInMillis());
	}
	/**
	 * 将日期格式的字符串格式化成指定的日期格式
	 * @param dateStr 源日期字符串
	 * @param formatStr1 源日期字符串日期格式
	 * @param formatStr2 新日期字符串日期格式
	 */
	public static String formatDateStringToString(String dateStr,String formatStr1,String formatStr2){
		SimpleDateFormat format = new SimpleDateFormat(formatStr1);
		try{
			if(null==dateStr||("").equals(dateStr))return null;
			Date date = format.parse(dateStr);
			return new SimpleDateFormat(formatStr2).format(date);
		}catch(Exception ex){
			throw new RuntimeException(ex.getMessage());
		}
	}
	
	/**
	 * added by ZhangWenSheng 2010-02-23
	 * 获得当前时间所在月的第一天和最后一天
	 * @param dateTime yyyy-MM-dd
	 * @return
	 */
	public static Map<String,String> getTime(String dateTime){
		Map<String,String> timeMap = new HashMap<String, String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		int year = 0;
		int month = 0;
		int day = 0;
		if(dateTime.length()==8){
			StringBuilder sb = new StringBuilder(dateTime);
			year = Integer.parseInt(sb.substring(0,4));
			month = Integer.parseInt(sb.substring(4,6));
			day = Integer.parseInt(sb.substring(6,8));
		}else if(dateTime.length()==10){
			String[] dateArray = dateTime.split("-");
			year = Integer.parseInt(dateArray[0]);
			month = Integer.parseInt(dateArray[1]);
			day = Integer.parseInt(dateArray[2]);
		}
		Calendar ca = Calendar.getInstance();
		String fromTime ="";
		String toTime="";
		String FromTimePre="";
		String ToTimePre="";
		//本月
		ca.set(year, month-1, day);
		ca.set(Calendar.DAY_OF_MONTH,1);
		fromTime = sdf.format(ca.getTime());
		ca.add(Calendar.MONTH,   1);   
		ca.add(Calendar.DAY_OF_MONTH,-1);
		toTime = sdf.format(ca.getTime());
		//上个月
		ca.set(year, month-2, day);
		ca.set(Calendar.DAY_OF_MONTH,1);
		FromTimePre = sdf.format(ca.getTime());
		ca.add(Calendar.MONTH, 1);   
		ca.add(Calendar.DAY_OF_MONTH,-1);
		ToTimePre = sdf.format(ca.getTime());
		timeMap.put("fromTime", fromTime);
		timeMap.put("toTime", toTime);
		timeMap.put("fromTimePre", FromTimePre);
		timeMap.put("toTimePre", ToTimePre);
		return timeMap;
	}
	
	public static Date parseDate8(String dateStr) throws ParseException{
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		try{
			date = sdf.parse(dateStr);
		}catch (ParseException e){
			throw new ParseException("时间格式转换异常",0);
		}
		return date;
	}
	
	/**
	 * 
	* <p>方法名称: getMoreMonth|描述: 获取多个月份</p>
	* @param date 需要处理的日期   例:2010-09-01
	* @param monthNumber 需要几个月份  例: monthNumber = 3 --- > 201007, 201008, 201009
	* @return
	 */
	public static Map<String, String> getMoreMonth(String dateTime, int monthNumber) {
		Map <String, String> map = new HashMap<String, String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		if (dateTime != null && !"".equals(dateTime)) {
			dateTime = dateTime.replaceAll("-", "");
		} else {
			dateTime = sdf.format(new Date());
		}
		
		if (dateTime.length() != 8) {
			dateTime = sdf.format(new Date());
		}
		int year = 0;
		int month = 0;
		int day = 0;
		
		Calendar calendar = Calendar.getInstance();
		StringBuilder sb = new StringBuilder(dateTime);
		year = Integer.parseInt(sb.substring(0,4));
		month = Integer.parseInt(sb.substring(4,6));
		day = Integer.parseInt(sb.substring(6,8));
		
		String yearMonth = "";
		for (int i = monthNumber; i > 0; i--) {
			calendar.set(year, month - i, day);
			yearMonth = sdf.format(calendar.getTime());
			map.put("statmonth" + i, yearMonth.substring(0,6));
		}
		return map;
	}
	
	/**
	 * 
	* <p>方法名称: getSpecifyDate|描述: 根据指定的日期dateTime:20101127, 获取多少天的日期dayNumber:3, 返回结果为20101130.</p>
	* @param dateTime
	* @param dayNumber
	* @return
	 */
	public static String getSpecifyDate(String dateTime, int dayNumber) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		if (dateTime != null && !"".equals(dateTime)) {
			dateTime = dateTime.replaceAll("-", "");
		} else {
			dateTime = sdf.format(new Date());
		}
		
		if (dateTime.length() != 8) {
			dateTime = sdf.format(new Date());
		}
		
		int year = 0;
		int month = 0;
		int day = 0;
		
		Calendar calendar = Calendar.getInstance();
		StringBuilder sb = new StringBuilder(dateTime);
		year = Integer.parseInt(sb.substring(0,4));
		month = Integer.parseInt(sb.substring(4,6));
		day = Integer.parseInt(sb.substring(6,8));
		
		calendar.set(year, month - 1, day + dayNumber);
		
		return sdf.format(calendar.getTime());
		
	}
	
	public static String datetime12() {
		return new SimpleDateFormat("yyMMddHHmmss").format(new Date());
	}
}
