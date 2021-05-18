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
public @interface SysParamLog {

	String value() default "";
}
