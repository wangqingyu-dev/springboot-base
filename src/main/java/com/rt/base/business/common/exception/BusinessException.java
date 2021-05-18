package com.rt.base.business.common.exception;

import lombok.Getter;

/**
 * 自定义异常
 *
 * @author Mark initPlatform
 */
@Getter
public class BusinessException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private String code;
	private String[] params;
	/**
	 * 支持自定义错误码和提示信息的异常
	 *
	 * @param code 错误码
	 */
	public BusinessException(String code, String... params) {
		super(code);
		this.code = code;
		this.params = params;
	}


	/**
	 * 支持自定义错误码和提示信息的异常
	 *
	 * @param code 错误码
	 */
	public BusinessException(String code) {
		super(code);
		this.code = code;
	}
	
}
