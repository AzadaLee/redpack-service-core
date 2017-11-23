/** 
 * @(#)OperateLogService.java 1.0.0 2016年7月26日 上午9:57:33  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */

package com.slfinance.redpack.core.services;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.slfinance.redpack.core.constants.enums.LogType;
import com.slfinance.redpack.core.entities.OperateLog;
import com.slfinance.redpack.core.repositories.OperateLogRepository;
import com.slfinance.redpack.core.services.base.BaseService;
import com.slfinance.redpack.core.utils.SpringUtils;

/**
 * 操作日志
 * 
 * @author SangLy
 * @version $Revision:1.0.0, $Date: 2016年7月26日 上午9:57:33 $
 */
@Service
public class OperateLogService extends BaseService<OperateLog, OperateLogRepository> {
	@Autowired
	SpringUtils springUtils;

	ExecutorService executorService = Executors.newFixedThreadPool(10);

	/**
	 * 异步保存日志
	 * 
	 * @author samson
	 * @createTime 2016年8月18日 下午4:59:07
	 * @param operateLog
	 */
	public void saveAsync(final OperateLog operateLog) {
		executorService.execute(new Runnable() {
			@Override
			public void run() {
				SpringUtils.getBean(OperateLogService.class).save(operateLog);
			}
		});
	}

	public long countByTypeAndContentAndOperateDateBetween(LogType logType, String content, Date operateStartDate, Date operateEndDate) {
		return repository.countByTypeAndContentAndOperateDateBetween(logType, content, operateStartDate, operateEndDate);
	}

	public List<Map<String, Object>> statisticsAdvertisement(List<LogType> logTypes, Date operateStartDate, Date operateEndDate) {
		return repository.statisticsAdvertisement(Lists.transform(logTypes, new Function<LogType, String>() {
			@Override
			public String apply(LogType logType) {
				return logType.name();
			}
		}), operateStartDate, operateEndDate);
	}
}
