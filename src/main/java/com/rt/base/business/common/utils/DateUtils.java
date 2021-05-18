package com.rt.base.business.common.utils;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.*;

/**
 * 日期处理
 *
 * @author Mark initPlatform
 */
public class DateUtils {
	/** 时间格式(yyyy-MM-dd) */
	public final static String DATE_PATTERN = "yyyy-MM-dd";
	/** 时间格式(yyyy-MM-dd HH:mm:ss) */
	public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    /** 时间格式(yyyy-MM-dd-HH) */
    public final static String DATE_HOUR_PATTERN = "yyyy-MM-dd-HH";
    /** 时间格式(MM-dd) */
    public final static String DATE_DAY_PATTERN = "MM-dd";

    public final static String DATE_VIEW= "yyyy-MM-dd日HH:mm:ss";

    /** yyyy/MM/dd HH:mm:ss */
    public static final String FORMAT_DATE_TIME = "yyyy/MM/dd HH:mm:ss";

    /**
     * 日期格式化 日期格式为：yyyy-MM-dd
     * @param date  日期
     * @return  返回yyyy-MM-dd格式日期
     */
	public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    /**
     * 日期格式化 日期格式为：yyyy-MM-dd
     * @param date  日期
     * @param pattern  格式，如：DateUtils.DATE_TIME_PATTERN
     * @return  返回yyyy-MM-dd格式日期
     */
    public static String format(Date date, String pattern) {
        if(date != null){
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }

    /**
     * 字符串转换成日期
     * @param strDate 日期字符串
     * @param pattern 日期的格式，如：DateUtils.DATE_TIME_PATTERN
     */
    public static Date stringToDate(String strDate, String pattern) {
        if (StringUtils.isBlank(strDate)){
            return null;
        }

        DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern);
        return fmt.parseLocalDateTime(strDate).toDate();
    }

    /**
     * 根据周数，获取开始日期、结束日期
     * @param week  周期  0本周，-1上周，-2上上周，1下周，2下下周
     * @return  返回date[0]开始日期、date[1]结束日期
     */
    public static Date[] getWeekStartAndEnd(int week) {
        DateTime dateTime = new DateTime();
        LocalDate date = new LocalDate(dateTime.plusWeeks(week));

        date = date.dayOfWeek().withMinimumValue();
        Date beginDate = date.toDate();
        Date endDate = date.plusDays(6).toDate();
        return new Date[]{beginDate, endDate};
    }

    /**
     * 对日期的【秒】进行加/减
     *
     * @param date 日期
     * @param seconds 秒数，负数为减
     * @return 加/减几秒后的日期
     */
    public static Date addDateSeconds(Date date, int seconds) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusSeconds(seconds).toDate();
    }

    /**
     * 对日期的【分钟】进行加/减
     *
     * @param date 日期
     * @param minutes 分钟数，负数为减
     * @return 加/减几分钟后的日期
     */
    public static Date addDateMinutes(Date date, int minutes) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusMinutes(minutes).toDate();
    }

    /**
     * 对日期的【小时】进行加/减
     *
     * @param date 日期
     * @param hours 小时数，负数为减
     * @return 加/减几小时后的日期
     */
    public static Date addDateHours(Date date, int hours) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusHours(hours).toDate();
    }

    /**
     * 对日期的【天】进行加/减
     *
     * @param date 日期
     * @param days 天数，负数为减
     * @return 加/减几天后的日期
     */
    public static Date addDateDays(Date date, int days) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusDays(days).toDate();
    }

    /**
     * 对日期的【周】进行加/减
     *
     * @param date 日期
     * @param weeks 周数，负数为减
     * @return 加/减几周后的日期
     */
    public static Date addDateWeeks(Date date, int weeks) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusWeeks(weeks).toDate();
    }

    /**
     * 对日期的【月】进行加/减
     *
     * @param date 日期
     * @param months 月数，负数为减
     * @return 加/减几月后的日期
     */
    public static Date addDateMonths(Date date, int months) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusMonths(months).toDate();
    }

    /**
     * 对日期的【年】进行加/减
     *
     * @param date 日期
     * @param years 年数，负数为减
     * @return 加/减几年后的日期
     */
    public static Date addDateYears(Date date, int years) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusYears(years).toDate();
    }

    /**
     * 获得当天零时零分零秒
     *
     * @author: fbi-protal
     * @param
     * @return: java.util.Date
     * @Exception
     */
    public static Date initDateByDay(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }


    /**
     * 判断一年的第几周 周一为一周第一天
     *
     * @author: fbi-protal
     * @param dataTime
     * @return: java.lang.Integer
     * @Exception
     */
    public static Integer whatWeek(Date dataTime) throws java.text.ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse(format.format(dataTime));
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        return calendar.get(Calendar.WEEK_OF_YEAR);

    }

    /**
     * 字符串转换日期
     *
     * @author: fbi-protal
     * @param str
     * @return: java.util.Date
     * @Exception
     */
    public static Date getDate(String str){
        if(StringUtils.isBlank(str)){
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = null;
        try {
            parse = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse;
    }

    /**
     * 获取连续的时间结果集（天连续）
     *
     * @author: fbi-protal
     * @param startDate
     * @param endDate
     * @return: java.util.List<java.lang.String>
     * @Exception
     */
    public static List<String> getDateList(Date startDate, Date endDate) {
        // 查询的连续日期集合
        List<String> dateList = new ArrayList<>();
        // 日历对象
        Calendar addDay = Calendar.getInstance();
        // 组装连续日期集合
        while (startDate.compareTo(endDate) < 0 || startDate.compareTo(endDate) == 0) {
            dateList.add(format(startDate,DATE_DAY_PATTERN));
            addDay.setTime(startDate);
            // 加一天
            addDay.add(Calendar.DATE, 1);
            startDate = addDay.getTime();
        }
        return dateList;
    }

    /**
     * 获取连续的时间结果集（周连续）展示形式 年-周
     *
     * @author: fbi-protal
     * @param startDate
     * @param endDate
     * @return: java.util.List<java.lang.String>
     * @Exception
     */
    public static List<String> getWeekList(Date startDate, Date endDate) {
        // 查询的连续日期集合
        List<String> dateList = new ArrayList<>();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        java.time.LocalDate localDate1 = java.time.LocalDate.parse(df.format(startDate));
        java.time.LocalDate localDate2 = java.time.LocalDate.parse(df.format(endDate));
        TemporalField weekFields = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
        int startWeek = localDate1.get(weekFields);
        int endWeek = localDate2.get(weekFields);
        // 组装连续的周数集合
        while (startWeek <= endWeek) {
            // 当前周
            String yearAndWeek = getYearAndWeek(startDate);
            dateList.add(yearAndWeek);
            // 下一周
            startDate = addDateWeeks(startDate, 1);
            ++startWeek;
        }

        return dateList;
    }

    /**
     * 获取当前周
     *
     * @author: fbi-protal
     * @param date
     * @return: int
     * @Exception
     */
    public static int getweek(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        java.time.LocalDate localDate1 = java.time.LocalDate.parse(df.format(date));
        TemporalField weekFields = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
        return localDate1.get(weekFields);
    }

    /**
     * 获取连续的时间结果集（小时连续）
     *
     * @author: fbi-protal
     * @param startDate
     * @param endDate
     * @return: java.util.List<java.lang.String>
     * @Exception
     */
    public static List<String> getHourList(Date startDate, Date endDate) {
        List<String> dateList = new ArrayList<>();
        while (startDate.before(endDate)) {
            String format = format(startDate,DateUtils.DATE_HOUR_PATTERN);
            dateList.add(format);
            startDate = addDateHours(startDate, 1);
        }
        return dateList;
    }

    public static List<String> getHourTimeList(Date startDate, Date endDate) {
        List<String> dateList = new ArrayList<>();
        while (startDate.before(endDate)) {
            String format = format(startDate,DateUtils.DATE_TIME_PATTERN);
            dateList.add(format);
            startDate = addDateHours(startDate, 1);
        }
        return dateList;
    }

    /**
     * 获取24小时
     *
     * @author: fbi-protal
     * @param
     * @return: java.util.List<java.lang.String>
     * @Exception
     */
    public static List<String> getHourList() {
        List<String> dateList = new ArrayList<>();
        int hour = 0;
        while (hour < 24) {
            dateList.add(String.valueOf(hour));
            ++hour;
        }
        return dateList;
    }

    /**
     * 获取年-周
     *
     * @author: fbi-protal
     * @param date
     * @return: java.lang.String
     * @Exception
     */
    public static String getYearAndWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.setTime(date);
        return cal.get(Calendar.YEAR) + "-" + cal.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 日期格式化后返回日期
     *
     * @author: fbi-protal
     * @param date
     * @return: java.util.Date
     * @Exception
     */
    public static Date formatTwo(Date date) {
        return formatDate(date, DATE_PATTERN);
    }
    public static Date formatDate(Date date, String pattern) {
        if(date != null){
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            try {
                return df.parse(df.format(date));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    /**
     * 获取时间段周数差(跨年)
     *
     * @author: fbi-protal
     * @param startDate
     * @param endDate
     * @return: int
     * @Exception
     */
    public static int getWeekDifference(Date startDate, Date endDate) {
        java.time.LocalDate start = java.time.LocalDate.parse(format(startDate));
        java.time.LocalDate end = java.time.LocalDate.parse(format(endDate));
        TemporalField weekFields = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
        return end.get(weekFields) - start.get(weekFields);
    }

    public static List<String> getDateList(Date startDate, Date endDate, String format) {
        // 查询的连续日期集合
        List<String> dateList = new ArrayList<>();
        // 日历对象
        Calendar addDay = Calendar.getInstance();
        // 组装连续日期集合
        while (startDate.compareTo(endDate) < 0 || startDate.compareTo(endDate) == 0) {
            dateList.add(format(startDate,format));
            addDay.setTime(startDate);
            // 加一天
            addDay.add(Calendar.DATE, 1);
            startDate = addDay.getTime();
        }
        return dateList;
    }

    public static LocalDateTime parseString2LocalDateTime(String str){
        if(org.springframework.util.StringUtils.isEmpty(str)){
            return null;
        }
        str = str.replaceAll("/","-");
        str = str.replaceAll("T"," ");
        String[] dateTime = str.split(" ");
        String[] date = dateTime[0].split("-");
        String[] time = dateTime.length > 1 ? dateTime[1].split(":") : new String[0];
        int day = date.length >= 3 ? Integer.valueOf(date[2]) : 0;
        int month = date.length >= 2 ? Integer.valueOf(date[1]) : 0;
        int year = date.length >= 1 ? Integer.valueOf(date[0]) : 0;

        /* liwencheng 2019-08-23 修改开始 原因：发现特殊时间格式，需要截取second */
        String[] seconds = time.length >= 3 ? time[2].split("\\.") : new String[0];
        int second = seconds.length >= 1 ? Integer.valueOf(seconds[0]) : 0;
        /* liwencheng 2019-08-23 修改结束 */
        int minute = time.length >= 2 ? Integer.valueOf(time[1]) : 0;
        int hour = time.length >= 1 ? Integer.valueOf(time[0]) : 0;
        return LocalDateTime.of(year, month, day, hour, minute, second);
    }

    /**
     * 获取当天结束时间
     * @param str 结束时间
     * @return 年-月-日 23:59:00
     */
    public static LocalDateTime parseStringLocalDateTime(String str){
        if(org.springframework.util.StringUtils.isEmpty(str)){
            return null;
        }
        str = str.replaceAll("/","-");
        str = str.replaceAll("T"," ");
        String[] dateTime = str.split(" ");
        String[] date = dateTime[0].split("-");
        String[] time = dateTime.length > 1 ? dateTime[1].split(":") : new String[0];
        int day = date.length >= 3 ? Integer.valueOf(date[2]) : 0;
        int month = date.length >= 2 ? Integer.valueOf(date[1]) : 0;
        int year = date.length >= 1 ? Integer.valueOf(date[0]) : 0;

        String[] seconds = time.length >= 3 ? time[2].split("\\.") : new String[0];
        int second = seconds.length >= 1 ? Integer.valueOf(seconds[0]) : 0;
        int minute = time.length >= 2 ? Integer.valueOf(time[1]) : 59;
        int hour = time.length >= 1 ? Integer.valueOf(time[0]) : 23;
        return LocalDateTime.of(year, month, day, hour, minute, second);
    }

    public static String parseLocalDateTime2String(LocalDateTime localDateTime, String pattern){
        if(localDateTime == null){
            return "";
        }
        java.time.format.DateTimeFormatter dateTimeFormatter = java.time.format.DateTimeFormatter.ofPattern(pattern,Locale.US);
        return dateTimeFormatter.format(localDateTime);
    }

    public static String parseLocalDateTime2String(LocalDateTime localDateTime){
        return parseLocalDateTime2String(localDateTime, FORMAT_DATE_TIME);
    }

    public static Date parseLocalDateTimeToDate(LocalDateTime localDateTime, String pattern){
        if(localDateTime == null){
            return new Date();
        }
        java.time.format.DateTimeFormatter dateTimeFormatter = java.time.format.DateTimeFormatter.ofPattern(pattern);
        return DateUtils.stringToDate(dateTimeFormatter.format(localDateTime),pattern);
    }

    public static Date parseLocalDateTimeToDateTime(LocalDateTime localDateTime, String pattern){
        if(localDateTime == null){
            return new Date();
        }
        java.time.format.DateTimeFormatter dateTimeFormatter = java.time.format.DateTimeFormatter.ofPattern(pattern);
        return DateUtils.stringToDate(dateTimeFormatter.format(localDateTime),pattern);
    }
}
