/** 
 * @(#)AsyncConfig.java 1.0.0 2016年8月15日 下午4:33:05  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */ 

package com.slfinance.redpack.core.configs;

/**   
 * 
 *  
 * @author  SangLy
 * @version $Revision:1.0.0, $Date: 2016年8月15日 下午4:33:05 $ 
 */
import java.util.concurrent.Executor;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.slfinance.redpack.core.exception.SLAsyncUncaughtExceptionHandler;
import com.slfinance.redpack.core.sms.ISmsService;
import com.slfinance.redpack.core.sms.impl.LocalSmsServiceImpl;

/**   
 * 异步任务的线程池配置和服务接口的初始化
 * @author  SangLy
 * @version $Revision:1.0.0, $Date: 2016年5月25日 下午1:25:06 $ 
 */
@Configuration
@EnableAsync
@ConfigurationProperties(prefix = "thread.pool")
public class AsyncConfig implements AsyncConfigurer{

	private int corePoolSize;
	private int maxPoolSize;
	private int queueCapacity;
	
	/**
	 * 发送短信服务接口的初始化
	 * @author LiYing
	 * @createTime 2016年5月25日 下午2:54:33
	 * @return
	 */
	@Bean
	public ISmsService localSmsService() {
	     return new LocalSmsServiceImpl();
	}
	
	/**
	 * 配置异步任务的线程池
	 */
	@Override
	public Executor getAsyncExecutor() {
		  ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
          executor.setCorePoolSize(corePoolSize);
          executor.setMaxPoolSize(maxPoolSize);
          executor.setQueueCapacity(queueCapacity);
          executor.initialize();
          return executor;
	}

	/**
	 * 异步异常的处理
	 */
	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		return new SLAsyncUncaughtExceptionHandler();
	}

	public void setCorePoolSize(int corePoolSize) {
		this.corePoolSize = corePoolSize;
	}

	public void setMaxPoolSize(int maxPoolSize) {
		this.maxPoolSize = maxPoolSize;
	}

	public void setQueueCapacity(int queueCapacity) {
		this.queueCapacity = queueCapacity;
	}
}