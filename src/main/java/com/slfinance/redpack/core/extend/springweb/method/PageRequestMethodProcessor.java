/** 
 * @(#)PageRequestMethodProcessor.java 1.0.0 2016年3月30日 上午11:33:16  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */

package com.slfinance.redpack.core.extend.springweb.method;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.slfinance.redpack.core.extend.springweb.service.LoginUserContextHolder;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import com.alibaba.fastjson.JSON;
import com.slfinance.redpack.core.extend.jpa.page.PageRequestVo;

/**
 * 
 * 
 * @author LiYing
 * @version $Revision:1.0.0, $Date: 2016年3月30日 上午11:33:16 $
 */
public class PageRequestMethodProcessor extends RequestResponseBodyMethodProcessor {

	private static final String START = "start";
	private static final String LENGTH = "length";

	public PageRequestMethodProcessor(List<HttpMessageConverter<?>> converters) {
		super(converters);
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return PageRequestVo.class.isAssignableFrom(parameter.getParameterType());
	}

	@Override
	protected <T> Object readWithMessageConverters(NativeWebRequest webRequest, MethodParameter methodParam, Type paramType) throws IOException, HttpMediaTypeNotSupportedException, HttpMessageNotReadableException {

		HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
		ServletServerHttpRequest inputMessage = new ServletServerHttpRequest(servletRequest);
		Map<String, Object> params = JSON.parseObject(IOUtils.toString(inputMessage.getBody()));
		return buildResult(params);
	}

	private PageRequestVo buildResult(Map<String, Object> params) {

		PageRequestVo pageRequestVo = new PageRequestVo();
		pageRequestVo.setLength(MapUtils.getIntValue(params, LENGTH, 10));
		pageRequestVo.setStart(MapUtils.getIntValue(params, START, 0));

		params.remove(START);
		params.remove(LENGTH);
		pageRequestVo.setParams(params);
		pageRequestVo.setLoginUser(LoginUserContextHolder.getLoginUser());
		return pageRequestVo;
	}
}
