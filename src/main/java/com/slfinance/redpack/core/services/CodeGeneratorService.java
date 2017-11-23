/** 
 * @(#)CodeGeneratorService.java 1.0.0 2016年7月26日 下午4:42:30  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */

package com.slfinance.redpack.core.services;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slfinance.redpack.core.constants.enums.CodeGenerator;
import com.slfinance.redpack.core.repositories.custom.CodeGeneratorRepositoryCust;
import com.slfinance.redpack.core.services.base.AbstractService;

/**
 * 各种编号生成服务
 * 
 * @author samson
 * @version $Revision:1.0.0, $Date: 2016年7月26日 下午4:42:30 $
 */
@Service
public class CodeGeneratorService extends AbstractService {

	@Autowired
	CodeGeneratorRepositoryCust codeGeneratorRepositoryCust;

	/**
	 * * 根据序列名和编号前缀名生成编号
	 * 
	 * <pre>
	 * 	编码规则：
	 * 		y:前缀 固定。
	 * 		x: 长度为10，纯数字0000000001 开始，按照创建时间顺序加1
	 * 		最终编码:y+x
	 * </pre>
	 * 
	 * @author samson
	 * @createTime 2016年7月27日 上午11:55:33
	 * @param codeGenerator
	 *            {@link CodeGenerator} 编号生成器
	 * @return
	 */
	public String getCode(CodeGenerator codeGenerator) {
		return codeGenerator.getPrefix() + StringUtils.leftPad(codeGeneratorRepositoryCust.getNextVal(codeGenerator.getSequenceName()) + "", 10, "0");
	}

	/**
	 * 获取客户编号
	 * 
	 * @author samson
	 * @createTime 2016年7月27日 上午11:58:42
	 * @return
	 */
	public String getCustomerCode() {
		return getCode(CodeGenerator.客户);
	}

	/**
	 * 获取红包编号
	 * 
	 * @author samson
	 * @createTime 2016年7月27日 上午11:58:49
	 * @return
	 */
	public String getRedPackCode() {
		return getCode(CodeGenerator.红包);
	}

	/**
	 * 获取广告主编号
	 * 
	 * @author samson
	 * @createTime 2016年7月27日 上午11:58:57
	 * @return
	 */
	public String getAdvertiserCode() {
		return getCode(CodeGenerator.广告主);
	}

	/**
	 * 获取广告编号
	 * 
	 * @author samson
	 * @createTime 2016年7月27日 上午11:59:05
	 * @return
	 */
	public String getAdvertisementCode() {
		return getCode(CodeGenerator.广告);
	}

	/**
	 * 获取流水编号
	 * 
	 * @author samson
	 * @createTime 2016年11月1日 上午10:33:26
	 * @return
	 */
	public String getFlowCode() {
		return getCode(CodeGenerator.流水);
	}

	/**
	 * 获取提现编号
	 * 
	 * @author samson
	 * @createTime 2016年11月1日 上午10:33:51
	 * @return
	 */
	public String getWithdrawCode() {
		return getCode(CodeGenerator.提现);
	}

	/**
	 * 获取账户编号
	 * 
	 * @author samson
	 * @createTime 2016年11月1日 上午10:34:17
	 * @return
	 */
	public String getAccountCode() {
		return getCode(CodeGenerator.账户);
	}

	/**
	 * 生成邀请码 ： 数字+字母 8位需要检验唯一性
	 * 
	 * @author SangLy
	 * @createTime 2016年8月24日 下午4:25:10
	 * @return
	 */
	public String getInvitationCode() {
		return RandomStringUtils.randomAlphanumeric(8);
	}

	/**
	 * 账户流水编号
	 * 
	 * @author samson
	 * @createTime 2016年11月22日 下午2:49:18
	 * @return
	 */
	public String getAccountFlowCode() {
		return getCode(CodeGenerator.流水);
	}
}
