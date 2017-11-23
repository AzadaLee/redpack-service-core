/** 
 * @(#)SearchFilterRepository.java 1.0.0 2016年6月29日 上午11:55:52  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */

package com.slfinance.redpack.core.repositories.base;

import java.util.List;

import com.slfinance.redpack.core.extend.jpa.page.PageRequestVo;
import com.slfinance.redpack.core.extend.jpa.page.PageResponse;
import com.slfinance.redpack.core.extend.jpa.search.SearchFilter;
import org.springframework.data.domain.Sort;

/**
 * 基于HQL的依赖于{@code SearchFilter}进行条件过滤的单表查询
 * 
 * @author samson
 * @version $Revision:1.0.0, $Date: 2016年6月29日 上午11:55:52 $
 */
public interface SearchFilterRepository<E> {
	/**
	 * 根据{@code SearchFilter} 查找单表单个记录
	 * 
	 * @param searchFilters
	 *            查询条件
	 * @return
	 */
	E findOne(List<SearchFilter> searchFilters);

	/**
	 * HQL多表关联查询单表单个记录
	 * 
	 * @param selectFromHql
	 *            表关联HQL (格式必须为:带 where且如果没有带条件的则需要添加1=1 框架只负责拼and a=b or c=d
	 *            and e=f)
	 * @param searchFilters
	 *            查询条件
	 * @return
	 */
	E findOne(String selectFromHql, final List<SearchFilter> searchFilters);

	/**
	 * HQL单表查询多条记录
	 * 
	 * @param searchFilters
	 *            查询条件
	 * @return
	 */
	List<E> findAll(List<SearchFilter> searchFilters);

	/**
	 * HQL多表关联查询多条单表记录
	 * 
	 * @param selectFromHql
	 *            表关联HQL (格式必须为:带 where且如果没有带条件的则需要添加1=1 框架只负责拼and a=b or c=d
	 * @param searchFilters
	 *            查询条件
	 * @return
	 */
	List<E> findAll(String selectFromHql, List<SearchFilter> searchFilters);

	/**
	 * HQL单表查询多条带排序的单表记录
	 * 
	 * @param searchFilters
	 *            查询条件
	 * @param sort
	 *            排序
	 * @return
	 */
	List<E> findAll(List<SearchFilter> searchFilters, Sort sort);

	/**
	 * HQL多表关联查询带排序的多条单表记录
	 * 
	 * @param selectFromHql
	 *            表关联HQL (格式必须为:带 where且如果没有带条件的则需要添加1=1 框架只负责拼and a=b or c=d
	 * @param searchFilters
	 *            查询条件
	 * @param sort
	 *            排序
	 * @return
	 */
	List<E> findAll(String selectFromHql, List<SearchFilter> searchFilters, Sort sort);

	/**
	 * 分页查找单表记录()
	 * 
	 * @param pageRequest
	 *            分页
	 * @return
	 */
	PageResponse<E> findAll(PageRequestVo pageRequest);

	/**
	 * 分页查找多表联合单表记录
	 * 
	 * @param selectFromHql
	 *            表关联HQL (格式必须为:带 where且如果没有带条件的则需要添加1=1 框架只负责拼and a=b or c=d
	 * @param pageRequest
	 *            分页
	 * @return
	 */
	PageResponse<E> findAll(String selectFromHql, PageRequestVo pageRequest);

	/**
	 * 单表统计
	 * 
	 * @param searchFilters
	 *            查询条件
	 * @return
	 */
	int count(List<SearchFilter> searchFilters);

	/**
	 * 多表关联统计
	 * 
	 * @param selectFromHql
	 *            (格式必须为:带 where且如果没有带条件的则需要添加1=1 框架只负责拼and a=b or c=d
	 * @param searchFilters
	 *            查询条件
	 * @return
	 */
	int count(String selectFromHql, List<SearchFilter> searchFilters);
}
