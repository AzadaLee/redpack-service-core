/** 
 * @(#)AppVersionServiceTest.java 1.0.0 2016年8月19日 上午10:49:29  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */ 

package com.slfinance.redpack.core.services;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.slfinance.redpack.core.ServiceCoreApplicationTests;
import com.slfinance.redpack.core.constants.enums.Platform;
import com.slfinance.redpack.core.entities.AppVersion;
import com.slfinance.redpack.core.extend.jpa.page.PageRequestVo;

/**   
 * 
 *  
 * @author  taoxm
 * @version $Revision:1.0.0, $Date: 2016年8月19日 上午10:49:29 $ 
 */
public class AppVersionServiceTest extends ServiceCoreApplicationTests{
	
	@Autowired
	private AppVersionService appVersionService;
	
	/**
	 * 根据id废除appVersion
	 * @author taoxm
	 * @createTime 2016年8月19日 上午11:14:38
	 */
	@Test
	public void testInvalidById(){
		String id = "5b1ed505-179c-4ab9-9b19-e2a7a14b4408";
		Assert.assertTrue(appVersionService.invalidById(id) != null);
	}
	
	/**
	 * 根据id发布appVersion
	 * @author work
	 * @createTime 2016年8月19日 上午11:15:48
	 */
	@Test
	public void testReleaseById(){
		String id = "5b1ed505-179c-4ab9-9b19-e2a7a14b4408";
		String userId = "123456789"; 
		Assert.assertTrue(appVersionService.releaseById(id,userId) != null);
	}
	
	/**
	 * 测试保存appversion
	 * @author taoxm
	 * @createTime 2016年8月19日 上午11:36:10
	 */
	@Test
	public void testSaveAppversion(){
		String platform = "ios";
		String version = "1.0.2";
		String updateContent = "更改下载地址";
		String downloadURL = "wwwww";
		AppVersion appVersion = new AppVersion();
		appVersion.setPlatform(Platform.valueOf(platform));
		appVersion.setAppVersion(version);
		appVersion.setDownloadURL(downloadURL);
		appVersion.setUpdateContent(updateContent);
		Assert.assertTrue(appVersionService.saveAppversion(appVersion) != null);
	}
	
	/**
	 * 测试APP版本信息列表
	 * @author taoxm
	 * @createTime 2016年8月17日 下午3:21:28
	 * @param pageRequest
	 * @return
	 */
	public void findAllPage() {
		PageRequestVo pageRequest = new PageRequestVo();
		Assert.assertTrue(appVersionService.findAllPage(pageRequest) != null);
	}
	
	/**
	 * 
	 * @author SangLy
	 * @createTime 2016年8月30日 上午9:55:54
	 */
	public void testAppGetCurrentVersion(){
		Assert.assertTrue(appVersionService.appGetCurrentVersion(Platform.android) != null);
	}
}
