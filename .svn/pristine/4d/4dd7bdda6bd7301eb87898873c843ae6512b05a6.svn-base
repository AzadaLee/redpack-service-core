/** 
 * @(#)OrderDetailService.java 1.0.0 2016年11月8日 下午7:14:37  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */

package com.slfinance.redpack.core.services;

import org.springframework.stereotype.Service;

import com.slfinance.redpack.core.constants.enums.OrderDetailCategory;
import com.slfinance.redpack.core.entities.OrderDetail;
import com.slfinance.redpack.core.repositories.OrderDetailRepository;
import com.slfinance.redpack.core.services.base.BaseService;

/**
 * 
 * 
 * @author SangLy
 * @version $Revision:1.0.0, $Date: 2016年11月8日 下午7:14:37 $
 */
@Service
public class OrderDetailService extends BaseService<OrderDetail, OrderDetailRepository> {
	
	public OrderDetail findByOrderIdAndCategory(String orderId,OrderDetailCategory category){
		return repository.findByOrderIdAndCategory(orderId, category);
	}

}
 