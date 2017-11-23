/** 
 * @(#)OrderDetail.java 1.0.0 2016年11月8日 下午6:57:08  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */

package com.slfinance.redpack.core.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.slfinance.redpack.core.constants.enums.OrderDetailCategory;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 
 * 
 * @author SangLy
 * @version $Revision:1.0.0, $Date: 2016年11月8日 下午6:57:08 $
 */
@Entity
@Table(name = "RP_T_ORDER_DETAIL")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class OrderDetail extends com.slfinance.redpack.core.entities.base.Entity {

	private static final long serialVersionUID = 6023957542774982806L;

	/**
	 * 订单ID
	 */
	private String orderId;

	/**
	 * 类目
	 */
	@Enumerated(EnumType.STRING)
	private OrderDetailCategory category;

	/**
	 * 金额
	 */
	private Double amount;

}
