/** 
 * @(#)SignLog.java 1.0.0 2016年7月25日 下午1:55:09  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */

package com.slfinance.redpack.core.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.slfinance.redpack.core.constants.enums.Identification;
import com.slfinance.redpack.core.constants.enums.Platform;
import com.slfinance.redpack.core.constants.enums.SignLogType;
import com.slfinance.redpack.core.constants.enums.UserType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 踪迹日志表(用户注册、登录等日志表)
 * 
 * @author SangLy
 * @version $Revision:1.0.0, $Date: 2016年7月25日 下午1:55:09 $
 */
@Entity
@Table(name = "RP_T_SIGN_LOG")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class SignLog extends com.slfinance.redpack.core.entities.base.Entity {

	private static final long serialVersionUID = -5000000000000000011L;

	/**
	 * 用户类型
	 */
	@Enumerated(EnumType.STRING)
	private UserType userType;

	/**
	 * 类型
	 */
	@Enumerated(EnumType.STRING)
	private SignLogType type;

	/**
	 * 
	 */
	@Enumerated(EnumType.STRING)
	private Platform platform;

	/**
	 * IP
	 */
	private String ip;

	/**
	 * 
	 */
	private Identification identification;

	/**
	 * 创建人
	 */
	private String operateUser;

	/**
	 * 创建时间
	 */
	private Date operateDate;

	/**
	 * 备注
	 */
	private String memo;

}
