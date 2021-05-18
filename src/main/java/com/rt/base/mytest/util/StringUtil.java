package com.rt.base.mytest.util;

public class StringUtil {

    public static String stripEnd(String str, String stripChars) {
        if (str == null) {
            return null;
        }
        int end = str.length();
        if (end == 0) {
            return str;
        }
        if (stripChars == null) {
            while ((end != 0) && Character.isWhitespace(str.charAt(end - 1))) {
                end--;
            }
        } else if (stripChars.length() == 0) {
            return str;
        } else {
            while ((end != 0) && (stripChars.indexOf(str.charAt(end - 1)) != -1)) {
                end--;
            }
        }
        return str.substring(0, end);
    }
}
