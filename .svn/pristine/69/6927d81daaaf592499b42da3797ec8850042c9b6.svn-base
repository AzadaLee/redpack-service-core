package com.slfinance.redpack.core.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.slfinance.redpack.core.constants.enums.MessageTemplateMessageType;
import com.slfinance.redpack.core.entities.MessageTemplate;
import com.slfinance.redpack.core.extend.jpa.page.PageRequestVo;
import com.slfinance.redpack.core.extend.jpa.page.PageResponse;
import com.slfinance.redpack.core.extend.jpa.page.QueryExtend;
import com.slfinance.redpack.core.repositories.base.BaseRepository;

public interface MessageTemplateRepository extends BaseRepository<MessageTemplate> {

	@QueryExtend
	@Query(nativeQuery = true, value = "select * from rp_t_message_template t where ?(t.message_type = :messageType)? and ?(t.content like :content || '%')? and ?(t.format_explain = formatExplain)? and ?(trunc(t.last_modified_date)) >= to_date(:startDate, 'yyyy-mm-dd')? and ?(trunc(t.last_modified_date)) <= to_date(:endDate, 'yyyy-mm-dd') ? order by t.last_modified_date desc ")
	public PageResponse<MessageTemplate> list(PageRequestVo pageRequest);

	/**
	 * 根据类型查找单条数据
	 * 
	 * @author samson
	 * @createTime 2016年11月14日 下午2:54:51
	 * @param messageType
	 * @return
	 */
	public MessageTemplate findOneByMessageType(@Param("messageType") MessageTemplateMessageType messageType);
}
