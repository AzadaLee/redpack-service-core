/** 
 * @(#)OperateLog.java 1.0.0 2016年7月25日 上午11:17:16  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */

package com.slfinance.redpack.core.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import com.slfinance.redpack.core.constants.enums.LogType;
import com.slfinance.redpack.core.constants.enums.UserType;

/**
 * 操作日志表
 * 
 * @author SangLy
 * @version $Revision:1.0.0, $Date: 2016年7月25日 上午11:17:16 $
 */
@Entity
@Table(name = "RP_T_OPERATE_LOG")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class OperateLog extends com.slfinance.redpack.core.entities.base.Entity {

	private static final long serialVersionUID = -5000000000000000005L;

	/**
	 * 用户类型
	 */
	@Enumerated(EnumType.STRING)
	private UserType userType;

	/**
	 * 日志类型
	 */
	@Enumerated(EnumType.STRING)
	private LogType type;

	/**
	 * 日志内容
	 */
	private String content;

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
