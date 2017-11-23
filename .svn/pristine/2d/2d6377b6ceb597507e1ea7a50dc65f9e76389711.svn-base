/**
 * @(#)CodeGeneratePrefix.java 1.0.0 2016年7月27日 上午11:16:24
 * <p/>
 * Copyright © 2016 善林金融.  All rights reserved.
 */

package com.slfinance.redpack.core.constants.enums;

/**
 * 编码生成规则
 *
 * @author samson
 * @version $Revision:1.0.0, $Date: 2016年7月27日 上午11:16:24 $
 */
public enum CodeGenerator {
	/** 客户 */
	客户("dsb_uid_", "CUSTOMER_CODE_SEQUENCE"),
	/** 红包 */
	红包("dsb_lmaid_", "REDPACK_CODE_SEQUENCE"),
	/** 广告主 */
	广告主("dsb_auid_", "ADVERTISER_CODE_SEQUENCE"),
	/** 广告 */
	广告("dsb_aid_", "ADVERTISEMENT_CODE_SEQUENCE"),
	/** 流水 */
	流水("dsb_spid_", "FLOW_CODE_SEQUENCE"),
	/** 提现 */
	提现("dsb_tpid_", "WITHDRAW_CODE_SEQUENCE"),
	/** 账户 */
	账户("ACCOUNT_NO_", "ACCOUNT_CODE_SEQUENCE");

	/** 编号前缀 */
	private String prefix;
	/** 序列号 */
	private String sequenceName;

	private CodeGenerator(String prefix, String sequenceName) {
		this.prefix = prefix;
		this.sequenceName = sequenceName;
	}

	public String getPrefix() {
		return this.prefix;
	}

	public String getSequenceName() {
		return this.sequenceName;
	}
}
