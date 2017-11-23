/** 
 * @(#)CodeGeneratorRepositoryCust.java 1.0.0 2016年7月26日 下午5:19:52  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */

package com.slfinance.redpack.core.repositories.custom;

/**
 * 自定义序列的扩展
 * 
 * @author samson
 * @version $Revision:1.0.0, $Date: 2016年7月26日 下午5:19:52 $
 */
public interface CodeGeneratorRepositoryCust {

	/**
	 * 获取指定序列下一个的序列号
	 * 
	 * @author samson
	 * @createTime 2016年7月26日 下午5:20:53
	 * @param sequenceName
	 *            序列名称
	 * @return
	 */
	int getNextVal(String sequenceName);

	/**
	 * 获取指定序列的当前序列号
	 * 
	 * @author samson
	 * @createTime 2016年7月26日 下午5:21:36
	 * @param sequenceName
	 *            序列名称
	 * @return
	 */
	int getCurrentVal(String sequenceName);
}
