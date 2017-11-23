/** 
 * @(#)BankService.java 1.0.0 2016年11月4日 下午7:29:37  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */

package com.slfinance.redpack.core.services;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.slfinance.redpack.core.constants.enums.RecordType;
import com.slfinance.redpack.core.constants.enums.BankType;
import com.slfinance.redpack.core.entities.Bank;
import com.slfinance.redpack.core.exception.SLException;
import com.slfinance.redpack.core.repositories.BankRepository;
import com.slfinance.redpack.core.services.base.BaseService;

/**
 * 
 * 
 * @author SangLy
 * @version $Revision:1.0.0, $Date: 2016年11月4日 下午7:29:37 $
 */
@Service
public class BankService extends BaseService<Bank, BankRepository> {

	/**
	 * app-银行卡新增|修改
	 * 
	 * @author SangLy
	 * @createTime 2016年11月7日 上午10:33:43
	 * @param id
	 * @param bank
	 * @param province
	 * @param city
	 * @param bankName
	 * @param bankCode
	 * @param cardHolderName
	 * @param customerId
	 */
	@Transactional(readOnly = false)
	public void saveCustomerBank(String id, String bank, String province, String city, String bankName, String bankCode, String cardHolderName, String customerId) throws SLException{
		Bank customerBank = null;
		if (StringUtils.isNotBlank(id)) {
			customerBank = repository.findOne(id);
			if (customerBank == null) {
				throw new SLException("220007", "bank not found");
			}
		}else{
			//一个客户只能绑定一张银行卡
			customerBank = findBycustomerId(customerId);
			if(customerBank != null){
				throw new SLException("220009","客户只能绑定一张银行卡");
			}else{
				customerBank = new Bank();
			}
		}
		customerBank.setCustomerId(customerId);
		try {
			customerBank.setBank(BankType.valueOf(bank));
		} catch (Exception e) {
			throw new SLException("220008","该银行不支持提现");
		}
		customerBank.setProvince(province);
		customerBank.setCity(city);
		customerBank.setBankName(bankName);
		customerBank.setBankCode(bankCode);
		customerBank.setCardHolderName(cardHolderName);
		customerBank.setRecordType(RecordType.正本);
		repository.save(customerBank);
	}
	
	/**
	 * 查询客户银行卡信息
	 * @author SangLy
	 * @createTime 2016年11月7日 上午11:42:04
	 * @param customerId
	 * @return
	 */
	public Bank findBycustomerId(String customerId){
		return repository.findByCustomerIdAndRecordType(customerId,RecordType.正本);
	}
}
