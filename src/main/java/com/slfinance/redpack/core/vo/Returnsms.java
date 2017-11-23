/** 
 * @(#)Returnsms.java 1.0.0 2016年8月11日 下午2:26:30  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */

package com.slfinance.redpack.core.vo;

import javax.xml.bind.annotation.XmlRootElement;

import com.slfinance.redpack.core.constants.enums.SmsMessageReturnStatus;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 短信接口返回信息的封装类
 * 
 * @author SangLy
 * @version $Revision:1.0.0, $Date: 2016年8月11日 下午2:26:30 $
 */
@Data
@NoArgsConstructor
@XmlRootElement
public class Returnsms {

	private SmsMessageReturnStatus returnstatus;
	private String message;
	private String remainpoint;
	private String taskID;
	private int successCounts;

	@Override
	public String toString() {
		return " returnstatus:" + returnstatus + "\n message:" + message + "\n remainpoint:" + remainpoint
				+ "\n taskID:" + taskID + "\n successCounts:" + successCounts;
	}
}
