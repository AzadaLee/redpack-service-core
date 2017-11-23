/**
 * @(#)ResponseCode.java 1.0.0 2015年12月12日 下午3:41:41
 * <p/>
 * Copyright © 2015 善林金融.  All rights reserved.
 */

package com.slfinance.redpack.core.response;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * 响应码
 * <p/>
 * 
 * <pre>
 * 错误码规则：
 * 
 * 	1.所有错误码为6位数字字符串，字符串前2位为错误码所属的模块，系统通用以00开始，比如，成功：000000
 * 
 * 	2.认证管理错误码以01开始，后面4位错误具体原因，
 * 		如：010001，
 * 		01--表示认证服务
 * 		00001--标识具体的错误信息
 * 	
 *           
 * 最新的错误错误码归类：
 * 	10：认证管理
 * 	11：用户管理
 * 	12：客户管理
 * 	13：借款管理
 * 	14：信审管理
 * 	15：报表管理
 * 	16：催收管理
 * 	17：产品管理
 * 	18：系统管理
 * 	19：参数管理
 * 
 * </pre>
 *
 * @author samson
 * @version $Revision:1.0.0, $Date: 2015年12月12日 下午3:41:41 $
 */
public class ResponseCode {

	/**
	 * 成功
	 */
	public static final String SUCCESS = "000000";

	/**
	 * 参数校验失败
	 */
	public static final String PARAMETERS_VALIDATE_FAILURE = "100000";

	/**
	 * 服务器异常
	 */
	public static final String SERVER_ERROR = "111111";

	/**
	 * 分页起始位置异常
	 */
	public static final String PAGE_REQUEST_START = "000001";
	/**
	 * 分页长度异常
	 */
	public static final String PAGE_REQUEST_LENGTH = "000002";

	/**
	 * code-message 映射 ,自动根据配置文件注入
	 */
	public static final Map<String, String> CODE_2_DEFAULTMESSAGE = new HashMap<String, String>();

	public static void defaultMessage(ResponseVo responseVo) {
		if (responseVo != null) {
			if (StringUtils.isEmpty(responseVo.getMessage())) {
				String message = null;
				if (StringUtils.isEmpty(message = CODE_2_DEFAULTMESSAGE.get(responseVo.getCode()))) {
					message = "操作成功";
				}
				responseVo.setMessage(message);
			}
		}
	}

	public static String getExceptionMessage(String code) {
		String message = CODE_2_DEFAULTMESSAGE.get(code);
		return StringUtils.isBlank(message) ? CODE_2_DEFAULTMESSAGE.get(SERVER_ERROR) : message;
	}
}
