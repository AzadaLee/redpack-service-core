/** 
 * @(#)AppVersion.java 1.0.0 2016年8月17日 下午2:37:50  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */

package com.slfinance.redpack.core.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import com.slfinance.redpack.core.constants.enums.AppVersionStatus;
import com.slfinance.redpack.core.constants.enums.Platform;
import com.slfinance.redpack.core.entities.base.BaseEntity;

/**
 * 
 * 
 * @author taoxm
 * @version $Revision:1.0.0, $Date: 2016年8月17日 下午2:37:50 $
 */
@Entity
@Table(name = "RP_T_APP_VERSION")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class AppVersion extends BaseEntity {
	private static final long serialVersionUID = -8730009148148369775L;

	/**
	 * 平台
	 */
	@Enumerated(EnumType.STRING)
	private Platform platform;

	/**
	 * APP版本号
	 */
	private String appVersion;

	/**
	 * 版本更新内容
	 */
	private String updateContent;

	/**
	 * 最新版本下载地址
	 */
	@Column(name = "DOWNLOAD_URL")
	private String downloadURL;

	/**
	 * 状态
	 */
	@Enumerated(EnumType.STRING)
	private AppVersionStatus status = AppVersionStatus.新建;

	/**
	 * 发布人
	 */
	private String releasedUser;

	/**
	 * 发布时间
	 */
	private Date releasedDate;

}
