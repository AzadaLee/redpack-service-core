/** 
 * @(#)VerificationCodeRepository.java 1.0.0 2016年7月25日 下午5:01:52  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */

package com.slfinance.redpack.core.repositories;

import java.util.Date;

import com.slfinance.redpack.core.constants.enums.BusinessType;
import com.slfinance.redpack.core.entities.VerificationCode;
import com.slfinance.redpack.core.repositories.base.BaseRepository;

/**
 * 
 * 
 * @author SangLy
 * @version $Revision:1.0.0, $Date: 2016年7月25日 下午5:01:52 $
 */
public interface VerificationCodeRepository extends BaseRepository<VerificationCode> {

	VerificationCode findFirstBySendAddressAndBusinessTypeOrderBySendTimeDesc(String sendAddress, BusinessType businessType);
	VerificationCode findFirstBySendAddressAndBusinessTypeAndVerificationCodeAndCreatedUserOrderBySendTimeDesc(String sendAddress, BusinessType businessType,String verificationCode, long createdUser);

	/**
	 * 根据发送时间
	 * 
	 * @author SangLy
	 * @createTime 2016年8月16日 上午9:53:43
	 * @param lastname
	 * @return
	 */
	Long countBySendAddressAndSendTimeGreaterThanEqual(String sendAddress, Date day);
}
