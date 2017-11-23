/** 
 * @(#)ThreadServieConfig.java 1.0.0 2016年8月22日 下午5:22:12  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */

package com.slfinance.redpack.core.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.slfinance.redpack.core.services.AppPushService;
import com.slfinance.redpack.core.services.MessageService;
import com.slfinance.redpack.core.services.OperateLogService;
import com.slfinance.redpack.core.services.RedPackService;
import com.slfinance.redpack.core.thread.CustomerRedpackOrderNotPaySendMessageThread;
import com.slfinance.redpack.core.thread.ModifyCustomerRedpackOrderPayStatusThread;
import com.slfinance.redpack.core.thread.ModifyRedPackStatusNotOpenToOpeningThread;
import com.slfinance.redpack.core.thread.ModifyRedPackStatusOpeningToOpendThread;
import com.slfinance.redpack.core.thread.RedPackAppointmentPushThread;

/**
 * 
 * 
 * @author SangLy
 * @version $Revision:1.0.0, $Date: 2016年8月22日 下午5:22:12 $
 */
@Configuration
public class ThreadServieConfig {

	@Bean(name = { "modifyRedPackStatusOpeningThread" })
	public ModifyRedPackStatusNotOpenToOpeningThread getModifyRedPackStatusOpeningThread(RedPackService redpackService, OperateLogService operateLogService) {
		ModifyRedPackStatusNotOpenToOpeningThread modifyRedPackStatusOpeningThread = new ModifyRedPackStatusNotOpenToOpeningThread(redpackService, operateLogService);
		return modifyRedPackStatusOpeningThread;
	}

	@Bean(name = { "modifyRedPackStatusOpeningToOpendThread" })
	public ModifyRedPackStatusOpeningToOpendThread getModifyRedPackStatusOpeningToOpendThread(RedPackService redpackService, OperateLogService operateLogService) {
		ModifyRedPackStatusOpeningToOpendThread modifyRedPackStatusOpeningToOpendThread = new ModifyRedPackStatusOpeningToOpendThread(redpackService, operateLogService);
		return modifyRedPackStatusOpeningToOpendThread;
	}
	
	@Bean(name = { "modifyCustomerRedpackOrderPayStatusThread" })
	public ModifyCustomerRedpackOrderPayStatusThread getModifyCustomerRedpackOrderPayStatusThread(RedPackService redpackService) {
		ModifyCustomerRedpackOrderPayStatusThread modifyCustomerRedpackOrderPayStatusThread = new ModifyCustomerRedpackOrderPayStatusThread(redpackService);
		return modifyCustomerRedpackOrderPayStatusThread;
	}
	
	@Bean(name = { "customerRedpackOrderNotPaySendMessageThread" })
	public CustomerRedpackOrderNotPaySendMessageThread getCustomerRedpackOrderNotPaySendMessageThread(RedPackService redpackService,MessageService messageService) {
		CustomerRedpackOrderNotPaySendMessageThread customerRedpackOrderNotPaySendMessageThread = new CustomerRedpackOrderNotPaySendMessageThread(redpackService,messageService);
		return customerRedpackOrderNotPaySendMessageThread;
	}

	@Bean
	public RedPackAppointmentPushThread redPackAppointmentPushThread(RedPackService redpackService, AppPushService appPushService) {
		return new RedPackAppointmentPushThread(redpackService, appPushService);
	}
}
