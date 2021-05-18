package com.rt.base.business.common.annotation;

import java.lang.annotation.*;

/**
 * 系统日志注解
 *
 * @author Mark initPlatform
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {

	String value() default "";

    /**
     * 日志类型 1:系统基础日志 2:重算任务管理 3:参数配置 4:码表手工同步
     */
	int logType() default 1;

}
