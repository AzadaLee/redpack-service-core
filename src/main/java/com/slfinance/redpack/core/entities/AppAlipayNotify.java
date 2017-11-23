/** 
 * @(#)AppPayNotifyVo.java 1.0.0 2016年11月12日 下午4:19:58  
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
 * 支付宝支付的异步通知入参
 * 
 * @author samson
 * @version $Revision:1.0.0, $Date: 2016年11月12日 下午4:19:58 $
 *
 */
@Entity
@Table(name = "RP_T_APP_ALIPAY_NOTIFY")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class AppAlipayNotify extends com.slfinance.redpack.core.entities.base.Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7118763862246449864L;
	/** 通知时间(通知的发送时间。格式为yyyy-MM-dd HH:mm:ss) */
	private String notify_time;
	/** 通知类型(trade_status_sync) */
	private String notify_type;
	/** 通知校验ID */
	private String notify_id;
	/** 支付宝分配给开发者的应用Id */
	private String app_id;
	/** 编码格式，如utf-8、gbk、gb2312等 */
	private String charset;
	/** 签名算法类型，目前支持RSA */
	private String sign_type;
	/** 请参考异步返回结果的验签 */
	private String sign;
	/** 支付宝交易凭证号 */
	private String trade_no;
	/** 原支付请求的商户订单号 */
	private String out_trade_no;
	/** 买家支付宝账号对应的支付宝唯一用户号。以2088开头的纯16位数字 */
	private String buyer_id;
	/** 买家支付宝账号 */
	private String buyer_logon_id;
	/**
	 * 交易目前所处的状态，见交易状态说明()
	 * 
	 * <pre>
	 * 		WAIT_BUYER_PAY：交易创建，等待买家付款
	 * 		TRADE_CLOSED：未付款交易超时关闭，或支付完成后全额退款
	 * 		TRADE_SUCCESS：交易支付成功
	 * 		TRADE_FINISHED：交易结束，不可退款
	 * </pre>
	 */
	private String trade_status;
	/** 本次交易支付的订单金额，单位为人民币（元） */
	private double total_amount;
	/** 商家在交易中实际收到的款项，单位为元 */
	private double receipt_amount;
	/** 用户在交易中支付的金额 */
	private double buyer_pay_amount;
	/** 该笔交易的买家付款时间。格式为yyyy-MM-dd HH:mm:ss */
	private String gmt_payment;
	/**
	 * 原始的请求数据
	 */
	private String origin_data;

}
