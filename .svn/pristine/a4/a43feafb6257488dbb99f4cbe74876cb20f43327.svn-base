/**
 * @(#)LoginUserContextHolder.java 1.0.0 2016年3月17日 下午3:45:48
 * <p/>
 * Copyright © 2016 善林金融.  All rights reserved.
 */

package com.slfinance.redpack.core.extend.springweb.service;

import com.slfinance.redpack.core.vo.LoginUser;

/**
 * 当前登录用户本地线程变量
 *
 * @author samson
 * @version $Revision:1.0.0, $Date: 2016年3月17日 下午3:45:48 $
 */
public class LoginUserContextHolder {

	/**
	 * 登录用户的key
	 */
	public static final String LOGIN_USER_KEY = "loginUser";

	private static ThreadLocal<LoginUser> loginUser = new ThreadLocal<LoginUser>();

	public static LoginUser getLoginUser() {
		return loginUser.get();
	}

	public static void setLoginUser(LoginUser userId) {
		loginUser.set(userId);
	}

}
