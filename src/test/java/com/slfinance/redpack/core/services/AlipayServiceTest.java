/** 
 * @(#)AlipayService.java 1.0.0 2016年11月12日 上午10:29:14  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */

package com.slfinance.redpack.core.services;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.slfinance.redpack.core.ServiceCoreApplicationTests;
import com.slfinance.redpack.core.constants.AlipayServiceEnvConstants;

/**
 * 
 * 
 * @author samson
 * @version $Revision:1.0.0, $Date: 2016年11月12日 上午10:29:14 $
 */
public class AlipayServiceTest extends ServiceCoreApplicationTests {

	private AlipayClient alipayClient = new DefaultAlipayClient(AlipayServiceEnvConstants.ALIPAY_GATEWAY, AlipayServiceEnvConstants.APP_ID, AlipayServiceEnvConstants.PRIVATE_KEY, "json", AlipayServiceEnvConstants.CHARSET, AlipayServiceEnvConstants.ALIPAY_PUBLIC_KEY);

	@Test
	public void testQuery() {

		AlipayTradeQueryRequest alipayRequest = new AlipayTradeQueryRequest();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("out_trade_no", "409083ee586570f70158657c2a390005");
		map.put("trade_no", "4968465135163");
		alipayRequest.setBizContent(JSON.toJSONString(map));

		try {
			AlipayTradeQueryResponse alipayTradeQueryResponse = alipayClient.execute(alipayRequest);
			System.out.println(alipayTradeQueryResponse);
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
	}
}
