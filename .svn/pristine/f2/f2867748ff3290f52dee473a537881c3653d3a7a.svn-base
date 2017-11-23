/** 
 * @(#)DistributeServiceTest.java 1.0.0 2016年11月14日 下午2:20:35  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */

package com.slfinance.redpack.core.services;

import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.slfinance.redpack.core.ServiceCoreApplicationTests;
import com.slfinance.redpack.core.entities.Distribute;

/**
 * 
 * 
 * @author SangLy
 * @version $Revision:1.0.0, $Date: 2016年11月14日 下午2:20:35 $
 */
public class DistributeServiceTest extends ServiceCoreApplicationTests {

	@Autowired
	private DistributeService distributeServic;

	@Test
	public void testRobRedPack() {
		Map<String ,Object> distribute = distributeServic.robRedPack("409083d158680c360158680e0739001c", "409083d1586ac57501586ace6ade000c");
		System.out.println(((Distribute)distribute.get("distribute")).getAmount());
	}

}
