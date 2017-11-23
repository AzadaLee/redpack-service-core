/**
 * @(#)AspectConfi.java 1.0.0 2015年12月23日 下午3:16:48
 * <p/>
 * Copyright © 2015 善林金融.  All rights reserved.
 */

package com.slfinance.redpack.core.configs;

import com.slfinance.redpack.core.extend.springweb.method.ResponseVoReturnValueAspect;
import com.slfinance.redpack.core.extend.validate.AnnotationValidateAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.slfinance.redpack.core.SystemArchitecture;
import com.slfinance.redpack.core.extend.validation.CustomerizeLocalValidatorFactoryBean;

/**
 * 切面配置
 *
 * @author samson
 * @version $Revision:1.0.0, $Date: 2015年12月23日 下午3:16:48 $
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = false)
public class AspectConfig {

	/**
	 * 项目全局架构切入点统一定义
	 *
	 * @return
	 */
	@Bean
	public SystemArchitecture systemArchitecture() {
		return new SystemArchitecture();
	}

	/**
	 * responseVo封装切面
	 *
	 * @return
	 */
	@Bean
	public ResponseVoReturnValueAspect responseVoReturnValueAspect() {
		return new ResponseVoReturnValueAspect();
	}

	/**
	 * 基于{@code Rules} 自定义注解的aop
	 * 
	 * @author samson
	 * @createTime 2016年4月6日 上午11:27:08
	 * @param customerizeLocalValidatorFactoryBean
	 *            数据校验工厂
	 * @return
	 */
	@Bean
	public AnnotationValidateAspect annotationValidateAspect(CustomerizeLocalValidatorFactoryBean customerizeLocalValidatorFactoryBean) {
		return new AnnotationValidateAspect(customerizeLocalValidatorFactoryBean);
	}

}
