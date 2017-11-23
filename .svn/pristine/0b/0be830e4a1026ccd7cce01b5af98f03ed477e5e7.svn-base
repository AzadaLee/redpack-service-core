package com.slfinance.redpack.core.repositories;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.slfinance.redpack.core.entities.Withdraw;
import com.slfinance.redpack.core.extend.jpa.page.QueryExtend;
import com.slfinance.redpack.core.repositories.base.BaseRepository;

public interface WithdrawRepository extends BaseRepository<Withdraw> {

	@QueryExtend
	@Query(nativeQuery=true,value="SELECT COUNT (1) FROM RP_T_WITHDRAW WHERE CUSTOMER_ID = :customerId AND TO_CHAR (CREATED_DATE, 'yyyy-MM') = TO_CHAR( :today,'yyyy-MM')")
	public Long countMonthWithdrawbyCustomerId(@Param("customerId")String customerId,@Param("today")Date today);
}
