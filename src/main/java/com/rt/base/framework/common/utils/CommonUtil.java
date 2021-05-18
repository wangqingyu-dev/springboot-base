package com.rt.base.framework.common.utils;

import java.util.UUID;

/**
 * @author wangq
 */
public class CommonUtil {

    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
