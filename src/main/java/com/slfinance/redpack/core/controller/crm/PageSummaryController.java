/** 
 * @(#)PageSummaryController.java 1.0.0 2016年8月16日 下午8:21:10  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */

package com.slfinance.redpack.core.controller.crm;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.slfinance.redpack.core.controller.base.BaseController;
import com.slfinance.redpack.core.response.ResponseVo;
import com.slfinance.redpack.core.services.AdvertisementService;

/**
 * 登录首页数据controller
 * 
 * @author taoxm
 * @version $Revision:1.0.0, $Date: 2016年8月16日 下午8:21:10 $
 */
@RestController
@RequestMapping(value = "/crm", method = RequestMethod.POST)
public class PageSummaryController extends BaseController {

	@Autowired
	private AdvertisementService advertisementService;

	/**
	 * 登录首页统计数据
	 * 
	 * @author taoxm
	 * @createTime 2016年8月17日 上午10:13:48
	 * @return
	 */
	@RequestMapping("/indexPageSummary")
	public ResponseVo indexPageSummary() {
		return ResponseVo.success(advertisementService.indexPageSummary());
	}
	
	/**
	 * 获取静态资源访问域名
	 * @return
	 */
	@RequestMapping("/getResourceDomain")
	public ResponseVo getResourceDomain(){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("resourceDomain", staticResourceProxyURI);
		return ResponseVo.success(result);
	}
}
