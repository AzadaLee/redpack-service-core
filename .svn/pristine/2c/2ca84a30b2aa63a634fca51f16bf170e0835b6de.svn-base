/** 
 * @(#)ReturnCustomerNotRobRedpackMoneyJob.java 1.0.0 2016年11月21日 下午3:50:45  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */

package com.slfinance.redpack.core.jobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.slfinance.redpack.core.extend.schedule.ScheduleJob;
import com.slfinance.redpack.core.services.RedPackService;

/**
 * 未抢完的红包返现
 * 
 * @author SangLy
 * @version $Revision:1.0.0, $Date: 2016年11月21日 下午3:50:45 $
 */
@Component
@ScheduleJob(cron = "0 0 1 * * ?")
public class ReturnCustomerNotRobRedpackMoneyJob extends AbstractJob {

	@Autowired
	private RedPackService redPackService;

	@Override
	public void execute() {
		redPackService.returnCustomerNotRobRedpackMoneyJob();
	}

	@Override
	public String getJobName() {
		// TODO Auto-generated method stub
		return "未抢完的红包返现";
	}

}
