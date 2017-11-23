/** 
 * @(#)ReturnMapping.java 1.0.0 2016年1月4日 下午3:17:36  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */

package com.slfinance.redpack.core.extend.jpa.page;

import java.lang.annotation.*;

/**
 * 返回{@code Map}结果key映射
 * 
 * @author samson
 * @version $Revision:1.0.0, $Date: 2016年1月4日 下午3:17:36 $
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ReturnMapping {

	/**
	 * 返回结果的key
	 * 
	 * @return
	 */
	String from();

	/**
	 * 返回结果的key对应的需要转换的key
	 * 
	 * @return
	 */
	String to();
}
