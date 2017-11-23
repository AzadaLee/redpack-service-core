/** 
 * @(#)OrderService.java 1.0.0 2016年11月8日 下午6:29:35  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */

package com.slfinance.redpack.core.services;

import org.springframework.stereotype.Service;

import com.slfinance.redpack.core.entities.Order;
import com.slfinance.redpack.core.repositories.OrderRepository;
import com.slfinance.redpack.core.services.base.BaseService;

/**
 * 
 * 
 * @author SangLy
 * @version $Revision:1.0.0, $Date: 2016年11月8日 下午6:29:35 $
 */
@Service
public class OrderService extends BaseService<Order,OrderRepository>{
	
	public Order findByRelatePrimary(String relatePrimary) {
		return repository.findByRelatePrimary(relatePrimary);
	}
}
