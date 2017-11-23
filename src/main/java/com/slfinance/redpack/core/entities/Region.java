/** 
 * @(#)Region.java 1.0.0 2016年11月1日 下午2:01:28  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */

package com.slfinance.redpack.core.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 
 * 
 * @author SangLy
 * @version $Revision:1.0.0, $Date: 2016年11月1日 下午2:01:28 $
 */
@Entity
@Table(name = "RP_T_REGION")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Region extends com.slfinance.redpack.core.entities.base.Entity {

	private static final long serialVersionUID = 6812757542774982806L;

	private String code;
	private String name;
	private String parentCode;
	private String grade;
}
