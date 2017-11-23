/** 
 * @(#)JPushConfig.java 1.0.0 2016年8月18日 下午2:42:43  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */

package com.slfinance.redpack.core.configs;

import java.util.Map;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * app推送配置
 * 
 * @author samson
 * @version $Revision:1.0.0, $Date: 2016年8月18日 下午2:42:43 $
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "jpush")
public class AppPushConfig {

	private String appKey;
	private String masterSecret;
	/**
	 * ios是否是生产环境
	 */
	private boolean apnsProduction;
	/**
	 * 通知
	 */
	private Map<String, Map<String, String>> notification;
}
