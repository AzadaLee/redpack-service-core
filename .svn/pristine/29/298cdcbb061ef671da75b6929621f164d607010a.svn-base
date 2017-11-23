/**
 * @(#)SLValidator.java 1.0.0 2014年12月20日 下午2:33:56
 * <p/>
 * Copyright © 2014 善林金融.  All rights reserved.
 */

package com.slfinance.redpack.core.extend.validate.validator;

import com.slfinance.redpack.core.extend.validate.ErrorMessage;

import java.util.Set;

/**
 * 参数输入验证器
 *
 * @author zhanghao
 * @version $Revision:1.0.0, $Date: 2014年12月20日 下午2:33:56 $
 */
public interface SLValidator {

	/**
	 * 获取错误信息
	 *
	 * @return
	 */
	String getMessage();

	Set<ErrorMessage> getMessages();

	/**
	 * 是否有错误信息
	 *
	 * @return
	 */
	boolean hasErrors();

	/**
	 * 对value值进行一定的处理
	 *
	 * @return
	 */
	Object getValue(String value);
}
