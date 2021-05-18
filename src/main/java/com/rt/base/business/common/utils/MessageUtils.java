package com.rt.base.business.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.MessageFormat;
import java.util.Locale;

/**
 * @author wangq
 */
@Component
public class MessageUtils {

    private static MessageUtils msgUtils;

    @Autowired
    private MessageSource messageSource;

    /**
     * 初始化MessageUtils
     * ------- PostConstruct注解相当于静态代码库,方法会在类初始化的时候 进行执行
     */
    @PostConstruct
    public void init() {
        try {
            msgUtils = this;
            msgUtils.messageSource = this.messageSource;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 获取国际化信息
     */
    public static String getMessage(String code, String ... params) {
        return getMessage(code,Locale.ENGLISH,params);
    }

    /**
     * 获取国际化信息
     */
    public static String getMessage(String code,String language,String country, String ... params) {
        // 国际化翻译
        Locale locale = new Locale(language,country);
        return getMessage(code,locale,params);
    }

    /**
     * 获取国际化信息
     */
    public static String getMessage(String code,Locale locale, String ... params) {
        // 国际化翻译
        String pattern = msgUtils.messageSource.getMessage(code, null, null, locale);
        if (StringUtils.isNotEmpty(pattern)) {
            return MessageFormat.format(pattern, params);
        } else {
            return "";
        }
    }
}
