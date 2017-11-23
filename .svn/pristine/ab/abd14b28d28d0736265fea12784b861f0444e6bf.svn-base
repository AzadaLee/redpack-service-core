/** 
 * @(#)OperateLogServiceTest.java 1.0.0 2016年8月18日 下午4:59:37  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */

package com.slfinance.redpack.core.services;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.slfinance.redpack.common.utils.DateUtil;
import com.slfinance.redpack.core.ServiceCoreApplicationTests;
import com.slfinance.redpack.core.constants.enums.LogType;
import com.slfinance.redpack.core.constants.enums.UserType;
import com.slfinance.redpack.core.entities.OperateLog;

/**
 * 
 * 
 * @author samson
 * @version $Revision:1.0.0, $Date: 2016年8月18日 下午4:59:37 $
 */
public class OperateLogServiceTest extends ServiceCoreApplicationTests {

	@Autowired
	private OperateLogService operateLogService;

	@Test
	public void testSaveAsync() throws InterruptedException {
		operateLogService.saveAsync(new OperateLog(UserType.robot, LogType.APP推送失败, "afeafeaf", null, new Date(), "111"));
		Thread.currentThread().join();
	}

	@Test
	public void testCountByLogTypeAndOperateDateBetween() {
		Date now = new Date();
		Date todayStart = DateUtil.weedDayBelow(now);
		Date todayEnd = new Date(DateUtil.addDays(todayStart, 1).getTime() - DateUtil.MILLIS_PER_SECOND);
		operateLogService.countByTypeAndContentAndOperateDateBetween(LogType.广告点击, "1", todayStart, todayEnd);
		long aa = operateLogService.countByTypeAndContentAndOperateDateBetween(LogType.广告点击, "1", todayStart, todayEnd);
		Assert.assertTrue(aa >= 0);
	}

	@Test
	public void testStatisticsAdvertisement() {
		Date now = new Date();
		Date todayStart = DateUtil.weedDayBelow(now);
		Date todayEnd = new Date(DateUtil.addDays(todayStart, 1).getTime() - DateUtil.MILLIS_PER_SECOND);
		List<Map<String, Object>> list = operateLogService.statisticsAdvertisement(Lists.newArrayList(LogType.广告点击), todayStart, todayEnd);
		Assert.assertNotNull(list);
	}
}
