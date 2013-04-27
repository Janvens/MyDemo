/**
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: �������ƿƼ����޹�˾</p>
 * <p>2012-11-19����03:33:05</p>
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
 * <p>�����ˣ�Zhang Wensheng �������ڣ�2012-11-19 </p>
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
	 * ��ʽ��ʱ���Ϊ�ַ���
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
	 * ��ʽ���ַ���Ϊʱ���
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
	 * �����ڸ�ʽ���ַ���ת��Ϊ����
	 * @param date Դ�����ַ���
	 * @param format Դ�����ַ�����ʽ
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
	 * �����ڸ�ʽ���ַ�����ʽ����ָ�������ڸ�ʽ
	 * @param dateStr Դ�����ַ���
	 * @param formatStr1 Դ�����ַ������ڸ�ʽ
	 * @param formatStr2 �������ַ������ڸ�ʽ
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
	 * ��õ�ǰʱ�������µĵ�һ������һ��
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
		//����
		ca.set(year, month-1, day);
		ca.set(Calendar.DAY_OF_MONTH,1);
		fromTime = sdf.format(ca.getTime());
		ca.add(Calendar.MONTH,   1);   
		ca.add(Calendar.DAY_OF_MONTH,-1);
		toTime = sdf.format(ca.getTime());
		//�ϸ���
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
			throw new ParseException("ʱ���ʽת���쳣",0);
		}
		return date;
	}
	
	/**
	 * 
	* <p>��������: getMoreMonth|����: ��ȡ����·�</p>
	* @param date ��Ҫ���������   ��:2010-09-01
	* @param monthNumber ��Ҫ�����·�  ��: monthNumber = 3 --- > 201007, 201008, 201009
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
	* <p>��������: getSpecifyDate|����: ����ָ��������dateTime:20101127, ��ȡ�����������dayNumber:3, ���ؽ��Ϊ20101130.</p>
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
