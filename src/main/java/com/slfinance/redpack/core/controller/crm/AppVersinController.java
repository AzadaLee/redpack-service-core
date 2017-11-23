/** 
 * @(#)AppVersinController.java 1.0.0 2016年8月17日 上午10:19:15  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */

package com.slfinance.redpack.core.controller.crm;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.slfinance.redpack.common.utils.MapUtil;
import com.slfinance.redpack.core.constants.enums.Platform;
import com.slfinance.redpack.core.controller.base.BaseController;
import com.slfinance.redpack.core.entities.AppVersion;
import com.slfinance.redpack.core.exception.SLException;
import com.slfinance.redpack.core.extend.fastjson.Serialize;
import com.slfinance.redpack.core.extend.fastjson.SerializeRule;
import com.slfinance.redpack.core.extend.jpa.page.PageRequestVo;
import com.slfinance.redpack.core.extend.validate.annotations.Rule;
import com.slfinance.redpack.core.extend.validate.annotations.Rules;
import com.slfinance.redpack.core.response.ResponseVo;
import com.slfinance.redpack.core.services.AppVersionService;

/**
 * 
 * 
 * @author taoxm
 * @version $Revision:1.0.0, $Date: 2016年8月17日 上午10:19:15 $
 */
@RestController("crmAppversionController")
@RequestMapping(value = "/crm/appVersion", method = RequestMethod.POST)
public class AppVersinController extends BaseController {

	@Autowired
	private AppVersionService appVersionService;

	/**
	 * APP版本信息作废
	 * 
	 * @author taoxm
	 * @createTime 2016年8月17日 下午2:27:17
	 * @param params
	 * @return
	 */
	@RequestMapping("/invalid")
	@Rules({ @Rule(name = "id", required = true, requiredMessage = "700001") })
	public ResponseVo invalid(@RequestBody Map<String, Object> params) {
		String id = MapUtil.getStringTrim(params, "id");
		appVersionService.invalidById(id);
		return ResponseVo.success();
	}

	/**
	 * APP版本信息发布
	 * 
	 * @author taoxm
	 * @createTime 2016年8月17日 下午2:27:17
	 * @param params
	 * @return
	 */
	@RequestMapping("/release")
	@Rules({ @Rule(name = "id", required = true, requiredMessage = "700001") })
	public ResponseVo release(@RequestBody Map<String, Object> params) {
		String id = MapUtil.getStringTrim(params, "id");
		String userId = getLoginUserId();
		appVersionService.releaseById(id, userId);
		return ResponseVo.success();
	}

	/**
	 * APP版本信息(新增|修改)
	 * 
	 * @author taoxm
	 * @createTime 2016年8月17日 下午3:05:43
	 * @param params
	 * @return
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@RequestMapping("/save")
	@Rules({ @Rule(name = "appVersion", required = true, requiredMessage = "700004", appVersion = true, appVersionMessage = "700007"), @Rule(name = "updateContent", required = true, requiredMessage = "700005") })
	public ResponseVo save(@RequestBody Map<String, Object> params) throws IllegalAccessException, InvocationTargetException {
		AppVersion appVersion = null;
		if (StringUtils.isBlank(MapUtil.getString(params, "id"))) {
			appVersion = new AppVersion();
			// 新增时platform不能为空
			if (StringUtils.isBlank(MapUtil.getStringTrim(params, "platform"))) {
				throw new SLException("700003");
			}
			Platform platform = Platform.valueOf(MapUtil.getStringTrim(params, "platform"));
			// 如果平台为android,downLoadURL为必输项
			if (platform == Platform.android && !StringUtils.isNotBlank(MapUtil.getStringTrim(params, "downloadURL"))) {
				throw new SLException("700006");
			}
			appVersion.setPlatform(platform);
		} else {
			// 修改时不能修改platform
			appVersion = appVersionService.findOne(MapUtil.getStringTrim(params, "id"));
		}
		appVersion.setAppVersion(MapUtil.getStringTrim(params, "appVersion"));
		appVersion.setUpdateContent(MapUtil.getStringTrim(params, "updateContent"));
		appVersion.setDownloadURL(MapUtil.getStringTrim(params, "downloadURL"));
		appVersionService.saveAppversion(appVersion);
		return ResponseVo.success();
	}

	/**
	 * 查询app版本列表
	 * 
	 * @author taoxm
	 * @createTime 2016年8月17日 下午3:29:30
	 * @param pageRequest
	 * @return
	 */
	@RequestMapping("/findAllPage")
	@Serialize({ @SerializeRule(clazz = Map.class, include = { "total", "data", "id", "platform", "appVersion", "updateContent", "downloadURL", "releasedDate", "releasedUser", "status" }) })
	public ResponseVo findAllPage(PageRequestVo pageRequest) {
		return ResponseVo.success(appVersionService.findAllPage(pageRequest));
	}
}
