/** 
 * @(#)AccountFlow.java 1.0.0 2016年11月1日 下午1:18:07  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */

package com.slfinance.redpack.core.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import com.slfinance.redpack.core.constants.enums.AccountFlowTradeDirection;
import com.slfinance.redpack.core.constants.enums.AccountFlowflowType;
import com.slfinance.redpack.core.entities.base.BaseEntity;

/**
 * 
 * 
 * @author SangLy
 * @version $Revision:1.0.0, $Date: 2016年11月1日 下午1:18:07 $
 */
@Entity
@Table(name = "RP_T_ACCOUNT_FLOW")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class AccountFlow extends BaseEntity {

	private static final long serialVersionUID = -5310000000000000001L;

	/**
	 * 账户ID
	 */
	private String accountId;

	/**
	 * 流水类型
	 */
	@Enumerated(EnumType.STRING)
	private AccountFlowflowType flowType;
	/**
	 * 流水类型描述
	 */
	private String flowTypeDesc;
	/**
	 * 流水编号
	 */
	private String flowCode;

	/**
	 * 交易方向
	 */
	@Enumerated(EnumType.STRING)
	private AccountFlowTradeDirection tradeDirection;

	/**
	 * 交易金额
	 */
	private Double tradeAmount;
	/**
	 * 交易后账户总金额
	 */
	private Double totalAmount;
	/**
	 * 交易后账户可用金额
	 */
	private Double availableAmount;
	/**
	 * 交易后账户冻结金额
	 */
	private Double freezeAmount;
	/**
	 * 关联主键
	 */
	private String relatePrimary;
	/**
	 * 原原始交易编号
	 */
	private String oldFlowCode;

	/**
	 * 
	 * @param accountId
	 *            账户
	 * @param flowCode
	 *            流水编号
	 * @param flowType
	 *            流水类型
	 * @param tradeAmount
	 *            交易金额
	 * @param tradeDirection
	 *            交易方向
	 * @param relatePrimary
	 *            关联主键
	 * @param oldFlowCode
	 *            原流水编号
	 * @param flowTypeDesc
	 *            流水类型描述
	 */
	public AccountFlow(String accountId, String flowCode, AccountFlowflowType flowType, Double tradeAmount, AccountFlowTradeDirection tradeDirection, String relatePrimary, String oldFlowCode, String flowTypeDesc) {
		this.accountId = accountId;
		this.flowCode = flowCode;
		this.flowType = flowType;
		this.tradeAmount = tradeAmount;
		this.tradeDirection = tradeDirection;
		this.relatePrimary = relatePrimary;
		this.oldFlowCode = oldFlowCode;
		this.flowTypeDesc = flowTypeDesc;
	}

	/**
	 * 根据账户设置流水中对应的交易后的相应金额
	 * 
	 * @author samson
	 * @createTime 2016年11月11日 下午4:18:12
	 */
	public void setAccountAmount(Account account) {
		this.totalAmount = account.getTotalAmount();
		this.freezeAmount = account.getFreezeAmount();
		this.availableAmount = account.getAvailableAmount();
	}

}
