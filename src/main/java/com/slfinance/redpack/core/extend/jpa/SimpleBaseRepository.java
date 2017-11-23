/**
 * Copyright (c) 2005-2012 https://github.com/zhangkaitao
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.slfinance.redpack.core.extend.jpa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.slfinance.redpack.core.entities.base.Entity;
import com.slfinance.redpack.core.exception.SLException;
import com.slfinance.redpack.core.extend.jpa.page.PageRequestVo;
import com.slfinance.redpack.core.extend.jpa.page.PageResponse;
import com.slfinance.redpack.core.extend.jpa.search.DynamicSpecifications;
import com.slfinance.redpack.core.extend.jpa.search.SearchFilter;
import com.slfinance.redpack.core.extend.jpa.search.SqlArrayWrapper;
import com.slfinance.redpack.core.repositories.base.BaseRepository;
import com.slfinance.redpack.core.repositories.base.SearchFilterRepository;
import com.slfinance.redpack.core.utils.SqlUtil;

/**
 * <p>
 * 抽象基础Custom Repository 实现
 * </p>
 * <p/>
 * User: Zhang Kaitao
 * <p/>
 * Date: 13-1-15 下午7:33
 * <p/>
 * Version: 1.0
 */
public class SimpleBaseRepository<E extends Entity> extends SimpleJpaRepository<E, String> implements BaseRepository<E>, SearchFilterRepository<E> {

	public static final String UPDATE_RECORDSTATUS_QUERY_STRING = "update %s x set x.recordStatus=':recordStatus' where x in (?1)";
	public static final String DELETE_ALL_QUERY_STRING = "delete from %s x where x in (?1)";
	public static final String FIND_QUERY_STRING = "from %s x where 1=1 ";
	public static final String COUNT_QUERY_STRING = "select count(1) from %s x where 1=1 ";

	protected final EntityManager em;
	protected final JpaEntityInformation<E, String> entityInformation;

	protected JdbcTemplate jdbcTemplate;
	protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private Class<E> entityClass;
	private String idName;

	public SimpleBaseRepository(JpaEntityInformation<E, String> entityInformation, EntityManager entityManager, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		super(entityInformation, entityManager);
		this.jdbcTemplate = jdbcTemplate;
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;

		this.entityInformation = entityInformation;
		this.entityClass = this.entityInformation.getJavaType();
		this.idName = this.entityInformation.getIdAttributeNames().iterator().next();
		this.em = entityManager;
	}

	@Override
	public void deleteInBatch(Iterable<E> entities) {
		delete(entities);
	}

	@Override
	public void deleteAll() {
		throw new SLException("不允许的操作");
	}

	@Override
	public void deleteAllInBatch() {
		deleteAll();
	}

	/**
	 * 按照主键查询
	 *
	 * @param id
	 *            主键
	 * @return 返回id对应的实体
	 */

	@Override
	public E findOne(String id) {
		return super.findOne(id);
	}

	@Override
	public E findOne(final List<SearchFilter> searchFilters) {
		try {
			return findOne(DynamicSpecifications.fromSearchFilterToSpecification(searchFilters, getDomainClass()));
		} catch (NoResultException e) {
			return null;
		}
	}

	public E findOne(String selectFromSql, final List<SearchFilter> searchFilters) {
		try {
			SqlArrayWrapper sqlArrayWrapper = DynamicSpecifications.fromSearchFilterToSQLWrapper(searchFilters);
			TypedQuery<E> query = em.createQuery(selectFromSql + sqlArrayWrapper.getSql(), getDomainClass());
			setParameters(query, sqlArrayWrapper.getParameters());
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public List<E> findAll(List<SearchFilter> searchFilters) {
		return super.findAll(DynamicSpecifications.fromSearchFilterToSpecification(searchFilters, getDomainClass()));
	}

	@Override
	public List<E> findAll(String selectFromSql, List<SearchFilter> searchFilters) {
		SqlArrayWrapper sqlArrayWrapper = DynamicSpecifications.fromSearchFilterToSQLWrapper(searchFilters);
		TypedQuery<E> query = em.createQuery(selectFromSql + sqlArrayWrapper.getSql(), getDomainClass());
		setParameters(query, sqlArrayWrapper.getParameters());
		return query.getResultList();
	}

	@Override
	public List<E> findAll(List<SearchFilter> searchFilters, Sort sort) {
		return super.findAll(DynamicSpecifications.fromSearchFilterToSpecification(searchFilters, getDomainClass()), sort);
	}

	@Override
	public List<E> findAll(String selectFromSql, List<SearchFilter> searchFilters, Sort sort) {
		SqlArrayWrapper sqlArrayWrapper = DynamicSpecifications.fromSearchFilterToSQLWrapper(searchFilters);
		TypedQuery<E> query = em.createQuery(selectFromSql + sqlArrayWrapper.getSql() + DynamicSpecifications.fromSortToOrderBySql(sort), getDomainClass());
		setParameters(query, sqlArrayWrapper.getParameters());
		return query.getResultList();
	}

	@Override
	public PageResponse<E> findAll(PageRequestVo pageRequest) {
		return new PageResponse<E>(super.findAll(DynamicSpecifications.fromSearchFilterToSpecification(pageRequest.getSearchFilters(), getDomainClass()), new PageRequest(pageRequest.getPageNo(), pageRequest.getLength(), pageRequest.getSort())));
	}

	@Override
	public PageResponse<E> findAll(String selectFromSql, PageRequestVo pageRequest) {
		SqlArrayWrapper sqlArrayWrapper = DynamicSpecifications.fromSearchFilterToSQLWrapper(pageRequest.getSearchFilters());
		String selectFromWhereSql = selectFromSql + sqlArrayWrapper.getSql();
		int total = count(selectFromWhereSql, sqlArrayWrapper);
		List<E> list = Collections.emptyList();
		if (total > 0) {
			TypedQuery<E> query = em.createQuery(selectFromWhereSql + DynamicSpecifications.fromSortToOrderBySql(pageRequest.getSort()), getDomainClass());
			query.setFirstResult(pageRequest.getStart());
			query.setMaxResults(pageRequest.getLength());
			setParameters(query, sqlArrayWrapper.getParameters());
			list = query.getResultList();
		}
		return new PageResponse<E>(total, list == null ? new ArrayList<E>(0) : list);
	}

	@Override
	public int count(List<SearchFilter> searchFilters) {
		return (int) count(DynamicSpecifications.fromSearchFilterToSpecification(searchFilters, getDomainClass()));
	}

	@Override
	public int count(String selectFromSql, List<SearchFilter> searchFilters) {
		SqlArrayWrapper sqlArrayWrapper = DynamicSpecifications.fromSearchFilterToSQLWrapper(searchFilters);
		return count(selectFromSql + sqlArrayWrapper.getSql(), sqlArrayWrapper);
	}

	/**
	 * 查找总记录数提取公共
	 *
	 * @param selectFromWhereSql
	 * @param sqlArrayWrapper
	 * @return
	 */
	protected int count(String selectFromWhereSql, SqlArrayWrapper sqlArrayWrapper) {
		String countSql = getCountSql(selectFromWhereSql);
		Query countQuery = em.createQuery(countSql);
		setParameters(countQuery, sqlArrayWrapper.getParameters());
		return Integer.valueOf(countQuery.getSingleResult().toString());
	}

	/**
	 * 设置query的参数
	 *
	 * @param query
	 * @param parameters
	 */
	private void setParameters(Query query, Object[] parameters) {
		if (parameters != null) {
			for (int i = 0; i < parameters.length; i++) {
				query.setParameter(i, parameters[i]);
			}
		}
	}

	/**
	 * HQL组装count
	 *
	 * @param sql
	 *            带组装sql
	 * @return
	 */
	private String getCountSql(String sql) {
		int fromIndex;
		if ((fromIndex = sql.toUpperCase().indexOf(SqlUtil.FROM)) != -1) {
			return SqlUtil.SELECT_COUNT + SqlUtil.BLANK + sql.substring(fromIndex);
		} else {
			throw new RuntimeException(String.format("错误的sql(不存在%s子句):%s", SqlUtil.FROM, sql));
		}
	}
}
