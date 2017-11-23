/** 
 * @(#)OperateLogRepository.java 1.0.0 2016年7月25日 下午4:49:53  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */

package com.slfinance.redpack.core.repositories;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.slfinance.redpack.core.constants.enums.LogType;
import com.slfinance.redpack.core.entities.OperateLog;
import com.slfinance.redpack.core.extend.jpa.page.QueryExtend;
import com.slfinance.redpack.core.repositories.base.BaseRepository;

/**
 * 
 * 
 * @author SangLy
 * @version $Revision:1.0.0, $Date: 2016年7月25日 下午4:49:53 $
 */
public interface OperateLogRepository extends BaseRepository<OperateLog> {

	public long countByTypeAndContentAndOperateDateBetween(LogType logType, String content, Date operateStartDate, Date operateEndDate);

	@QueryExtend
	@Query(nativeQuery = true, value = "SELECT OL.CONTENT,count(OL.CONTENT) as count FROM RP_T_OPERATE_LOG ol WHERE ol.type in (:logTypes) AND OL.OPERATE_DATE BETWEEN :operateStartDate AND :operateEndDate GROUP BY OL.content")
	public List<Map<String, Object>> statisticsAdvertisement(@Param("logTypes") List<String> logTypes, @Param("operateStartDate") Date operateStartDate, @Param("operateEndDate") Date operateEndDate);
}
