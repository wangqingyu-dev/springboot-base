package com.rt.base.mytest.DTOoperation;

import java.math.BigDecimal;

/**
 * @author wqy
 */
public class StringTest {
    public static void main(String[] args) {
        BigDecimal divide = new BigDecimal("2").divide(new BigDecimal("3"),2,BigDecimal.ROUND_HALF_UP);
        System.out.println(divide);
    }
}
