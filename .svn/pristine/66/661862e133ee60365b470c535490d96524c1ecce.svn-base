package com.slfinance.redpack;

import com.slfinance.redpack.core.extend.beanNameGenerator.AuthorizationNameBeanGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServiceCoreApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(ServiceCoreApplication.class);
		application.setBeanNameGenerator(new AuthorizationNameBeanGenerator());
		application.run(args);
	}
}
