/** 
 * @(#)PageResponse.java 1.0.0 2015年12月19日 下午5:09:28  
 *  
 * Copyright © 2015 善林金融.  All rights reserved.  
 */

package com.slfinance.redpack.core.extend.jpa.page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;

import org.springframework.data.domain.Page;

/**
 * 分页相应对象
 * 
 * @author samson
 * @version $Revision:1.0.0, $Date: 2015年12月19日 下午5:09:28 $
 */
@Data
@AllArgsConstructor
public class PageResponse<T> {

	protected int total;
	protected List<T> data;
	protected Map<String, Object> extraData = new HashMap<String, Object>();

	public PageResponse(int total, List<T> data) {
		this.total = total;
		this.data = data;
	}

	public PageResponse(Page<T> page) {
		this((int) page.getTotalElements(), page.getContent());
	}
}
