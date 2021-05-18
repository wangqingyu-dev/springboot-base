package com.rt.base.business.form.demo;

import lombok.Data;

/**
 * @author wangq
 */
@Data
public class DemoForm {

    /**
     * 入参实例
     */
    private String param;

    private String redisKey;

    private String redisValue;
}
