/** 
 * @(#)MessageServiceTest.java 1.0.0 2016年11月14日 下午4:32:06  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */

package com.slfinance.redpack.core.services;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.slfinance.redpack.core.ServiceCoreApplicationTests;
import com.slfinance.redpack.core.constants.enums.MessageTemplateMessageType;

/**
 * 
 * 
 * @author samson
 * @version $Revision:1.0.0, $Date: 2016年11月14日 下午4:32:06 $
 */
public class MessageServiceTest extends ServiceCoreApplicationTests {

	@Autowired
	private MessageService messageService;

	@Test
	public void testSendMessage() {
		Assert.assertNotNull(messageService.sendMessage(MessageTemplateMessageType.充值失败, "1", "409083ee58615c5c0158615d65250000").getId());
		Assert.assertNotNull(messageService.sendMessage(MessageTemplateMessageType.充值成功, "1", "409083ee58615c5c0158615d65250000").getId());
		Assert.assertNotNull(messageService.sendMessage(MessageTemplateMessageType.未付款红包即将到期, "1", "409082b05823b11e015823b1f8c30000").getId());
		Assert.assertNotNull(messageService.sendMessage(MessageTemplateMessageType.用户注册, "1", "").getId());
		Assert.assertNotNull(messageService.sendMessage(MessageTemplateMessageType.确定红包时间, "1", "409082b05823b11e015823b1f8c30000").getId());
		Assert.assertNotNull(messageService.sendMessage(MessageTemplateMessageType.红包被驳回, "1", "409082b05823b11e015823b1f8c30000").getId());
		Assert.assertNotNull(messageService.sendMessage(MessageTemplateMessageType.红包通过审核, "1", "409082b05823b11e015823b1f8c30000").getId());
		Assert.assertNotNull(messageService.sendMessage(MessageTemplateMessageType.转出失败, "1", "1").getId());
		Assert.assertNotNull(messageService.sendMessage(MessageTemplateMessageType.转出成功, "1", "1").getId());
		Assert.assertNotNull(messageService.sendMessage(MessageTemplateMessageType.邀请好友成功, "1", "1").getId());
	}
}
