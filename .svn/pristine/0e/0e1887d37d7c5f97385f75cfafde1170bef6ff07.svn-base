package com.slfinance.redpack.core.services;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.slfinance.redpack.core.ServiceCoreApplicationTests;

public class CodeGeneratorServiceTest extends ServiceCoreApplicationTests {

	@Autowired
	CodeGeneratorService codeGeneratorService;

	@Test
	public void test() {
		Assert.assertTrue(codeGeneratorService.getCustomerCode() != null);
		Assert.assertTrue(codeGeneratorService.getAdvertiserCode() != null);
	//	Assert.assertTrue(codeGeneratorService.getAdvertiseCode() != null);
		Assert.assertTrue(codeGeneratorService.getAdvertisementCode() != null);
		Assert.assertTrue(codeGeneratorService.getRedPackCode() != null);
	}
}
