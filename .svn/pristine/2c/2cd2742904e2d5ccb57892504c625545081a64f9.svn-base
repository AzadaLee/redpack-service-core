/**
 * Copyright (c) 2005-2012 https://github.com/zhangkaitao
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.slfinance.redpack.core.controller.base;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.slfinance.redpack.core.extend.springweb.service.LoginUserContextHolder;
import com.slfinance.redpack.core.vo.LoginUser;

/**
 * 基础控制器
 * <p/>
 * User: Zhang Kaitao
 * <p/>
 * Date: 13-2-23 下午3:56
 * <p/>
 * Version: 1.0
 */
public abstract class BaseController {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * 静态资源代理
	 */
	@Value("${staticResourceProxyURI}")
	protected String staticResourceProxyURI;
	
	/**
	 * 域名
	 */
	@Value("${domainName}")
	protected String domainName;
	

	/**
	 * 初始化参数Map
	 *
	 * @return
	 */
	protected Map<String, Object> initParmas() {
		return new HashMap<String, Object>();
	}

	/**
	 * 获取当前登录用户信息
	 * 
	 * @author samson
	 * @createTime 2016年8月15日 下午3:02:59
	 * @return
	 */
	protected LoginUser getLoginUser() {
		return LoginUserContextHolder.getLoginUser();
	}

	/**
	 * 获取当前登录用户id
	 * 
	 * @author samson
	 * @createTime 2016年8月15日 下午3:03:51
	 * @return
	 */
	protected String getLoginUserId() {
		LoginUser loginUser = LoginUserContextHolder.getLoginUser();
		return loginUser == null ? null : loginUser.getId();
	}

}
