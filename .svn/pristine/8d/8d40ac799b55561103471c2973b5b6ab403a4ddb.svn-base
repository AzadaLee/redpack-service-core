/** 
 * @(#)SmsMessageService.java 1.0.0 2016年7月26日 上午10:18:10  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */

package com.slfinance.redpack.core.services;

import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.slfinance.redpack.core.configs.SMSClientConfig;
import com.slfinance.redpack.core.constants.enums.BusinessType;
import com.slfinance.redpack.core.constants.enums.CodeType;
import com.slfinance.redpack.core.constants.enums.SMSContentTemplate;
import com.slfinance.redpack.core.entities.SMSMessage;
import com.slfinance.redpack.core.entities.VerificationCode;
import com.slfinance.redpack.core.exception.SLException;
import com.slfinance.redpack.core.extend.validate.RuleUtils;
import com.slfinance.redpack.core.repositories.SMSMessageRepository;
import com.slfinance.redpack.core.services.base.BaseService;
import com.slfinance.redpack.core.sms.ISmsService;

/**
 * 短信
 * 
 * @author SangLy
 * @version $Revision:1.0.0, $Date: 2016年7月26日 上午10:18:10 $
 */
@Service
public class SMSMessageService extends BaseService<SMSMessage, SMSMessageRepository> {

	private @Autowired SMSClientConfig smsConfig;
	private @Autowired VerificationCodeService verificationCodeService;
	private @Autowired ISmsService localSmsService;
	private @Autowired CustomerService customerService;

	/**
	 * 
	 * @author SangLy
	 * @createTime 2016年8月12日 下午2:22:57
	 * @param phone
	 *            发送目标手机号
	 * @param businessType
	 *            发送短信类型。 例如： 登录注册、找回密码
	 * @param contentTemplate
	 *            短信内容模板。例如：验证类短信或推广类短信
	 * @param loginUserId
	 *            登录用户id
	 * @throws SLException
	 */
	@Transactional
	public void sendMobileVerificationCode(String phone, BusinessType businessType, SMSContentTemplate contentTemplate, String loginUserId) throws SLException {
		//判断是否为手机号
		if(!RuleUtils.isMobile(phone)){
			throw new SLException("100014", "phone number is wrong");
		}
		//判断手机是否被注册
		customerService.validateMobileExists(phone, businessType);
		// 如果超过设置的每个手机最多发送的最大条数则报错
		if (smsConfig.getOnePhoneMaxSendPerDay() <= verificationCodeService.phoneSendCountOfDay(phone, new Date())) {
			throw new SLException("100026", "Have One Phone Max Send PerDay");
		}
		//随机生成手机验证码
//		String code = getRandomCode();
		String code = "111111";
		verificationCodeService.save(getVerificationCode(phone, code, new Date(), businessType, loginUserId));
		localSmsService.asyncSend(phone, String.format(smsConfig.getContentTemplate().get(businessType.getCode()), code), contentTemplate);
	}

	private synchronized String getRandomCode() {
		return RandomStringUtils.randomNumeric(6);
	}

	private VerificationCode getVerificationCode(String phone, String code, Date sendTime, BusinessType businessType, String loginUserId) {
		return new VerificationCode(phone, CodeType.手机, businessType, code, sendTime, DateUtils.addMilliseconds(sendTime, smsConfig.getMaxInactiveInterval()), new Date(), true, loginUserId);
	}
}
