/** 
 * @(#)SmsMessage.java 1.0.0 2016年7月25日 下午2:07:46  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */

package com.slfinance.redpack.core.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.slfinance.redpack.core.constants.enums.SmsMessageReturnStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 短信表
 * 
 * @author SangLy
 * @version $Revision:1.0.0, $Date: 2016年7月25日 下午2:07:46 $
 */
@Entity
@Table(name = "RP_T_SMS_MESSAGE")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class SMSMessage extends com.slfinance.redpack.core.entities.base.Entity {

	private static final long serialVersionUID = -5000000000000000012L;

	/**
	 * 手机号
	 */
	private String mobile;

	/**
	 * 发送内容
	 */
	private String sendContent;

	/**
	 * 发送时间
	 */
	private Date sendTime;

	/**
	 * 扩展码
	 */
	private String extNo;

	/**
	 * 返回状态
	 */
	@Enumerated(EnumType.STRING)
	private SmsMessageReturnStatus returnStatus;

	/**
	 * 返回信息
	 */
	private String returnMessage;

	/**
	 * 本次序列号ID
	 */
	private String taskId;

	/**
	 * 成功次数
	 */
	private Integer successCounts = 0;

	/**
	 * 创建时间
	 */
	private Date createdDate;

	/**
	 * 备注
	 */
	private String memo;

}
