package com.slfinance.redpack.core.entities;

import java.util.Date;

import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.slfinance.redpack.core.constants.enums.CustomerRelationType;
import com.slfinance.redpack.core.entities.base.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 客户属性表 Created by samson on 2016/8/12.
 */
@javax.persistence.Entity
@Table(name = "RP_T_CUSTOMER_RELATION")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class CustomerRelation extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4154314731242148927L;
	/**
	 * 邀请人
	 */
	private String customerId;
	/**
	 * 关联表
	 */
	private String relateTable;
	/**
	 * 关联表主键
	 */
	private String relatePrimary;

	/**
	 * 类型
	 */
	@Enumerated(EnumType.STRING)
	private CustomerRelationType type;

	/**
	 * 创建时间
	 */
	@CreatedDate
	private Date createdDate;

}
