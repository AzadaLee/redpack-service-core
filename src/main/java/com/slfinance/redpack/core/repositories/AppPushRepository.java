/** 
 * @(#)AppVersionRepository.java 1.0.0 2016年8月17日 下午2:45:10  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */

package com.slfinance.redpack.core.repositories;

import java.util.List;

import com.slfinance.redpack.core.entities.AppPush;
import com.slfinance.redpack.core.repositories.base.BaseRepository;

/**
 * 
 * 
 * @author samson
 * @version $Revision:1.0.0, $Date: 2016年8月17日 下午2:45:10 $
 */
public interface AppPushRepository extends BaseRepository<AppPush> {

	List<AppPush> findAllByTarget(String target);
}
