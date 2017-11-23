/** 
 * @(#)BankController.java 1.0.0 2016年11月4日 下午7:30:54  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */

package com.slfinance.redpack.core.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.slfinance.redpack.core.controller.base.BaseController;
import com.slfinance.redpack.core.entities.Bank;
import com.slfinance.redpack.core.exception.SLException;
import com.slfinance.redpack.core.extend.fastjson.Serialize;
import com.slfinance.redpack.core.extend.fastjson.SerializeRule;
import com.slfinance.redpack.core.extend.validate.annotations.Rule;
import com.slfinance.redpack.core.extend.validate.annotations.Rules;
import com.slfinance.redpack.core.response.ResponseVo;
import com.slfinance.redpack.core.services.BankService;
import com.slfinance.redpack.core.services.RegionService;

/**
 * 
 * 
 * @author SangLy
 * @version $Revision:1.0.0, $Date: 2016年11月4日 下午7:30:54 $
 */
@RestController
@RequestMapping(value = "bank", method = RequestMethod.POST)
public class BankController extends BaseController {

	@Autowired
	private BankService bankService;
	
	@Autowired
	private RegionService regionService;

	/**
	 * app-银行卡新增|修改
	 * 
	 * @author SangLy
	 * @createTime 2016年11月7日 上午8:57:53
	 * @param params
	 * @return
	 */
	@RequestMapping("save")
	@Rules({ @Rule(name = "bank", required = true, requiredMessage = "220002"), @Rule(name = "province", required = true, requiredMessage = "220003"), @Rule(name = "city", required = true, requiredMessage = "220004"), @Rule(name = "bankName", required = true, requiredMessage = "220005"), @Rule(name = "bankCode", bankCard = true), @Rule(name = "cardHolderName", required = true, requiredMessage = "220006") })
	public ResponseVo appSave(@RequestBody Map<String, Object> params) throws SLException {
		String id = MapUtils.getString(params, "id"); // 记录id
		String bank = MapUtils.getString(params, "bank");
		String province = MapUtils.getString(params, "province");
		String city = MapUtils.getString(params, "city");
		String bankName = MapUtils.getString(params, "bankName");
		String bankCode = MapUtils.getString(params, "bankCode");
		String cardHolderName = MapUtils.getString(params, "cardHolderName");
		bankService.saveCustomerBank(id, bank, province, city, bankName, bankCode, cardHolderName, getLoginUserId());
		return ResponseVo.success();
	}
	
	/**
	 * app-获取银行卡信息
	 * 
	 * @author SangLy
	 * @createTime 2016年11月7日 上午8:57:53
	 * @param params
	 * @return
	 */
	
	@RequestMapping("detail")
	public ResponseVo appDetail(@RequestBody Map<String, Object> params) throws SLException {
		Map<String,Object> result = new HashMap<String,Object>();
		Bank bank = bankService.findBycustomerId(getLoginUserId());
		if (bank != null) {
			result.put("id", bank.getId());
			result.put("bank", bank.getBank());
			result.put("province", bank.getProvince());
			result.put("provinceName", regionService.findByCode(bank.getProvince()).getName());
			result.put("city", bank.getCity());
			result.put("cityName", regionService.findByCode(bank.getCity()).getName());
			result.put("bankName", bank.getBankName());
			result.put("bankCode", bank.getBankCode());
			result.put("cardHolderName", bank.getCardHolderName());
		}
		return ResponseVo.success(result);
	}

}
