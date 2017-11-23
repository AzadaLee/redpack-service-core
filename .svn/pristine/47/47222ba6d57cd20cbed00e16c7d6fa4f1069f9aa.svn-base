/** 
 * @(#)CustomerServiceTest.java 1.0.0 2016年8月15日 下午4:08:39  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */

package com.slfinance.redpack.core.services;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.slfinance.redpack.core.CreditServiceCoreApplicationTests;
import com.slfinance.redpack.core.entities.Customer;

/**
 * 
 * 
 * @author SangLy
 * @version $Revision:1.0.0, $Date: 2016年8月15日 下午4:08:39 $
 */
public class CustomerServiceTest extends CreditServiceCoreApplicationTests {

	@Autowired
	private CustomerService customerService;

	@Test
	public void testRegister() {

		Customer customer = new Customer();
		customer.setMobile("15716382738");
		customer.setPassword("879766");

		// 不带邀请码的注册
		// customerService.register(customer, "727392", "");
		// 带邀请码的注册
		customerService.register(customer, "879766", "YAO_QING_MA");

	}

	@Test
	public void testAppResetPassword() {
		customerService.appResetPassword("15716382738", "636157", "789635");
	}

}
