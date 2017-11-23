package com.slfinance.redpack.core.repositories;

import java.util.Map;

import org.springframework.data.jpa.repository.Query;

import com.slfinance.redpack.core.constants.enums.AccountFlowTradeDirection;
import com.slfinance.redpack.core.constants.enums.AccountFlowflowType;
import com.slfinance.redpack.core.entities.AccountFlow;
import com.slfinance.redpack.core.extend.jpa.page.PageRequestVo;
import com.slfinance.redpack.core.extend.jpa.page.PageResponse;
import com.slfinance.redpack.core.extend.jpa.page.QueryExtend;
import com.slfinance.redpack.core.repositories.base.BaseRepository;

public interface AccountFlowRepository extends BaseRepository<AccountFlow> {

	/**
	 * app-账单列表
	 * @author SangLy
	 * @createTime 2016年11月7日 下午5:23:01
	 * @param pageRequest
	 * @return
	 */
	@QueryExtend
	@Query(nativeQuery=true,value="select fl.created_date,fl.trade_amount,fl.flow_code as account_flow_code,flow_type_desc memo from rp_t_account_flow fl join  rp_t_account act on act.id = fl.account_id  join rp_t_customer cu on  act.customer_id = cu.id and cu.id = :customerId and fl.flow_type in ( :flowTypes) order by fl.created_date desc")
	public PageResponse<Map<String, Object>> flowDetail(PageRequestVo pageRequest);

	/**
	 * 根据关联主键查询流水
	 * @param relatePrimary
	 * @return
	 */
	AccountFlow findByRelatePrimary(String relatePrimary);

	/**
	 * 
	 * @param relatePrimary
	 * @param tradeDirection
	 * @param flowType
	 * @return
	 */
	public AccountFlow findTopByRelatePrimaryAndTradeDirectionAndFlowTypeOrderByCreatedDateDesc(String relatePrimary, AccountFlowTradeDirection tradeDirection, AccountFlowflowType flowType);

}
