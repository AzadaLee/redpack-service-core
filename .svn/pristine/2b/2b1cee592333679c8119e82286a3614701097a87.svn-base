/** 
 * @(#)AppVersionService.java 1.0.0 2016年8月17日 下午2:33:06  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */

package com.slfinance.redpack.core.services;

import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.slfinance.redpack.core.constants.enums.AppVersionStatus;
import com.slfinance.redpack.core.constants.enums.Platform;
import com.slfinance.redpack.core.entities.AppVersion;
import com.slfinance.redpack.core.exception.SLException;
import com.slfinance.redpack.core.extend.jpa.page.PageRequestVo;
import com.slfinance.redpack.core.extend.jpa.page.PageResponse;
import com.slfinance.redpack.core.repositories.AppVersionRepository;
import com.slfinance.redpack.core.services.base.BaseService;

/**
 * 
 * 
 * @author taoxm
 * @version $Revision:1.0.0, $Date: 2016年8月17日 下午2:33:06 $
 */
@Service
public class AppVersionService extends BaseService<AppVersion, AppVersionRepository> {

	/**
	 * 根据id废除appVersion
	 * 
	 * @author taoxm
	 * @createTime 2016年8月17日 下午2:59:08
	 * @param id
	 * @return
	 */
	@Transactional
	public AppVersion invalidById(String id) {
		AppVersion appVersion = findOne(id);
		if (null == appVersion) {
			throw new SLException("700002");
		}
		appVersion.setStatus(AppVersionStatus.已作废);
		return save(appVersion);
	}

	/**
	 * 根据id发布appVersion
	 * 
	 * @author taoxm
	 * @createTime 2016年8月17日 下午2:59:08
	 * @param id
	 *            userId
	 * @return
	 */
	@Transactional
	public AppVersion releaseById(String id, String userId) {
		AppVersion appVersion = findOne(id);
		//1、判断当前appVersion是否存在
		if (null == appVersion) {
			throw new SLException("700002");
		}
		//2、判断当前发布的版本是否大于所有发布版本
		AppVersion currentMaxAppversion = appGetCurrentVersion(appVersion.getPlatform());
		if(currentMaxAppversion != null){
			if(currentMaxAppversion.getAppVersion().compareTo(appVersion.getAppVersion()) >= 0){
				throw new SLException("700008");
			}
		}
		appVersion.setReleasedUser(userId);
		appVersion.setReleasedDate(new Date());
		appVersion.setStatus(AppVersionStatus.已发布);
		return save(appVersion);
	}

	/**
	 * APP版本信息(新增|修改)
	 * 
	 * @author taoxm
	 * @createTime 2016年8月17日 下午3:21:02
	 * @param appVersion
	 * @return
	 */
	@Transactional
	public AppVersion saveAppversion(AppVersion appVersion) {
		return save(appVersion);
	}

	/**
	 * APP版本信息列表
	 * 
	 * @author taoxm
	 * @createTime 2016年8月17日 下午3:21:28
	 * @param pageRequest
	 * @return
	 */
	public PageResponse<Map<String, Object>> findAllPage(PageRequestVo pageRequest) {
		PageResponse<Map<String, Object>> response = repository.findAllPage(pageRequest);
		return response;
	}

	/**
	 * app-获取当前服务器最新的版本号
	 * 
	 * @author SangLy
	 * @createTime 2016年8月19日 上午11:30:59
	 * @param platform
	 * @return
	 */
	public AppVersion appGetCurrentVersion(Platform platform) {
		return repository.findFirstByPlatformAndStatusOrderByReleasedDateDesc(platform, AppVersionStatus.已发布);
	}
	
}
