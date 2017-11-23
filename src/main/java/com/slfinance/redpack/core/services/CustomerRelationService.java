/** 
 * @(#)CustomerRelationService.java 1.0.0 2016年8月17日 下午5:27:30  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */

package com.slfinance.redpack.core.services;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.slfinance.redpack.common.utils.DateUtil;
import com.slfinance.redpack.core.constants.TableConstant;
import com.slfinance.redpack.core.constants.enums.CustomerRelationType;
import com.slfinance.redpack.core.entities.CustomerRelation;
import com.slfinance.redpack.core.repositories.CustomerRelationRepository;
import com.slfinance.redpack.core.services.base.BaseService;

/**
 * 
 * 
 * @author SangLy
 * @version $Revision:1.0.0, $Date: 2016年8月17日 下午5:27:30 $
 */
@Service
public class CustomerRelationService extends BaseService <CustomerRelation, CustomerRelationRepository> {
	
	
	
	/**
	 * 根据条件查询记录
	 * 
	 * @author SangLy
	 * @createTime 2016年8月17日 下午6:44:21
	 * @param day
	 *            例如查询当天的分享任务 ，格式去掉时分秒：2016-8-17 00:00:00
	 * @param customerId
	 *            客户ID
	 * @param relateTable
	 *            关联表
	 * @param relatePrimary
	 *            关联表主键
	 * @param type
	 *            关联类型
	 * @return
	 */
	public List<Map<String, Object>> findListCustomerIdAndRelateTableAndRelatePrimaryAndTypeAndDay(Date systemTime,String customerId,String relateTable,String relatePrimary,CustomerRelationType type){
		Date day = DateUtil.weedDayBelow(systemTime);
		return repository.findListCustomerIdAndRelateTableAndRelatePrimaryAndTypeAndDay(day,customerId, relateTable, relatePrimary, type.toString());
	}
	
	/**
	 * 查询邀请人记录
	 * 
	 * @author SangLy
	 * @createTime 2016年8月17日 下午6:44:21
	 * @param day
	 *           格式去掉时分秒：2016-8-17 00:00:00
	 * @param customerId
	 *            客户ID
	 * @param type
	 *            关联类型
	 * @return
	 */
	public List<Map<String, Object>> findListCustomerIdAndRelateTableAndTypeAndDay(Date systemTime,String customerId,CustomerRelationType type){
		Date day = DateUtil.weedDayBelow(systemTime);
		return repository.findListCustomerIdAndRelateTableAndTypeAndDay(day,customerId,type.toString());
	}
	
	/**
	 * 查询邀请人注册日志
	 * 
	 * @author SangLy
	 * @createTime 2016年8月17日 下午6:44:21
	 * @param day
	 *           格式去掉时分秒：2016-8-17 00:00:00
	 * @param customerId
	 *            客户ID
	 * @param type
	 *            关联类型
	 * @return
	 */
	public List<Map<String, Object>> findListRelatePrimaryAndRelateTableAndTypeAndDay(Date systemTime,String customerId,CustomerRelationType type){
		Date day = DateUtil.weedDayBelow(systemTime);
		return repository.findListRelatePrimaryAndRelateTableAndTypeAndDay(day,customerId,type.toString());
	}
	
	/**
	 * 保存中间表
	 * 
	 * @author SangLy
	 * @createTime 2016年8月19日 下午5:38:41
	 * @param customerId
	 * @param relateTable
	 * @param relatePrimary
	 * @param type
	 */
	@Transactional
	public void saveCustomerRelation(String customerId, String relateTable, String relatePrimary, CustomerRelationType type) {
		CustomerRelation customerRelation = new CustomerRelation();
		customerRelation.setCustomerId(customerId);
		customerRelation.setRelateTable(relateTable);
		customerRelation.setRelatePrimary(relatePrimary);
		customerRelation.setType(type);
		repository.save(customerRelation);
	}

	/**
	 * app-红包取消预约
	 * @author SangLy
	 * @createTime 2016年8月19日 下午6:14:34
	 * @param id
	 */
	@Transactional
	public void appCancelAppointment(String id,String coustomerId){
		List<Map<String, Object>> hongbaodingyueList = findListCustomerIdAndRelateTableAndRelatePrimaryAndTypeAndDay(new Date(), coustomerId, TableConstant.T_REDPACK, id, CustomerRelationType.红包订阅);
		if(hongbaodingyueList.size() != 0){
			repository.delete(MapUtils.getString(hongbaodingyueList.get(0), "id"));
		}
	}
}
