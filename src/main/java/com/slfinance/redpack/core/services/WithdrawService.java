package com.slfinance.redpack.core.services;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.slfinance.redpack.core.entities.Withdraw;
import com.slfinance.redpack.core.repositories.WithdrawRepository;
import com.slfinance.redpack.core.services.base.BaseService;

@Service
public class WithdrawService extends BaseService<Withdraw, WithdrawRepository>{
	
	/**
	 * 查询一个月申请提现的次数
	 * 
	 * @author SangLy
	 * @createTime 2016年11月7日 下午3:59:15
	 * @return
	 */
	public Long countMonthWithdrawbyCustomerId(String customerId, Date today) {
		return repository.countMonthWithdrawbyCustomerId(customerId, today);
	}

}
