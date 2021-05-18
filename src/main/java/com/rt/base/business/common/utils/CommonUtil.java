package com.rt.base.business.common.utils;

import java.security.MessageDigest;
import java.util.UUID;

public class CommonUtil {

    public static String byteToHexStringForMD5(byte[] MD5Bytes) {
        char[] hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        char[] str = new char[32];
        int k = 0;

        for(int i = 0; i < 16; ++i) {
            byte byte0 = MD5Bytes[i];
            str[k++] = hexDigits[byte0 >>> 4 & 15];
            str[k++] = hexDigits[byte0 & 15];
        }

        String s = new String(str);
        return s;
    }

    public static final String encyptPassword(String originalPsw, String DEFAULT_PASSWORD) {
        if (originalPsw == null) {
            return null;
        } else {
            if (!DEFAULT_PASSWORD.equals(originalPsw)) {
                try {
                    return byteToHexStringForMD5(MessageDigest.getInstance("MD5").digest(originalPsw.getBytes()));
                } catch (Exception var3) {
                    var3.printStackTrace();
                }
            }

            return originalPsw;
        }
    }

    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
