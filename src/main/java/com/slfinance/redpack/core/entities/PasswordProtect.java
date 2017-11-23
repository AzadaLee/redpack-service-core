/** 
 * @(#)PasswordProtect.java 1.0.0 2016年11月1日 下午1:57:27  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */

package com.slfinance.redpack.core.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.slfinance.redpack.core.constants.enums.PasswordProtectType;
import com.slfinance.redpack.core.entities.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 
 * 
 * @author SangLy
 * @version $Revision:1.0.0, $Date: 2016年11月1日 下午1:57:27 $
 */
@Entity
@Table(name = "RP_T_PASSWORD_PROTECT")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class PasswordProtect extends BaseEntity {

	private static final long serialVersionUID = 6925757542774982806L;

	private String target;

	/**
	 * 类型
	 */
	@Enumerated(EnumType.STRING)
	private PasswordProtectType type;

}
