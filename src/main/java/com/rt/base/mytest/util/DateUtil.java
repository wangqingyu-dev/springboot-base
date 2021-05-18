package com.rt.base.mytest.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * 工具类
 */
@Slf4j
public class DateUtil {
    //格式化日期

    // 列"2018"  length = 4
    public static final String DATE_Y = "yyyy";
    private static final String FORMAT_Y = "[0-9]{4}";

    // 列"201806"  length = 6
    public static final String DATE_YM = "yyyyMM";
    private static final String FORMAT_YM = "[0-9]{6}";

    // 列"2018-06"  length = 7
    public static final String DATE_YM_ = "yyyy-MM";
    private static final String FORMAT_YM_ = "[0-9]{4}-[0-9]{2}";

    // 列"20180602"  length = 8
    public static final String DATE_YMD = "yyyyMMdd";
    private static final String FORMAT_YMD = "[0-9]{8}";

    // 列"20180602212023"  length = 14
    public static final String DATE_YMDHMS = "yyyyMMddHHmmss";
    private static final String FORMAT_YMDHMS = "[0-9]{14}";

    // 列"2018-06-02"  length = 10
    public static final String DATE_YMD_ = "yyyy-MM-dd";
    private static final String FORMAT_YMD_ = "[0-9]{4}-[0-9]{2}-[0-9]{2}";

    // 列"2018-06-02 21:03:23"  length = 19
    public static final String DATE_YMDHMS_ = "yyyy-MM-dd HH:mm:ss";
    private static final String FORMAT_YMDHMS_ = "[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}";

    // 列"2018-06-02 21:03:23.1"  length = 21
    public static final String DATE_YMDHMSS1_ = "yyyy-MM-dd HH:mm:ss.s";
    private static final String FORMAT_YMDHMSS1_ = "[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}.[0-9]{1}";

    // 列"2018-06-02 21:03:23.123"  length = 23
    public static final String DATE_YMDHMSS3_ = "yyyy-MM-dd HH:mm:ss.SSS";
    private static final String FORMAT_YMDHMSS3_ = "[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}.[0-9]{3}";

    // 列"2018-06-02T21:03:23"  length = 19
    public static final String DATE_YMDHMS_T = "yyyy-MM-dd'T'HH:mm:ss";
    private static final String FORMAT_YMDHMS_T = "[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}";

    // 格林威治时间转回Date类型: 加时区
    public static final String DATE_CST = "EEE MMM dd HH:mm:ss z yyyy";

    // 带有空格，（单机窗口下载的数据中的日期的格式）
    public static final String DATE_YMDHMS_N = "yyyy - MM - dd hh: mm: ss";


    /**
     * 获取SimpleDateFormat（格式默认yyyy-MM-dd）
     *
     * @return SimpleDateFormat
     */
    public static SimpleDateFormat getSDF() {
        return getSDF(DATE_YMD_);
    }

    /**
     * 获取SimpleDateFormat
     *
     * @param dateFormat 需要格式化的格式
     * @return SimpleDateFormat
     */
    public static SimpleDateFormat getSDF(String dateFormat) {
        return new SimpleDateFormat(dateFormat);
    }

    /**
     * 日期-> 字符串（格式默认yyyy-MM-dd）
     *
     * @param date 日期
     * @return 字符串
     */
    public static String date2Str(Date date) {
        return date2Str(date, DATE_YMD_);
    }

    /**
     * 日期-> 字符串
     *
     * @param date       日期
     * @param dateFormat 格式
     * @return 字符串
     */
    public static String date2Str(Date date, String dateFormat) {
        String str = null;
        if (!ObjectUtils.isEmpty(date)) {
            SimpleDateFormat sdf = getSDF(dateFormat);
            str = sdf.format(date);
        }
        return str;
    }

    /**
     * 字符串-> 日期
     *
     * @param sdf            SimpleDateFormat
     * @param strDate        字符串
     * @param throwException 当转换错误时，是否抛异常
     * @return 日期
     */
    public static Date str2Date(SimpleDateFormat sdf, String strDate, boolean throwException) {
        try {
            return sdf.parse(strDate);
        } catch (ParseException e) {
            log.error(e.getMessage());
            e.printStackTrace();
            if (throwException) {
                throw new RuntimeException();
            } else {
                return null;
            }
        }
    }

    /**
     * 字符串-> 日期--当转换错误时，抛异常
     *
     * @param sdf     SimpleDateFormat
     * @param strDate 字符串
     * @return 日期
     */
    public static Date str2Date(SimpleDateFormat sdf, String strDate) {
        return str2Date(sdf, strDate, true);
    }

    /**
     * 字符串-> 日期--当转换错误时，抛异常
     *
     * @param formatStr formatStr
     * @param strDate   字符串
     * @return 日期
     */
    public static Date str2Date(String formatStr, String strDate) {
        return str2Date(getSDF(formatStr), strDate, true);
    }

    /**
     * 字符串-> 日期--当转换错误时，抛异常 (格式：yyyy-MM-dd)
     *
     * @param strDate 字符串
     * @return 日期
     */
    public static Date str2Date(String strDate) {
        return str2Date(getSDF(DATE_YMD_), strDate, true);
    }

    /**
     * 字符串-> 日期--当转换错误时，抛异常 (格式任意，每个都重新获取格式)
     *
     * @param strDate 字符串
     * @return 日期
     */
    public static Date auto2Date(String strDate) {
        String formatStr = getFormatStr(strDate);
        return str2Date(getSDF(formatStr), strDate, true);
    }


    /**
     * 字符串-> 日期
     *
     * @param strDate
     * @return
     */
    public static Date parseString(String strDate) {

        Date date = null;
        if (StringUtils.isEmpty(strDate)) {
            date = new Date(0L);
        } else {
            SimpleDateFormat simpleDateFormat = null;
            // 正则表达式判断满足某个格式
            if (Pattern.compile(FORMAT_YMDHMSS1_).matcher(strDate).matches()) {
                simpleDateFormat = new SimpleDateFormat(DATE_YMDHMSS1_);
            } else if (Pattern.compile(FORMAT_YMD_).matcher(strDate).matches()) {
                simpleDateFormat = new SimpleDateFormat(DATE_YMD_);
            } else if (Pattern.compile(FORMAT_YMDHMS_T).matcher(strDate).matches()) {
                simpleDateFormat = new SimpleDateFormat(DATE_YMDHMS_T);
            } else if (Pattern.compile(FORMAT_YMDHMS_).matcher(strDate).matches()) {
                simpleDateFormat = new SimpleDateFormat(DATE_YMDHMS_);
            } else if (Pattern.compile(FORMAT_YMD).matcher(strDate).matches()) {
                simpleDateFormat = new SimpleDateFormat(DATE_YMD);
            } else {
                // TODO 判断格式
            }
            if (ObjectUtils.isEmpty(simpleDateFormat)) {
                throw new RuntimeException();
            }
            try {
                date = simpleDateFormat.parse(strDate);
            } catch (ParseException e) {
                throw new RuntimeException();
            }
        }
        return date;
    }

    /**
     * 根据传入的字符串的长度，获取相应的格式，一定情况下可用。
     *
     * @param dateStr
     * @return
     */
    public static String getFormatStr(String dateStr) {
        String formatStr;
        switch (dateStr.length()) {
            case 4:
                formatStr = DateUtil.DATE_Y;
                break;
            case 6:
                formatStr = DateUtil.DATE_YM;
                break;
            case 8:
                formatStr = DateUtil.DATE_YMD;
                break;
            case 14:
                formatStr = DateUtil.DATE_YMDHMS;
                break;
            case 10:
                formatStr = DateUtil.DATE_YMD_;
                break;
            case 19:
                if (dateStr.contains("T")) {
                    formatStr = DateUtil.DATE_YMDHMS_T;
                    break;
                }
                formatStr = DateUtil.DATE_YMDHMS_;
                break;
            case 21:
                formatStr = DateUtil.DATE_YMDHMSS1_;
                break;
            case 23:
                formatStr = DateUtil.DATE_YMDHMSS3_;
                break;
            case 25:
                formatStr = DateUtil.DATE_YMDHMS_N;
                break;
            default:
                throw new RuntimeException();
        }
        return formatStr;
    }


    /**
     * 获取下个月
     *
     * @param date 某日期
     * @return 下个月
     */
    public static Date getNextMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, 1);
        return cal.getTime();
    }

    /**
     * 获取下个月
     *
     * @param date 某日期
     * @return 下个月
     */
    public static Date getAddDate(Date date, int param) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, param);
        return cal.getTime();
    }

    /**
     * 获取某个月的第一个工作日
     *
     * @param date 某日期
     * @return 第一个工作日
     */
    public static Date getFirstWorkDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        int weekDay = cal.get(Calendar.DAY_OF_WEEK);
        if (weekDay == Calendar.SUNDAY) {
            cal.add(Calendar.DAY_OF_MONTH, 1);
        } else if (weekDay == Calendar.SATURDAY) {
            cal.add(Calendar.DAY_OF_MONTH, 2);
        }
        return cal.getTime();
    }

    public static String formatFullTime(LocalDateTime localDateTime, String pattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return localDateTime.format(dateTimeFormatter);
    }

}
