/** 
 * @(#)AdvertisementStatisticsJob.java 1.0.0 2016年9月1日 下午8:03:56  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */

package com.slfinance.redpack.core.jobs;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.slfinance.redpack.common.utils.DateUtil;
import com.slfinance.redpack.common.utils.MapUtil;
import com.slfinance.redpack.core.constants.enums.LogType;
import com.slfinance.redpack.core.entities.Advertisement;
import com.slfinance.redpack.core.extend.schedule.ScheduleJob;
import com.slfinance.redpack.core.services.AdvertisementService;
import com.slfinance.redpack.core.services.OperateLogService;
import com.slfinance.redpack.core.services.RedPackService;

/**
 * 广告的点击和分享统计(每天凌晨05分执行)
 * 
 * @author samson
 * @version $Revision:1.0.0, $Date: 2016年9月1日 下午8:03:56 $
 */
@Component
@ScheduleJob(cron = "0 5 0 * * ?")
public class AdvertisementStatisticsJob extends AbstractJob {

	@Autowired
	OperateLogService operateLogService;
	@Autowired
	RedPackService redPackService;
	@Autowired
	AdvertisementService advertisementService;

	@Override
	public void execute() {
		Date now = new Date();
		Date todayStart = DateUtil.weedDayBelow(now);
		Date todayEnd = new Date(DateUtil.addDays(todayStart, 1).getTime() - DateUtil.MILLIS_PER_SECOND);
		List<Map<String, Object>> click = operateLogService.statisticsAdvertisement(Lists.newArrayList(LogType.广告点击), todayStart, todayEnd);
		List<Map<String, Object>> share = operateLogService.statisticsAdvertisement(Lists.newArrayList(LogType.广告分享), todayStart, todayEnd);

		int count = 0;
		for (Map<String, Object> data : click) {
			String id = MapUtil.getString(data, "content");
			count = MapUtil.getIntValue(data, "count");
			if (count > 0) {
				Advertisement advertisement = advertisementService.findOne(id);
				if (advertisement != null) {
					advertisement.setHitsCount((int) ((advertisement.getHitsCount() == null ? 0 : advertisement.getHitsCount()) + count));
					advertisementService.save(advertisement);
				}
			}
		}
		for (Map<String, Object> data : share) {
			String id = MapUtil.getString(data, "content");
			count = MapUtil.getIntValue(data, "count");
			if (count > 0) {
				Advertisement advertisement = advertisementService.findOne(id);
				if (advertisement != null) {
					advertisement.setSharedCount((int) ((advertisement.getSharedCount() == null ? 0 : advertisement.getSharedCount()) + count));
					advertisementService.save(advertisement);
				}
			}
		}
	}

	@Override
	public String getJobName() {
		return "广告点击和分享每日统计";
	}

}
