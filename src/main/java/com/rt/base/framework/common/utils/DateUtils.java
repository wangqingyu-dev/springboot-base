package com.rt.base.framework.common.utils;

import java.time.LocalDateTime;
import java.util.*;

/**
 * 日期处理
 *
 * @author Mark initPlatform
 */
public class DateUtils {

    /** yyyy/MM/dd HH:mm:ss */
    public static final String FORMAT_DATE_TIME = "yyyy/MM/dd HH:mm:ss";

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

}
