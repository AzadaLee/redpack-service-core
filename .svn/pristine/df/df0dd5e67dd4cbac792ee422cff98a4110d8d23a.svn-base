/** 
 * @(#)VerificationCode.java 1.0.0 2016年7月25日 下午2:24:26  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */

package com.slfinance.redpack.core.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.slfinance.redpack.core.constants.enums.BusinessType;
import com.slfinance.redpack.core.constants.enums.CodeType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 验证码信息表
 * 
 * @author SangLy
 * @version $Revision:1.0.0, $Date: 2016年7月25日 下午2:24:26 $
 */
@Entity
@Table(name = "RP_T_VERIFICATION_CODE")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class VerificationCode extends com.slfinance.redpack.core.entities.base.Entity {

	private static final long serialVersionUID = -5000000000000000014L;

	/**
	 * 发送目标地址
	 */
	public String sendAddress;

	/**
	 * 验证类型
	 */
	@Enumerated(EnumType.STRING)
	public CodeType codeType;

	/**
	 * 业务类型
	 */
	@Enumerated(EnumType.STRING)
	public BusinessType businessType;

	/**
	 * 验证码
	 */
	public String verificationCode;

	/**
	 * 发送时间
	 */
	public Date sendTime;

	/**
	 * 失效时间
	 */
	public Date invalidTime;

	/**
	 * 创建时间
	 */
	public Date createdDate;

	/**
	 * 是否已验证
	 */
	public boolean isValid;

	/**
	 * 创建人
	 */
	public String createdUser;

}
