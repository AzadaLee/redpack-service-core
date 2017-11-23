/** 
 * @(#)SqlArrayWrapper.java 1.0.0 2016年6月28日 下午3:49:16  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */

package com.slfinance.redpack.core.extend.jpa.search;

import lombok.Getter;

/**
 * 
 * 
 * @author samson
 * @version $Revision:1.0.0, $Date: 2016年6月28日 下午3:49:16 $
 */
@Getter
public class SqlArrayWrapper {
	private String sql;
	private Object[] parameters;

	public SqlArrayWrapper(String sql, Object[] parameters) {
		this.sql = sql;
		this.parameters = parameters;
	}
}
