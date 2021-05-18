package com.rt.base.mytest.util;

import com.rt.base.mytest.util.error.BizException;
import com.rt.base.mytest.util.error.JsonReturnCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @author liW
 * @version 1.0
 * @date 2019-7-1 10:25
 */
@Slf4j
public class NumUtil {

    // 零
    public static final BigDecimal ZERO = BigDecimal.ZERO;
    // 一
    public static final BigDecimal ONE = BigDecimal.ONE;
    // 十
    public static final BigDecimal TEN = BigDecimal.TEN;
    // 百
    public static final BigDecimal HUNDRED = new BigDecimal("100");


    public static final String FORMAT_LEN2_0 = "00";


    /**
     * 格式化整数数字（如1格式化为0001）
     *
     * @param value     需要格式化的数字
     * @param numFormat 格式化之后的格式
     */
    public static String numFormat(int value, String numFormat) {
        if (!ObjectUtils.isEmpty(value) && !ObjectUtils.isEmpty(numFormat)) {
            return new DecimalFormat(numFormat).format(value);
        }
        return null;
    }


    /**
     * 格式化整数数字（如1格式化为0001）
     *
     * @param value     需要格式化的数字
     * @param newScale 格式化之后的格式
     */
    public static BigDecimal decFormat(BigDecimal value, int newScale) {
        if (!ObjectUtils.isEmpty(value) && !ObjectUtils.isEmpty(newScale)) {
            return value.setScale(newScale, BigDecimal.ROUND_HALF_UP);
        }
        return null;
    }


    /**
     * 格式化整数数字（如1格式化为0001）
     *
     * @param value     需要格式化的数字
     * @param newScale 格式化之后的格式
     */
    public static String dec2Str(BigDecimal value, int newScale) {
        if (!ObjectUtils.isEmpty(value) && !ObjectUtils.isEmpty(newScale)) {
            return value.setScale(newScale, BigDecimal.ROUND_HALF_UP).toString();
        }
        return "";
    }

    /**
     * 格式化数字
     *
     * @param bd    需要格式化的数字
     * @param scale 格式化的精度
     */
    public static String numFormat(Object bd, int scale) {
        if (!ObjectUtils.isEmpty(bd) && !ObjectUtils.isEmpty(scale) && scale >= 0) {
            String scaleStr = "";
            for (int i = 0; i < scale; i++) {
                scaleStr = scaleStr + "0";
            }
            if (!"".equals(scaleStr)) {
                scaleStr = "." + scaleStr;
            }
            return new DecimalFormat("#,##0" + scaleStr).format(bd);
        }
        return null;
    }

    /**
     * 字符串转换位BigDecimal
     *
     * @param value 要转换的值
     * @return
     */
    public static BigDecimal strToDec(String value) {
        if (ObjectUtils.isEmpty(value) || ObjectUtils.isEmpty(value.trim())) {
            return BigDecimal.ZERO;
        } else {
            try {
                return new BigDecimal(value);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                log.error(BizException.ERROR_MSG, e);
                throw new BizException(JsonReturnCode.TO_NUM_ERROR_PARAM.addParam("msg", value));
            }
        }
    }

    /**
     * null 转换为Bigdecimal中的0
     *
     * @param value 要转换的值
     * @return
     */
    public static BigDecimal nullToZero(BigDecimal value) {
        if (value == null) {
            return BigDecimal.ZERO;
        } else {
            return value;
        }
    }

//    /**
//     * 格式化数字
//     *
//     * @param num    数字
//     * @param format 格式
//     * @return 格式化之后的字符串
//     */
//    public static String numFormat(int num, String format) {
//        return new DecimalFormat(format).format(num);
//    }

    /**
     * 格式化数字
     *
     * @param numStr 数字--字符串形式
     * @param format 格式
     * @return 格式化之后的字符串
     */
    public static String numFormat(String numStr, String format) {
        int num = Integer.parseInt(numStr);
        return new DecimalFormat(format).format(num);
    }


}
