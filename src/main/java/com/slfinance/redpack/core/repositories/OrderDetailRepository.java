/** 
 * @(#)OrderDetailRepository.java 1.0.0 2016年11月8日 下午7:12:48  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */

package com.slfinance.redpack.core.repositories;

import com.slfinance.redpack.core.constants.enums.OrderDetailCategory;
import com.slfinance.redpack.core.entities.OrderDetail;
import com.slfinance.redpack.core.repositories.base.BaseRepository;

/**
 * 
 * 
 * @author SangLy
 * @version $Revision:1.0.0, $Date: 2016年11月8日 下午7:12:48 $
 */
public interface OrderDetailRepository extends BaseRepository<OrderDetail> {

	OrderDetail findByOrderIdAndCategory(String orderId,OrderDetailCategory category);
}
