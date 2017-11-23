/** 
 * @(#)SMSClientConfig.java 1.0.0 2016年8月11日 下午2:07:13  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */

package com.slfinance.redpack.core.configs;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * 短信服务器配置文件
 * 
 * @author SangLy
 * @version $Revision:1.0.0, $Date: 2016年8月11日 下午2:07:13 $
 */
@Data
@Component
@ConfigurationProperties(prefix = "sms.client")
public class SMSClientConfig {

	/**
	 * 服务器地址
	 */
	private String serverUrl;

	/**
	 * 账号1
	 */
	private String user1;

	/**
	 * 密码1
	 */
	private String pwd1;

	/**
	 * 账号2
	 */
	private String user2;

	/**
	 * 密码2
	 */
	private String pwd2;

	/**
	 * 每个手机每天最多发送手机条数
	 */
	private Long onePhoneMaxSendPerDay;

	/**
	 * 短信内容模板
	 */
	private Map<String, String> contentTemplate;

	/**
	 * 过期时间间隔
	 */
	private int maxInactiveInterval;

}
