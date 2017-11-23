/** 
 * @(#)VerificationCodeService.java 1.0.0 2016年7月26日 上午10:22:23  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */

package com.slfinance.redpack.core.services;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;

import com.slfinance.redpack.core.constants.enums.BusinessType;
import com.slfinance.redpack.core.entities.VerificationCode;
import com.slfinance.redpack.core.exception.SLException;
import com.slfinance.redpack.core.repositories.VerificationCodeRepository;
import com.slfinance.redpack.core.services.base.BaseService;
import com.slfinance.redpack.common.utils.DateUtil;
/**
 * 验证码信息
 * 
 * @author SangLy
 * @version $Revision:1.0.0, $Date: 2016年7月26日 上午10:22:23 $
 */
@Service
public class VerificationCodeService extends BaseService<VerificationCode, VerificationCodeRepository> {
	/**
	 * 获取有效的验证码
	 * 
	 * @author LiYing
	 * @createTime 2016年3月29日 上午10:13:57
	 * @param sendAddress
	 * @return
	 * @throws SLException
	 */
	public VerificationCode getSentVerificationCode(String sendAddress, BusinessType businessType) throws SLException {
		VerificationCode verificationCode = repository.findFirstBySendAddressAndBusinessTypeOrderBySendTimeDesc(sendAddress, businessType);
		if (verificationCode == null) {
			throw new SLException("100008", "Can not find any verification code base on sendAddress! sendAddress:%s",
					sendAddress);
		}

		if (!verificationCode.isValid()) {
			throw new SLException("100007", "Verification code had used!");
		}

		if (DateUtils.truncatedCompareTo(verificationCode.getInvalidTime(), new Date(), Calendar.MILLISECOND) < 0) {
			throw new SLException("100005", "Verification code had expired!");
		}
		return verificationCode;
	}

	/**
	 * 验证码的验证
	 * 
	 * @author LiYing
	 * @createTime 2016年5月24日 下午2:44:16
	 * @param verificationCode
	 * @param sendAddress
	 * @param businessType
	 * @return
	 * @throws SLException
	 */
	public VerificationCode checkVerificationCode(String verificationCode, String sendAddress,
			BusinessType businessType) throws SLException {
		VerificationCode sentVerificationCode = getSentVerificationCode(sendAddress, businessType);
		if (!sentVerificationCode.getVerificationCode().equals(verificationCode)) {
			throw new SLException("100006", "Verification code is wrong!");
		}
		return sentVerificationCode;
	}

	/**
	 * 邮箱回调验证码验证
	 * 
	 * @author jeff
	 * @createTime 2016年6月1日 下午1:58:49
	 * @param verificationCode
	 * @param sendAddress
	 * @param businessType
	 * @param createdUser
	 * @return
	 * @throws SLException
	 */
	public VerificationCode checkVerificationCode(String verificationCode, String sendAddress,
			BusinessType businessType, long createdUser) throws SLException {
		VerificationCode emailVerificationCode = repository
				.findFirstBySendAddressAndBusinessTypeAndVerificationCodeAndCreatedUserOrderBySendTimeDesc(sendAddress,
						businessType, verificationCode, createdUser);
		if (emailVerificationCode == null) {
			throw new SLException("120029", "The verify message isn't exsit!");
		}
		if (!emailVerificationCode.isValid()) {
			throw new SLException("120051", "The email has verified!");
		}
		Date currentTime = new Date();
		if (currentTime.after(emailVerificationCode.getInvalidTime())) {
			throw new SLException("120023", "The email has Invalid!");
		}
		return emailVerificationCode;
	}

	/**
	 * 验证验证码，验证哈后设置为无效（验证码已经使用）
	 * 
	 * @author LiYing
	 * @createTime 2016年5月24日 下午2:23:05
	 * @param sendAddress
	 * @param verificationCode
	 * @param businessType
	 * @throws SLException
	 */
	public void settingVerificationInvalid(String sendAddress, String verificationCode, BusinessType businessType)
			throws SLException {
		VerificationCode sentVerificationCode = checkVerificationCode(verificationCode, sendAddress, businessType);
		sentVerificationCode.setValid(false);
		update(sentVerificationCode);
	}
	
	/**
	 * 更具传入手机号和日期查询当天已经发送的手机短信个数
	 * 
	 * @author SangLy
	 * @createTime 2016年8月16日 上午9:34:56
	 * @param phone
	 *            手机号
	 * @param day
	 *            传入的日期：注意这个日期会自动取整，例如：2016年8月16日9:36:06 会自动变成2016年8月16日00:00：00
	 * @return
	 */
	public Long phoneSendCountOfDay(String phone, Date day) {
		Date date= DateUtil.weedDayBelow(day);
		return repository.countBySendAddressAndSendTimeGreaterThanEqual(phone, date);
	}
}
