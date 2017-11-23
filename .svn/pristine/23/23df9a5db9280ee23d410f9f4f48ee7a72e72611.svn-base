/**
 * @(#)JpaConfig.java 1.0.0 2015年12月22日 下午6:33:33
 * <p/>
 * Copyright © 2015 善林金融.  All rights reserved.
 */

package com.slfinance.redpack.core.configs;

import javax.sql.DataSource;

import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.slfinance.redpack.core.extend.jpa.SimpleBaseRepositoryFactoryBean;
import com.slfinance.redpack.core.extend.jpa.auditor.LoginUserAuditorAware;

/**
 * JPA配置
 *
 * @author samson
 * @version $Revision:1.0.0, $Date: 2015年12月22日 下午6:33:33 $
 */
@Configuration
@EnableJpaAuditing(auditorAwareRef = "loginUserAuditorAware")
@EntityScan(basePackages = { "com.slfinance.redpack.core.entities" })
@EnableJpaRepositories(repositoryFactoryBeanClass = SimpleBaseRepositoryFactoryBean.class, repositoryImplementationPostfix = "CustImpl", basePackages = { "com.slfinance.redpack.core.repositories" })
public class JpaConfig {

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Bean
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate(JdbcTemplate jdbcTemplate) {
		return new NamedParameterJdbcTemplate(jdbcTemplate);
	}

	@Bean
	public AuditorAware<String> loginUserAuditorAware() {
		return new LoginUserAuditorAware();
	}

}
