package com.azhen.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.ConversionException;

import cc.messcat.entity.EnterpriseNews;

/**
 * @author kgdoqi extends saiyo
 * 
 */
public class DateHelper {

	public Calendar cal;
	private Integer year, day, month, hour, minute;

	/**
	 * 
	 */
	public DateHelper() {
		cal = Calendar.getInstance();
		year = Integer.valueOf(cal.get(Calendar.YEAR));
		day = Integer.valueOf(cal.get(Calendar.DAY_OF_MONTH));
		month = Integer.valueOf(cal.get(Calendar.MONTH) + 1);
		hour = Integer.valueOf(cal.get(Calendar.HOUR_OF_DAY));
		minute = Integer.valueOf(cal.get(Calendar.MINUTE));
	}

	//
	public String getCurrentDate(String language) {

		if (language.equals("EN")) {
			String currentDateEN = month + "-" + day + "-" + year + " " + hour + ":" + minute;
			return currentDateEN;
		}
		String currentDateCN = year + "年" + month + "月" + day + "日" + hour + ":" + minute;
		return currentDateCN;
	}

	public String getCurrentDate() {
		String currentDateEN = year + "-" + month + "-" + day;
		return currentDateEN;
	}

	// 
	public String getRandomNum() {
		String strTime = year.toString() + month.toString() + day.toString() + hour.toString() + minute.toString();
		String strRan = strTime + String.valueOf(Math.round(Math.random() * 8999 + 1000));
		return strRan;
	}

	public String getCurrentDateTimeNum() {
		String currentDateEN = year + "" + month + "" + day + "" + hour + "" + minute + "";
		return currentDateEN;
	}

	public int compareDate(String strDate1, String strDate2) {
		Date date1 = convertToDate(strDate1);
		Date date2 = convertToDate(strDate2);
		return date1.compareTo(date2);
	}

	public Date convertToDate(String strDate) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		if (strDate != null && !"".equals(strDate)) {
			try {
				date = df.parse((String) strDate);
			} catch (Exception pe) {
				pe.printStackTrace();
				throw new ConversionException("Error converting String to Date");
			}
		}
		return date;
	}

	/**
	 * 获取一个日期，该日期是在date的days天之前(days是负数)或之后(days是正数)
	 * 
	 * @param date
	 * @param days
	 * @return Date
	 */
	public static Date getDateByCalculateDays(Date date, int days) {
		Calendar calender = Calendar.getInstance();
		calender.setTime(date);
		calender.add(Calendar.DATE, days);
		return calender.getTime();
	}

	/**
	 * 判断传入的date是否是新日期，将当前时间减去days天得出一个时间date2，然后再用date和date2比较，
	 * 如果date在date2之后或等于date2返回true，否则返回false
	 * 
	 * @param days
	 * @return boolean
	 * @throws Exception
	 */
	public static boolean isNewDate(Date date, int days) throws Exception {
		if (date == null) {
			throw new Exception("日期不能为空！");
		}
		Date date2 = getDateByCalculateDays(new Date(), days * (-1));
		int compareResult = date.compareTo(date2);
		if (compareResult < 0) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 传入一个list，及对应的字段，获取到所含有的日期分类
	 */
	public static Set<Date> getMonthListFromList(List<EnterpriseNews> list) throws Exception {
		Set<Date> epNewSet = new LinkedHashSet<Date>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		for (EnterpriseNews enterpriseNews : list) {
			Date tempDate = enterpriseNews.getInitTime();
			String tempDateString = sdf.format(tempDate);
			tempDate = sdf.parse(tempDateString);
			epNewSet.add(tempDate);
		}
		return epNewSet;
	}

	/**
	 * 把日期按照某个格式转换成字符串
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String dataToString(Object date, String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(date);
	}

	/**
	 * 把字符串按照某个格式转换成日期类型
	 * 
	 * @param strDate
	 * @param format
	 * @return
	 */
	public static Date stringToDate(String strDate, String format) throws Exception {
		DateFormat df = new SimpleDateFormat(format);
		Date date = null;
		if (strDate != null && !"".equals(strDate)) {
			date = df.parse((String) strDate);
		}
		return date;
	}

}
