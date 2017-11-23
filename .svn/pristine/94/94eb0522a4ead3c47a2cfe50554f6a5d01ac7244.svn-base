/** 
 * @(#)ExceptionControllerAdvice.java 1.0.0 2016年4月6日 下午8:15:48  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */

package com.slfinance.redpack.core.controller.base;

import java.lang.reflect.UndeclaredThrowableException;

import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.slfinance.redpack.core.exception.SLException;
import com.slfinance.redpack.core.extend.validate.ValidateRuntimeException;
import com.slfinance.redpack.core.response.ResponseCode;
import com.slfinance.redpack.core.response.ResponseVo;

/**
 * controller 的advice
 * 
 * @author samson
 * @version $Revision:1.0.0, $Date: 2016年4月6日 下午8:15:48 $
 */
@ControllerAdvice
public class BaseControllerAdvice extends BaseController {

	/**
	 * controller 层异常统一处理
	 *
	 * @param e
	 * @return
	 */
	@ExceptionHandler
	@ResponseBody
	public ResponseVo exceptionHandler(Throwable e) {
		e = e instanceof UndeclaredThrowableException ? e.getCause() : e;// 代理异常处理
		if (e instanceof SLException) {
			SLException slException = (SLException) e;
			String code = slException.getCode();
			String codeMessage = ResponseCode.getExceptionMessage(code);
			logger.debug("异常:::code:{},message:{},codeMessage:{}", slException.getCode(), slException.getMessage(), codeMessage);
			return ResponseVo.fail(code, codeMessage);
		}

		if (e instanceof BindException) {// 数据邦定校验异常
			BindException bindException = (BindException) e;
			FieldError fieldError = bindException.getBindingResult().getFieldError();// 获取第一条错误信息
			logger.debug("SPRING MVC 数据邦定校验失败;{}");
			return ResponseVo.fail(ResponseCode.PARAMETERS_VALIDATE_FAILURE, fieldError.getField() + fieldError.getDefaultMessage());
		}

		if (e instanceof ValidateRuntimeException) {
			logger.debug("ValidateRuntimeException.............");
			return ResponseVo.fail(ResponseCode.PARAMETERS_VALIDATE_FAILURE, e.getMessage());
		}
		if (e instanceof MethodArgumentNotValidException) {
			String code = ((MethodArgumentNotValidException) e).getBindingResult().getAllErrors().get(0).getDefaultMessage();
			String codeMessage = ResponseCode.getExceptionMessage(code);
			logger.debug("异常:::code:{},message:{}", code, codeMessage);
			return ResponseVo.fail(code, codeMessage);
		}

		logger.info("异常信息:", e);
		return ResponseVo.fail(ResponseCode.SERVER_ERROR, " 其他非正常异常");
	}
}
