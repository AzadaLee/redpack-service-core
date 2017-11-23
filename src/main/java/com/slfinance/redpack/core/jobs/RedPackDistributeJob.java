/** 
 * @(#)RedPackDistributeJob.java 1.0.0 2016年11月11日 下午4:14:21  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */

package com.slfinance.redpack.core.jobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.slfinance.redpack.core.extend.schedule.ScheduleJob;
import com.slfinance.redpack.core.services.RedPackService;

/**
 * 凌晨2点往当天红包预分配表添加记录
 * 
 * 
 * @author SangLy
 * @version $Revision:1.0.0, $Date: 2016年11月11日 下午4:14:21 $
 */
@Component
@ScheduleJob(cron = "0 0 2 * * ?")
public class RedPackDistributeJob extends AbstractJob {

	@Autowired
	private RedPackService redPackService;

	@Override
	public void execute() {
		redPackService.redPackDistributeJob();
	}

	@Override
	public String getJobName() {
		// TODO Auto-generated method stub
		return "当天红包预分配记录";
	}
}
