/** 
 * @(#)RegionRepository.java 1.0.0 2016年11月3日 下午4:19:29  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */

package com.slfinance.redpack.core.repositories;

import java.util.List;

import com.slfinance.redpack.core.entities.Region;
import com.slfinance.redpack.core.repositories.base.BaseRepository;

/**
 * 
 * 
 * @author SangLY
 * @version $Revision:1.0.0, $Date: 2016年11月3日 下午4:19:29 $
 */
public interface RegionRepository extends BaseRepository<Region> {

	List<Region> findByParentCode(String parentCode);
	
	Region  findByCode(String code);

}
