/** 
 * @(#)FileRelation.java 1.0.0 2016年11月1日 下午1:41:26  
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
 * @version $Revision:1.0.0, $Date: 2016年11月1日 下午1:41:26 $
 */
@Entity
@Table(name = "RP_T_FILE_RELATION")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class FileRelation extends com.slfinance.redpack.core.entities.base.Entity {

	private static final long serialVersionUID = 6905637542774982806L;

	private String relateTable;
	private String relatePrimary;
	private String fileId;

}
