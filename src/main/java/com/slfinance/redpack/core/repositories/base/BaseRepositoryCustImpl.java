/**
 * @(#)BaseRepositoryCustImpl.java 1.0.0 2015年12月19日 下午2:16:14
 * <p/>
 * Copyright © 2015 善林金融.  All rights reserved.
 */

package com.slfinance.redpack.core.repositories.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 *
 * @author samson
 * @version $Revision:1.0.0, $Date: 2015年12月19日 下午2:16:14 $
 */
public class BaseRepositoryCustImpl {

	@Autowired
	protected JdbcTemplate jdbcTemplate;

	@Autowired
	protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@PersistenceContext
	protected EntityManager entityManager;

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
}
