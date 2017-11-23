/** 
 * @(#)RedPackAppointmentPushThread.java 1.0.0 2016年8月26日 下午4:33:44  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */

package com.slfinance.redpack.core.thread;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.slfinance.redpack.common.utils.DateUtil;
import com.slfinance.redpack.core.constants.RedPackConstant;
import com.slfinance.redpack.core.entities.RedPack;
import com.slfinance.redpack.core.services.AppPushService;
import com.slfinance.redpack.core.services.RedPackService;

/**
 * 红包预约推送
 * 
 * @author samson
 * @version $Revision:1.0.0, $Date: 2016年8月26日 下午4:33:44 $
 */
public class RedPackAppointmentPushThread extends Thread {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	RedPackService redPackService;
	AppPushService appPushService;

	public RedPackAppointmentPushThread(RedPackService appPackService, AppPushService appPushService) {
		this.redPackService = appPackService;
		this.appPushService = appPushService;
		this.start();
	}

	@Override
	public void run() {
		while (true) {
			try {
				Date systemTime = new Date();
				RedPack redpack = redPackService.findCloselyHaveNotOpen(systemTime);
				// 不存在||已推送过||距开启时间>(5+3)分钟则休眠5分钟,主要依据红包开启5分钟内不能创建、修改和推送提前3分钟发送;则休眠5分钟
				if ((redpack == null) || appPushService.findAllByTarget(redpack.getId()).size() > 0 || ((redpack.getTimePoint().getTime() - systemTime.getTime()) > (RedPackConstant.REDPACK_MODIFY_BEFORE_NOW_MINS + RedPackConstant.REDPACK_APPOINTMENT_PUSH_AHEAD_MINS) * DateUtil.MINUTE_MILISECONDS)) {
					logger.info("open redpack more than 8 minutes,sleep start");
					sleep(RedPackConstant.REDPACK_MODIFY_BEFORE_NOW_MINS * DateUtil.MINUTE_MILISECONDS);
					logger.info("open redpack more than 8 minutes end,sleep end");

					// 只推送不过时的
				} else if ((redpack.getTimePoint().getTime() - systemTime.getTime()) > 0) {
					// 距开启时间还有3分钟内开始推送
					if ((redpack.getTimePoint().getTime() - systemTime.getTime()) <= RedPackConstant.REDPACK_APPOINTMENT_PUSH_AHEAD_MINS * DateUtil.MINUTE_MILISECONDS) {
						appPushService.pushRedpackAppointment(redpack);

						// 距开启时间还有3-8分钟内 则休眠剩余时间差
					} else {
						logger.info("push redpack appointment between 3 and 8 minutes,sleep start");
						sleep(redpack.getTimePoint().getTime() - systemTime.getTime() - RedPackConstant.REDPACK_APPOINTMENT_PUSH_AHEAD_MINS * DateUtil.MINUTE_MILISECONDS);
						logger.info("push redpack appointment between 3 and 8 minutes,sleep end");
					}
				} else {
					logger.info("cant push redpack appointment other..");
				}
			} catch (Exception e) {
				logger.info("error", e);
			}
		}
	}
}
