/** 
 * @(#)DictionaryController.java 1.0.0 2016年11月3日 下午4:09:08  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */

package com.slfinance.redpack.core.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.slfinance.redpack.core.constants.enums.RedpackType;
import com.slfinance.redpack.core.controller.base.BaseController;
import com.slfinance.redpack.core.entities.Region;
import com.slfinance.redpack.core.extend.fastjson.Serialize;
import com.slfinance.redpack.core.extend.fastjson.SerializeRule;
import com.slfinance.redpack.core.response.ResponseVo;
import com.slfinance.redpack.core.services.RegionService;

/**
 * 
 * 
 * @author SangLy
 * @version $Revision:1.0.0, $Date: 2016年11月3日 下午4:09:08 $
 */
@RestController
@RequestMapping(value = "dic", method = RequestMethod.POST)
public class DictionaryController extends BaseController {
	
	@Autowired
	private RegionService regionService;

	/**
	 * app-获取省、市下拉框
	 * 
	 * @author SangLy
	 * @createTime 2016年11月3日16:32:33
	 * @return
	 */
	@RequestMapping("location")
	@Serialize({ @SerializeRule(clazz = Region.class, include = { "code", "name"}) })
	public ResponseVo appGetShibboleth(@RequestBody Map<String, Object> params) {
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("data", regionService.findRegionListByParentCode(MapUtils.getString(params, "parentCode")));
		return ResponseVo.success(result);
	}
	
	/**
	 * app-获取数据字典下拉框数据
	 * 
	 * @author SangLy
	 * @createTime 2016年11月3日16:32:33
	 * @return
	 */
	@RequestMapping("select")
	public ResponseVo appGetSelect(@RequestBody Map<String, Object> params) {
		Map<String, Object> result = new HashMap<String,Object>();
		result.put("value", RedpackType.土豪红包.toString());
		result.put("text", RedpackType.经济红包.toString());
		return ResponseVo.success(result);
	}

}
