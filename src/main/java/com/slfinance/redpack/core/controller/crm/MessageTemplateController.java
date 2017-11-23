package com.slfinance.redpack.core.controller.crm;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.slfinance.redpack.common.utils.MapUtil;
import com.slfinance.redpack.core.entities.MessageTemplate;
import com.slfinance.redpack.core.extend.fastjson.Serialize;
import com.slfinance.redpack.core.extend.fastjson.SerializeRule;
import com.slfinance.redpack.core.extend.jpa.page.PageRequestVo;
import com.slfinance.redpack.core.extend.validate.annotations.Rule;
import com.slfinance.redpack.core.extend.validate.annotations.Rules;
import com.slfinance.redpack.core.response.ResponseVo;
import com.slfinance.redpack.core.services.MessageTemplateService;

@RestController("crmMessageTemplateController")
@RequestMapping(method=RequestMethod.POST,value="/crm/messageTemplate")
public class MessageTemplateController {
	
	@Autowired
	private MessageTemplateService messageTemplateService;
	
	/**
	 * 站内信单个信息获取
	 * @param params
	 * @return
	 */
	@RequestMapping("/detail")
	@Rules({@Rule(name="id",required=true,requiredMessage="210001")})
	@Serialize({@SerializeRule(clazz=MessageTemplate.class,include={"id","messageType","content","formatExplain"})})
	public ResponseVo detail(@RequestBody Map<String, Object> params){
		String id = MapUtil.getStringTrim(params, "id");
		return ResponseVo.success(messageTemplateService.findOne(id));
	}
	
	/**
	 * 更新站内信
	 * @param params
	 * @return
	 */
	@RequestMapping("/update")
	@Rules({@Rule(name="id",required=true,requiredMessage="210001"),@Rule(name="content",required=true,requiredMessage="210003")})
	public ResponseVo update(@RequestBody Map<String, Object> params){
		String id = MapUtil.getStringTrim(params, "id");
		String content = MapUtil.getStringTrim(params, "content");
		return ResponseVo.success(messageTemplateService.update(id,content));
	}
	
	/**
	 * 查询站内信列表
	 * @param pageRequest
	 * @return
	 */
	@RequestMapping("/list")
	public ResponseVo list(PageRequestVo pageRequest){
		return ResponseVo.success(messageTemplateService.list(pageRequest));
	}
}
