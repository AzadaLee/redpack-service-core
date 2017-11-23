/** 
 * @(#)Withdraw.java 1.0.0 2016年11月2日 下午4:33:40  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */

package com.slfinance.redpack.core.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.slfinance.redpack.core.constants.enums.WithdrawStatus;
import com.slfinance.redpack.core.entities.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 
 * 
 * @author SangLy
 * @version $Revision:1.0.0, $Date: 2016年11月2日 下午4:33:40 $
 */
@Entity
@Table(name = "RP_T_WITHDRAW")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Withdraw extends BaseEntity {

	private static final long serialVersionUID = -5900020000000000008L;

	private String customerId;
	private String withdrawCode;
	private String bankId;
	private Double amount;
	private String flowId;
	
	@Enumerated(EnumType.STRING)
	private WithdrawStatus status;

}
