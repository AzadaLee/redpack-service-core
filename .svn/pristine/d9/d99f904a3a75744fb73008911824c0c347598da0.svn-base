/** 
 * @(#)DistributeRepository.java 1.0.0 2016年11月2日 上午11:30:27  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */

package com.slfinance.redpack.core.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.slfinance.redpack.core.entities.Distribute;
import com.slfinance.redpack.core.extend.jpa.page.QueryExtend;
import com.slfinance.redpack.core.repositories.base.BaseRepository;

/**
 * 
 * 
 * @author SangLy
 * @version $Revision:1.0.0, $Date: 2016年11月2日 上午11:30:27 $
 */
public interface DistributeRepository extends BaseRepository<Distribute> {

	/**
	 * 根据红包id查询最近一条还未分配的记录
	 * @author work
	 * @createTime 2016年11月14日 下午3:02:57
	 * @param createdDate
	 * @param redpackId
	 * @return
	 */
	@QueryExtend
	@Query(nativeQuery = true, value = "select * from (select di.* ,rownum as rn from rp_t_distribute di where to_date(to_char(di.created_date,'yyyy-MM-dd'),'yyyy-MM-dd') = :createdDate and di.assignee is null and di.redpack_id = :redpackId  order by di.ordered desc) where rn =1")
	Distribute findByAssigneeIsNullAndRedpackIdAndCreatedDateGreaterThanEqualOrderByOrderedAsc(@Param("createdDate")Date createdDate, @Param("redpackId")String redpackId);
	
	List<Distribute> findByAssigneeAndRedpackId(String assignee,String redpackId);
}
