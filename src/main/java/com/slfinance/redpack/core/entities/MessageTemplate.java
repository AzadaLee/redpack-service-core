/** 
 * @(#)MessageTemplate.java 1.0.0 2016年11月1日 下午1:47:57  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */

package com.slfinance.redpack.core.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.slfinance.redpack.core.constants.enums.MessageTemplateMessageType;
import com.slfinance.redpack.core.entities.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 
 * 
 * @author work
 * @version $Revision:1.0.0, $Date: 2016年11月1日 下午1:47:57 $
 */
@Entity
@Table(name = "RP_T_MESSAGE_TEMPLATE")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class MessageTemplate extends BaseEntity {

	private static final long serialVersionUID = 6125737542774982806L;

	/**
	 * 站内信类型
	 */
	@Enumerated(EnumType.STRING)
	private MessageTemplateMessageType messageType;
	private String content;
	private String formatExplain;
}
