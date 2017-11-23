/** 
 * @(#)ModifyCustomerRedpackOrderPayStatusThread.java 1.0.0 2016年11月17日 下午3:49:23  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */

package com.slfinance.redpack.core.thread;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.slfinance.redpack.common.utils.DateUtil;
import com.slfinance.redpack.core.constants.RedpackRecordStatus;
import com.slfinance.redpack.core.constants.enums.RedpackStatus;
import com.slfinance.redpack.core.entities.RedPack;
import com.slfinance.redpack.core.services.RedPackService;

/**
 * 前台客户，订单30分钟没有支付，把订单设置成失效状态，并在设置失效状态之前发
 * 
 * @author SangLy
 * @version $Revision:1.0.0, $Date: 2016年11月17日 下午3:49:23 $
 */
public class ModifyCustomerRedpackOrderPayStatusThread extends Thread {
	private final Log logger = LogFactory.getLog(ModifyRedPackStatusNotOpenToOpeningThread.class);

	private RedPackService redpackService;

	private Date systemTime = new Date();

	private RedPack redpack = null;

	public ModifyCustomerRedpackOrderPayStatusThread(RedPackService redpackService) {
		this.redpackService = redpackService;
		this.start();
	}

	@Override
	public void run() {
		while (true) {
			try {
				systemTime = new Date();
				// 查询最近一条没有支付的订单
				redpack = redpackService.findCloselyHaveNotPayOfCustomerOrder(RedpackStatus.待付款,RedpackRecordStatus.正常);
				if(redpack != null){
					if (redpack.getCreatedDate().getTime() < systemTime.getTime()) {
						// 订单生成30分钟还没有支付
						if (DateUtil.addMinutes(redpack.getCreatedDate(), 30).compareTo(systemTime) <= 0) {
							redpackService.modifyCustomerRedpackOrderPayStatusThreadSevice(redpack);
						} else {
							// 休眠
							Thread.sleep(DateUtil.addMinutes(redpack.getCreatedDate(), 30).getTime() - systemTime.getTime());
						}
					} else {
						// 订单失效
						redpackService.modifyCustomerRedpackOrderPayStatusThreadSevice(redpack);
					}
				}else{
					// 没有"待付款"订单，休眠1分钟做刷新
					Thread.sleep(1 * 60000);
				}
				
			} catch (Exception e) {
				logger.error("前台客户，订单30分钟没有支付，把订单设置成失效状态 error", e);
			}
		}
	}
}
