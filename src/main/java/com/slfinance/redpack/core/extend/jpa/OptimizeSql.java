/**
 * @(#)OptimizeSql.java 1.0.0 2016年1月8日 上午11:43:02
 * <p/>
 * Copyright © 2016 善林金融.  All rights reserved.
 */

package com.slfinance.redpack.core.extend.jpa;

import java.util.Map;

import com.slfinance.redpack.core.extend.jpa.page.PageRequestVo;

/**
 * sql优化
 *
 * @author samson
 * @version $Revision:1.0.0, $Date: 2016年1月8日 上午11:43:02 $
 */
public interface OptimizeSql {

	/**
	 * 获取优化后的sql
	 *
	 * @return
	 */
	String getSql();

	/**
	 * 已Map形式获取优化后的参数
	 *
	 * @return
	 */
	Map<String, Object> getParameters();

	/**
	 * 获取原始参数数组
	 *
	 * @return
	 */
	Object[] getArgs();

	/**
	 * 获取分页的参数
	 *
	 * @return
	 */
	PageRequestVo getPageArg();

}
