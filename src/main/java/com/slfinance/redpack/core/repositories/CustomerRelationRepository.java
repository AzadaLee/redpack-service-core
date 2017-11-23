/** 
 * @(#)CustomerRelationRepository.java 1.0.0 2016年8月15日 下午3:45:29  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */

package com.slfinance.redpack.core.repositories;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.slfinance.redpack.core.entities.CustomerRelation;
import com.slfinance.redpack.core.extend.jpa.page.QueryExtend;
import com.slfinance.redpack.core.repositories.base.BaseRepository;

/**
 * 
 * 
 * @author SangLy
 * @version $Revision:1.0.0, $Date: 2016年8月15日 下午3:45:29 $
 */
public interface CustomerRelationRepository extends BaseRepository<CustomerRelation> {
	
	/**
	 * 根据条件查询记录
	 * @author SangLy
	 * @createTime 2016年8月17日 下午6:44:21
	 * @param day
	 * 		  例如查询当天的分享任务 ,格式去掉时分秒例如时间格式为：2016-8-17 00:00:00
	 * @param customerId
	 * 		 客户ID
	 * @param relateTable
	 * 		关联表
	 * @param relatePrimary
	 * 		关联表主键
	 * @param type
	 * 		关联类型
	 * @return
	 */
	@QueryExtend
	@Query(nativeQuery=true , value="select * from rp_t_customer_relation where to_date(to_char(created_date,'yyyy-MM-dd'),'yyyy-MM-dd') = :day and customer_id = :customerId and relate_table =:relateTable and relate_primary = :relatePrimary and type = :type")
	List<Map<String, Object>> findListCustomerIdAndRelateTableAndRelatePrimaryAndTypeAndDay(@Param("day")Date day, @Param("customerId")String customerId,@Param("relateTable")String relateTable,@Param("relatePrimary")String relatePrimary,@Param("type")String type);

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
	@QueryExtend
	@Query(nativeQuery=true , value="select * from rp_t_customer_relation where to_date(to_char(created_date,'yyyy-MM-dd'),'yyyy-MM-dd') = :day and customer_id = :customerId and relate_table = 'RP_T_CUSTOMER' and type = :type")
	List<Map<String, Object>> findListCustomerIdAndRelateTableAndTypeAndDay(@Param("day")Date day, @Param("customerId")String customerId,@Param("type")String type);
	
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
	@QueryExtend
	@Query(nativeQuery=true , value="select * from rp_t_customer_relation where to_date(to_char(created_date,'yyyy-MM-dd'),'yyyy-MM-dd') = :day and relate_table = 'RP_T_CUSTOMER' and relate_primary = :relatePrimary and type = :type")
	List<Map<String, Object>> findListRelatePrimaryAndRelateTableAndTypeAndDay(@Param("day")Date day, @Param("relatePrimary")String relatePrimary ,@Param("type")String type);
	
}
