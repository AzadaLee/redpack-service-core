/** 
 * @(#)CustomerController.java 1.0.0 2016年8月15日 下午1:51:19  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */

package com.slfinance.redpack.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.slfinance.redpack.core.constants.enums.Platform;
import com.slfinance.redpack.core.controller.base.BaseController;
import com.slfinance.redpack.core.entities.AppVersion;
import com.slfinance.redpack.core.exception.SLException;
import com.slfinance.redpack.core.extend.fastjson.Serialize;
import com.slfinance.redpack.core.extend.fastjson.SerializeRule;
import com.slfinance.redpack.core.response.ResponseVo;
import com.slfinance.redpack.core.services.AppVersionService;

/**
 * 
 * 
 * @author SangLy
 * @version $Revision:1.0.0, $Date: 2016年8月15日 下午1:51:19 $
 */
@RestController
@RequestMapping(value = "appVersion", method = RequestMethod.POST)
public class AppVersinController extends BaseController {

	@Autowired
	private AppVersionService appVersionService;


	/**
	 * app-获取当前服务器最新的版本号
	 * @author SangLy
	 * @createTime 2016年8月19日 下午1:30:13
	 * @return
	 * @throws SLException
	 */
	@RequestMapping("getCurrentVersion")
	@Serialize({ @SerializeRule(clazz = AppVersion.class, include = { "appVersion", "updateContent", "downloadURL" }) })
	public ResponseVo appGetCurrentVersion(@RequestHeader(value = "pf", required = false) Platform platform) throws SLException {
		platform = platform == Platform.windows ? Platform.android : platform;// 主要用于web获取android的下载地址
		return ResponseVo.success(appVersionService.appGetCurrentVersion(platform));
	}

}
