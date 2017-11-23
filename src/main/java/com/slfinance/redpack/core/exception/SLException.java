/**
 * @(#)SLException.java 1.0.0 2015年12月14日 上午9:36:14
 * <p/>
 * Copyright © 2015 善林金融.  All rights reserved.
 */

package com.slfinance.redpack.core.exception;

/**
 * 封装异常
 *
 * @author samson
 * @version $Revision:1.0.0, $Date: 2015年12月14日 上午9:36:14 $
 */
public class SLException extends RuntimeException {

	private static final long serialVersionUID = -1410570443387344326L;
	private String code;

	public SLException(String code, String message, String param) {
		super(message != null ? String.format(message, param) : message);
		this.code = code;
	}

	public SLException(String code, String message) {
		this(code, message, null);
	}

	public SLException(String code) {
		this(code, null, null);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
