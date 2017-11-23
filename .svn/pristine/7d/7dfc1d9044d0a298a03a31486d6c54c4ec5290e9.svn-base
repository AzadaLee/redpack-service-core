/** 
 * @(#)AppPushServiceTest.java 1.0.0 2016年8月18日 下午5:18:05  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */

package com.slfinance.redpack.core.services;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.slfinance.redpack.core.ServiceCoreApplicationTests;

/**
 * 
 * 
 * @author samson
 * @version $Revision:1.0.0, $Date: 2016年8月18日 下午5:18:05 $
 */
public class AppPushServiceTest extends ServiceCoreApplicationTests {

	@Autowired
	AppPushService appPushService;

	@Autowired
	RedPackService redpackService;

	@Test
	public void testPushRedpackAppointment() throws InterruptedException {
		appPushService.pushRedpackAppointment(redpackService.findOne("4090829356c47a790156c489a95e0001"));
	}
}
