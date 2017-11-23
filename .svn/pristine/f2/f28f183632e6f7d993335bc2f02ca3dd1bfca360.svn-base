/** 
 * @(#)SMSMessageServiceTest.java 1.0.0 2016年8月11日 下午7:02:19  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */

package com.slfinance.redpack.core.services;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.slfinance.redpack.core.CreditServiceCoreApplicationTests;
import com.slfinance.redpack.core.constants.enums.BusinessType;
import com.slfinance.redpack.core.constants.enums.SMSContentTemplate;

/**
 * 短信服务测试类
 * 
 * @author SangLy
 * @version $Revision:1.0.0, $Date: 2016年8月11日 下午7:02:19 $
 */
public class SMSMessageServiceTest extends CreditServiceCoreApplicationTests {
	
	@Autowired
	private SMSMessageService smsMessageService;
	
	/**
	 * 短信发送服务测试
	 * 
	 * 注意:LocalSmsServiceImpl 上要添加@Service注解   因为这个类是后台线程启动。如果想测试必须实例化一个类
	 * 
	 * @author SangLy
	 * @createTime 2016年8月12日 下午1:40:12
	 */
	@Test
	public void testSendMobileVerificationCode(){
		smsMessageService.sendMobileVerificationCode("15716382738", BusinessType.注册, SMSContentTemplate.验证, "用户id00001");
	}

}
