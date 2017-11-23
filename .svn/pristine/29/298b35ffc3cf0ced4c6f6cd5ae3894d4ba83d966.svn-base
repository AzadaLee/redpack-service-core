/** 
 * @(#)ErrorCodeConfig.java 1.0.0 2016年3月26日 下午3:56:47  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */

package com.slfinance.redpack.core.configs;

import java.io.InputStreamReader;
import java.util.Properties;

import lombok.Data;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.slfinance.redpack.core.response.ResponseCode;

/**
 * 错误响应码配置
 * 
 * @author samson
 * @version $Revision:1.0.0, $Date: 2016年3月26日 下午3:56:47 $
 */
@Data
@Configuration
@ConfigurationProperties("error.code")
public class ErrorCodeConfig implements InitializingBean {
	private String fileName;

	@Override
	public void afterPropertiesSet() throws Exception {
		String file = "/" + fileName + ".properties";
		Properties codeMessage = new Properties();
		codeMessage.load(new InputStreamReader(this.getClass().getResourceAsStream(file), "UTF-8"));
		for (Object key : codeMessage.keySet()) {
			ResponseCode.CODE_2_DEFAULTMESSAGE.put(key.toString(), ObjectUtils.toString(codeMessage.get(key)));
		}
	}
}
