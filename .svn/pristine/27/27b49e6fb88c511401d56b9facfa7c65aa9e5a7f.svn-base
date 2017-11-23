package com.slfinance.redpack.core.services;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.slfinance.redpack.core.ServiceCoreApplicationTests;
import com.slfinance.redpack.core.entities.Account;
import com.slfinance.redpack.core.extend.jpa.page.PageRequestVo;
import com.slfinance.redpack.core.extend.jpa.page.PageResponse;

public class AccountServiceTest  extends ServiceCoreApplicationTests{
	
	@Autowired
	private AccountService accountService;
	
	@Test
	public void testSystemRecharge(){
		String memo = "再次系统充值";
		Double amount = 5000.0;
		Account account = accountService.systemRecharge(amount, memo);
		Assert.assertTrue(account != null);
	}
	
	@Test
	public void testFlowList(){
		PageRequestVo pageRequest = new PageRequestVo(0, 10);
		pageRequest.addParam("accountType", "系统账户");
		PageResponse<Map<String, Object>> result = accountService.flowList(pageRequest);
		Assert.assertTrue(result != null);
	}

}
