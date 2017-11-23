/** 
 * @(#)RedPackDistributeJob.java 1.0.0 2016年11月11日 下午4:14:21  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */

package com.slfinance.redpack.core.jobs;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.slfinance.redpack.core.extend.schedule.ScheduleJob;
import com.slfinance.redpack.core.services.RedPackService;

/**
 * 红包过期定时任务(每天凌晨)
 * 
 * 
 * @author SangLy
 * @version $Revision:1.0.0, $Date: 2016年11月11日 下午4:14:21 $
 */
@Component
@ScheduleJob(cron = "0 0 0 * * ?")
public class RedpackExpireJob extends AbstractJob {

	@Autowired
	private RedPackService redPackService;

	@Override
	public void execute() {
		redPackService.expire(new Date());
	}

	@Override
	public String getJobName() {
		// TODO Auto-generated method stub
		return "当天红包预分配记录";
	}
}
