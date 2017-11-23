/** 
 * @(#)AppPush.java 1.0.0 2016年8月30日 下午2:50:14  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */

package com.slfinance.redpack.core.entities;

import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import com.slfinance.redpack.core.constants.enums.PushType;
import com.slfinance.redpack.core.entities.base.Entity;

/**
 * 
 * app推送
 * 
 * @author samson
 * @version $Revision:1.0.0, $Date: 2016年8月30日 下午2:50:14 $
 */
@javax.persistence.Entity
@Table(name = "RP_T_APP_PUSH")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class AppPush extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1200849486049171338L;

	/** 推送目标 */
	private String target;

	/** 推送类型 */
	@Enumerated(EnumType.STRING)
	private PushType pushType;

	/** 推送内容(json串) */
	private String content;

	/** 推送结果(json串) */
	private String pushResult;

	/** 第三方ID */
	private String thirdPartyId;

	/** 响应码 */
	private String responseCode;

	/**
	 * 创建时间
	 */
	private Date createdDate;

	/** 推送时间 */
	private Date pushTime;

	/** 备注 */
	private String memo;

	public AppPush(String target, PushType pushType, String content, String pushResult, String thirdPartyId, String responseCode, Date createdDate, Date pushTime) {
		this.target = target;
		this.pushType = pushType;
		this.content = content;
		this.pushResult = pushResult;
		this.thirdPartyId = thirdPartyId;
		this.responseCode = responseCode;
		this.createdDate = createdDate;
		this.pushTime = pushTime;
	}
}
