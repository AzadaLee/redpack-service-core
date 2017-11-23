/** 
 * @(#)ISmsService.java 1.0.0 2016年5月20日 上午9:25:34  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */

package com.slfinance.redpack.core.sms;

import java.util.List;

import org.springframework.scheduling.annotation.Async;

import com.slfinance.redpack.core.constants.enums.SMSContentTemplate;
import com.slfinance.redpack.core.exception.SLException;



/**
 * 短信服务接口
 * 
 * @author SangLy
 * @version $Revision:1.0.0, $Date: 2016年5月20日 上午9:25:34 $
 */
public interface ISmsService {
	/**
	 * 同步发送短信
	 * @author SangLy
	 * @createTime 2016年5月25日 下午2:36:09
	 * @param phone
	 * @param content
	 * @throws SLException
	 */
	void syncSend(String phone, String content, SMSContentTemplate contentTemplate) throws SLException;
	
	/**
	 * 异步发送短信
	 * @author SangLy
	 * @createTime 2016年5月25日 下午2:36:14
	 * @param phone
	 * @param content
	 * @throws SLException
	 */
	@Async
	void asyncSend(String phone, String content, SMSContentTemplate contentTemplate) throws SLException;
	
	/**
	 * 批量同步发送短信
	 * @author SangLy
	 * @createTime 2016年5月25日 下午2:36:28
	 * @param phone
	 * @param content
	 * @throws SLException
	 */
	void syncBatchSend(List<String> phone, String content, SMSContentTemplate contentTemplate) throws SLException;
	
	/**
	 * 批量异步发送短信
	 * @author SangLy
	 * @createTime 2016年5月25日 下午2:36:45
	 * @param phone
	 * @param content
	 * @throws SLException
	 */
	@Async
	void asyncBatchSend(List<String> phone, String content, SMSContentTemplate contentTemplate) throws SLException;
}
