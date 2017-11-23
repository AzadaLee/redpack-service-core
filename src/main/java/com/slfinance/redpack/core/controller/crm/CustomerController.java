/** 
 * @(#)CustomerController.java 1.0.0 2016年8月30日 上午10:52:30  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */ 

package com.slfinance.redpack.core.controller.crm;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.slfinance.redpack.common.utils.MapUtil;
import com.slfinance.redpack.core.constants.enums.CustomerStatus;
import com.slfinance.redpack.core.controller.base.BaseController;
import com.slfinance.redpack.core.entities.Customer;
import com.slfinance.redpack.core.extend.fastjson.Serialize;
import com.slfinance.redpack.core.extend.fastjson.SerializeRule;
import com.slfinance.redpack.core.extend.jpa.page.PageRequestVo;
import com.slfinance.redpack.core.extend.validate.annotations.Rule;
import com.slfinance.redpack.core.extend.validate.annotations.Rules;
import com.slfinance.redpack.core.response.ResponseVo;
import com.slfinance.redpack.core.services.CustomerService;

/**   
 * 
 *  
 * @author  taoxm
 * @version $Revision:1.0.0, $Date: 2016年8月30日 上午10:52:30 $ 
 */
@RestController("crmCustomerController")
@RequestMapping(value="/crm/customer",method=RequestMethod.POST)
public class CustomerController extends BaseController{
	
	@Autowired
	private CustomerService customerService;
	
	/**
	 * 设置客户状态
	 * @author taoxm
	 * @createTime 2016年8月30日 上午10:56:38
	 * @param params
	 * @return
	 */
	@RequestMapping("/updateStatus")
	@Rules({@Rule(name="id",required=true,requiredMessage="120001"),@Rule(name="status",required=true,requiredMessage="120056")})
	public ResponseVo updateStatus(@RequestBody Map<String, Object> params){
		String id = MapUtil.getStringTrim(params, "id");
		CustomerStatus customerStatus = CustomerStatus.valueOf(MapUtil.getStringTrim(params, "status"));
		customerService.updateStatusById(id,customerStatus);
		return ResponseVo.success();
	}
	
	/**
	 * 客户列表查询
	 * @author taoxm
	 * @createTime 2016年8月30日 上午11:13:36
	 * @param pageRequest
	 * @return
	 */
	@RequestMapping("/findAllCustomer")
	@Serialize({@SerializeRule(clazz=Customer.class,include={"id", "mobile", "createdDate", "status", "customerCode" })})
	public ResponseVo findAllCustomer(PageRequestVo pageRequest){
		return ResponseVo.success(customerService.findAllCustomer(pageRequest));
	}
}
