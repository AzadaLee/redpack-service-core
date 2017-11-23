package com.slfinance.redpack.core.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.slfinance.redpack.core.constants.enums.MessageTemplateMessageType;
import com.slfinance.redpack.core.entities.MessageTemplate;
import com.slfinance.redpack.core.exception.SLException;
import com.slfinance.redpack.core.extend.jpa.page.PageRequestVo;
import com.slfinance.redpack.core.extend.jpa.page.PageResponse;
import com.slfinance.redpack.core.repositories.MessageTemplateRepository;
import com.slfinance.redpack.core.services.base.BaseService;

@Service
public class MessageTemplateService extends BaseService<MessageTemplate, MessageTemplateRepository> {

	public static final String PLACEHOLDER_PREFIX = "$$";
	public static final String PLACEHOLDER_SUFFIX = "$$";

	/**
	 * 更新站内信
	 * 
	 * @param id
	 * @param content
	 * @return
	 */
	@Transactional
	public MessageTemplate update(String id, String content) {
		MessageTemplate messageTemplate = findOne(id);
		if (null == messageTemplate) {
			throw new SLException("210002");
		}
		messageTemplate.setContent(content);
		return save(messageTemplate);
	}

	/**
	 * 查询站内信列表
	 * 
	 * @param pageRequest
	 * @return
	 */
	public PageResponse<MessageTemplate> list(PageRequestVo pageRequest) {
		return repository.list(pageRequest);
	}

	/**
	 * 根据类型查找单条数据
	 * 
	 * @author samson
	 * @createTime 2016年11月14日 下午2:49:34
	 * @param messageTemplateMessageType
	 * @return
	 */
	public MessageTemplate findOneByMessageTemplateMessageType(MessageTemplateMessageType messageType) {
		return repository.findOneByMessageType(messageType);
	}
}
