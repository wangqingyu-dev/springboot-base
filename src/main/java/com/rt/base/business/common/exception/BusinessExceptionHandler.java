package com.rt.base.business.common.exception;

import com.rt.base.business.common.Constants;
import com.rt.base.business.common.em.ResultCode;
import com.rt.base.business.common.ResultInfo;
import com.rt.base.business.common.utils.MessageUtils;
import com.rt.base.business.common.utils.RequestUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Locale;

/**
 * 异常处理器
 *
 * @author Mark initPlatform
 */
@RestControllerAdvice(basePackages = "com.rt.base.business.controller")
public class BusinessExceptionHandler implements ResponseBodyAdvice<Object> {
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 拦截自定义的异常
	 */
	@ExceptionHandler(BusinessException.class)
	public ResultInfo handleAppException(BusinessException e) {

		return ResultInfo.build(ResultCode.ERROR.getCode(), ResultCode.NOT_FOUND.getName(), getLocaleMessage(e.getCode(), e.getParams()),null,null);
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResultInfo handlerNoFoundException(Exception e) {
		// 404
		return ResultInfo.build(ResultCode.NOT_FOUND.getCode(), ResultCode.NOT_FOUND.getName(),getLocaleMessage(ResultCode.NOT_FOUND.getName(), null),null,null);
	}

	@ExceptionHandler(DuplicateKeyException.class)
	public ResultInfo handleDuplicateKeyException(DuplicateKeyException e){
		return ResultInfo.build(ResultCode.UNAUTHORIZED.getCode(), ResultCode.UNAUTHORIZED.getName(), getLocaleMessage(ResultCode.UNAUTHORIZED.getName(), null),null,null);
	}

	@ExceptionHandler(AuthorizationException.class)
	public ResultInfo handleAuthorizationException(AuthorizationException e){
		return ResultInfo.build(ResultCode.DATA_EXIST.getCode(), ResultCode.DATA_EXIST.getName(), getLocaleMessage(ResultCode.DATA_EXIST.getName(), null),null,null);
	}
	/**
	 * 处理其他抛出的异常
	 */
	@ExceptionHandler(Exception.class)
	public ResultInfo handleException(Exception e) {
		logger.error(e.getMessage(), e);
		return ResultInfo.build(ResultCode.ERROR.getCode(), ResultCode.ERROR.getName(), getLocaleMessage(ResultCode.ERROR.getName(), null),null,null);
	}

	/**
	 * 获取国际化异常信息
	 */
	private String getLocaleMessage(String code, String ... params) {
		String languageCode = RequestUtil.getHeaderByKey(Constants.HeaderKey.LANGUAGE_CODE);
		if (StringUtils.isNotEmpty(languageCode)) {
			return MessageUtils.getMessage(code,languageCode,"",params);
		} else {
			return MessageUtils.getMessage(code,Locale.CHINESE,params);
		}
	}

	@Override
	public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
		return true;
	}

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
								  Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response)  {
		ResultInfo<Object> resultInfo = null;
		if (body instanceof ResultInfo) {
			resultInfo = (ResultInfo)body;
			resultInfo.setResultCode(((ResultInfo) body).getResultCode());
			if (org.apache.commons.lang.StringUtils.isNotEmpty(((ResultInfo) body).getMessageCode())) {
				resultInfo.setResultMessage(getLocaleMessage(((ResultInfo) body).getMessageCode(), null));
			} else {
				resultInfo.setResultMessage(getLocaleMessage(ResultCode.SUCCESS.getName(), null));
			}
			return resultInfo;
		} else {
			resultInfo = new ResultInfo<Object>();
			resultInfo.setResultCode(ResultCode.SUCCESS.getCode());
			resultInfo.setResultMessage(getLocaleMessage(ResultCode.SUCCESS.getName(), null));
			resultInfo.setBody(body);
		}
		return resultInfo;
	}
}
