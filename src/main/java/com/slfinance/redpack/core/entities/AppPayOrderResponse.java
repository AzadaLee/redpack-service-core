/** 
 * @(#)AppPayOrderResonse.java 1.0.0 2016年11月12日 下午2:37:49  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */

package com.slfinance.redpack.core.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * app订单支付返回结果
 * 
 * @author samson
 * @version $Revision:1.0.0, $Date: 2016年11月12日 下午2:37:49 $
 */
@Entity
@Table(name = "RP_T_APP_PAY_ORDER_RESPONSE")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class AppPayOrderResponse extends com.slfinance.redpack.core.entities.base.Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2436942300485434282L;
	/**
	 * 商户网站唯一订单号
	 */
	private String out_trade_no;
	/**
	 * 该交易在支付宝系统中的交易流水号。最长64位。
	 */
	private String trade_no;
	/**
	 * 支付宝分配给开发者的应用Id。
	 */
	private String app_id;
	/**
	 * 该笔订单的资金总额，单位为RMB-Yuan。取值范围为[0.01,100000000.00]，精确到小数点后两位。
	 */
	private double total_amount;
	/**
	 * 收款支付宝账号对应的支付宝唯一用户号。以2088开头的纯16位数字
	 */
	private String seller_id;
	/**
	 * 处理结果的描述，信息来自于code返回结果的描述
	 */
	private String msg;

	/**
	 * 编码格式
	 */
	private String charset;
	/**
	 * 时间
	 */
	private String timestamp;
	/**
	 * 结果处理是否成功，成功为T，失败为F
	 */
	private String is_success;
	/**
	 * 结果码
	 */
	private String code;

	/**
	 * 加密内容
	 */
	private String alipay_trade_app_pay_response;
	/**
	 * 加密后的结果
	 */
	private String sign;

	/**
	 * 加密方式
	 */
	private String sign_type;
	/**
	 * 原始数据
	 */
	private String origin_data;
}
