/** 
 * @(#)AppVersionRepository.java 1.0.0 2016年8月17日 下午2:45:10  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */

package com.slfinance.redpack.core.repositories;

import java.util.Map;

import org.springframework.data.jpa.repository.Query;

import com.slfinance.redpack.core.constants.enums.AppVersionStatus;
import com.slfinance.redpack.core.constants.enums.Platform;
import com.slfinance.redpack.core.entities.AppVersion;
import com.slfinance.redpack.core.extend.jpa.page.PageRequestVo;
import com.slfinance.redpack.core.extend.jpa.page.PageResponse;
import com.slfinance.redpack.core.extend.jpa.page.QueryExtend;
import com.slfinance.redpack.core.extend.jpa.page.ReturnMapping;
import com.slfinance.redpack.core.repositories.base.BaseRepository;

/**
 * 
 * 
 * @author taoxm
 * @version $Revision:1.0.0, $Date: 2016年8月17日 下午2:45:10 $
 */
public interface AppVersionRepository extends BaseRepository<AppVersion> {

	/**
	 * 查询app版本列表
	 * 
	 * @author taoxm
	 * @createTime 2016年8月17日 下午3:28:47
	 * @param pageRequest
	 * @return
	 */
	@QueryExtend({ @ReturnMapping(from = "DOWNLOAD_URL", to = "downloadURL"),@ReturnMapping(from = "NAME", to = "releasedUser") })
	@Query(nativeQuery = true, value = "select t.id,t.platform,t.app_version,t.update_content,t.download_url,t.status,t.released_date,u.name from rp_t_app_version t left join rp_t_user u on t.released_user = u.id where ?(t.platform = :platform)? and ?(trunc(t.released_date)) >= to_date( :startReleasedDate , 'yyyy-mm-dd')? and ?(trunc(t.released_date)) <= to_date( :endReleasedDate , 'yyyy-mm-dd')? order by t.created_date desc,t.released_date desc")
	public PageResponse<Map<String, Object>> findAllPage(PageRequestVo pageRequest);

	/**
	 * 更具平台和状态 查找最近一条记录
	 * 
	 * @author SangLy
	 * @createTime 2016年8月19日 下午1:29:08
	 * @param platform
	 * @param status
	 * @return
	 */
	AppVersion findFirstByPlatformAndStatusOrderByReleasedDateDesc(Platform platform, AppVersionStatus status);

}
