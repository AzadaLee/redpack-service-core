/** 
 * @(#)MessageRepository.java 1.0.0 2016年11月1日 下午5:51:23  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */

package com.slfinance.redpack.core.repositories;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;

import com.slfinance.redpack.core.entities.Message;
import com.slfinance.redpack.core.extend.jpa.page.PageRequestVo;
import com.slfinance.redpack.core.extend.jpa.page.PageResponse;
import com.slfinance.redpack.core.extend.jpa.page.QueryExtend;
import com.slfinance.redpack.core.repositories.base.BaseRepository;

/**
 * 
 * 
 * @author SangLy
 * @version $Revision:1.0.0, $Date: 2016年11月1日 下午5:51:23 $
 */
public interface MessageRepository extends BaseRepository<Message> {

	/**
	 * 
	 * 根据是否已读统计个数
	 * 
	 * 1:已读 0:未读
	 * @author work
	 * @createTime 2016年11月1日 下午6:17:32
	 * @param isRead
	 * @return
	 */
	Long countByCustomerIdAndIsRead(String customerId, int isRead);
	
	/**
	 * app-我的红包预约列表
	 * @author SangLy
	 * @createTime 2016年8月19日 下午4:55:28
	 * @param pageRequest
	 * @return
	 */
	@QueryExtend
	@Query(nativeQuery = true, value = "select created_date,content from rp_t_message where customer_id = :customerId order by is_read asc , created_date desc")
	PageResponse<Map<String, Object>> findMessagesListByUserId(PageRequestVo pageRequest);
	
	List<Message> findByCustomerIdAndIsRead(String customerId,int isRead);
}
