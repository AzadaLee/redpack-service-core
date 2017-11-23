/**
 * @(#)Redpack.java 1.0.0 2016年7月25日 上午11:34:31
 * <p/>
 * Copyright © 2016 善林金融.  All rights reserved.
 */

package com.slfinance.redpack.core.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import com.slfinance.redpack.core.constants.RedpackRecordStatus;
import com.slfinance.redpack.core.constants.enums.RedpackStatus;
import com.slfinance.redpack.core.constants.enums.RedpackType;
import com.slfinance.redpack.core.constants.enums.UserType;
import com.slfinance.redpack.core.entities.base.BaseEntity;

/**
 * 红包表
 *
 * @author SangLy
 * @version $Revision:1.0.0, $Date: 2016年7月25日 上午11:34:31 $
 */
@Entity
@Table(name = "RP_T_REDPACK")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class RedPack extends BaseEntity {

	private static final long serialVersionUID = -5000000000000000006L;

	private String advertisementId;

	/**
	 * 红包类型
	 */
	@Enumerated(EnumType.STRING)
	private RedpackType redpackType;

	private String redpackCode;
	private Date timePoint;
	private Double bigAmount;
	private Integer bigCount;
	private Double smallAmount;
	private Integer smallCount;
	private Double amount;
	
	/**
	 * 排序,由时任务生成
	 */
	private Integer ordered;
	
	/*
	 * 审核时间
	 */
	private Date auditDate;
	
	/**
	 * 审核人
	 */
	private Date auditUser;

	/**
	 * 红包状态
	 */
	@Enumerated(EnumType.STRING)
	private RedpackStatus status;

	/**
	 * 用户类型
	 */
	@Enumerated(EnumType.STRING)
	private UserType userType;
	
	/**
	 * 主要用于app用户删除红包订单
	 */
	@Enumerated(EnumType.STRING)
	private RedpackRecordStatus recordStatus = RedpackRecordStatus.正常;
	 

}
