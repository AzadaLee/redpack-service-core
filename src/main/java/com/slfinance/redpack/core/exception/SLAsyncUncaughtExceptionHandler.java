package com.slfinance.redpack.core.exception;

/** 
 * @(#)SLAsyncUncaughtExceptionHandler.java 1.0.0 2016年5月25日 下午2:28:37  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */ 


import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

/**   
 * 异步方法异常的处理
 * @author  LiYing
 * @version $Revision:1.0.0, $Date: 2016年5月25日 下午2:28:37 $ 
 */
public class SLAsyncUncaughtExceptionHandler implements AsyncUncaughtExceptionHandler {

	private final Log logger = LogFactory.getLog(SLAsyncUncaughtExceptionHandler.class);

	@Override
	public void handleUncaughtException(Throwable ex, Method method, Object... params) {
		if (logger.isErrorEnabled()) {
			logger.error(String.format("Unexpected error occurred invoking async " + "method '%s'.", method), ex);
		}
	}
}
