package com.rt.base.business.common.utils;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wangq
 */
public class RequestUtil {

    public  static String getHeaderByKey(String key) {
        // 从 http 请求头中取出 token
        String result = "";
        if (StringUtils.isNotEmpty(key)) {
            result = getRequest().getHeader(key);
        }
        return result;
    }
    /**
     * 获取request
     *
     * @return
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        return requestAttributes == null ? null : requestAttributes.getRequest();
    }
}
