/** 
 * @(#)Order.java 1.0.0 2016年11月1日 下午1:53:00  
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

import com.slfinance.redpack.core.constants.enums.OrderStatus;
import com.slfinance.redpack.core.constants.enums.OrderType;
import com.slfinance.redpack.core.entities.base.BaseEntity;

/**
 * 
 * 
 * @author SangLy
 * @version $Revision:1.0.0, $Date: 2016年11月1日 下午1:53:00 $
 */
@Entity
@Table(name = "RP_T_ORDER")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Order extends BaseEntity {

	private static final long serialVersionUID = 6625757542774982806L;

	private String customerId;
	private String relatePrimary;
	private String thirdOrderNo;
	private Double orderAmount;
	private String subject;
	/**
	 * 订单类型
	 */
	@Enumerated(EnumType.STRING)
	private OrderType orderType;

	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus = OrderStatus.正常;

}
