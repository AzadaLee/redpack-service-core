/**
 * @(#)ResponseVoReturnValueAspect.java 1.0.0 2015年12月14日 下午2:13:59
 * <p/>
 * Copyright © 2015 善林金融.  All rights reserved.
 */

package com.slfinance.redpack.core.extend.springweb.method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import com.slfinance.redpack.core.response.ResponseCode;
import com.slfinance.redpack.core.response.ResponseVo;

/**
 * 关于responseVo返回值添加数据拦截器
 *
 * @author samson
 * @version $Revision:1.0.0, $Date: 2015年12月14日 下午2:13:59 $
 */
@Aspect
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class ResponseVoReturnValueAspect {
	/**
	 * 配置前置通知
	 *
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */
	@Around("com.slfinance.redpack.core.SystemArchitecture.controller()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		Object object = joinPoint.proceed();
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		if (ResponseVo.class.isAssignableFrom(methodSignature.getReturnType())) {
			if (object == null) {
				return ResponseVo.fail(ResponseCode.SERVER_ERROR, "");
			} else {
				ResponseCode.defaultMessage((ResponseVo) object);

			}
		}
		return object;
	}
}
