/**
 * @(#)PageRequest.java 1.0.0 2015年12月19日 下午3:13:47
 * <p/>
 * Copyright © 2015 善林金融.  All rights reserved.
 */

package com.slfinance.redpack.core.extend.jpa.page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.Min;

import com.slfinance.redpack.core.extend.jpa.search.SearchFilter;
import com.slfinance.redpack.core.extend.jpa.search.SearchOperator;
import com.slfinance.redpack.core.vo.LoginUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.data.domain.Sort;

import com.google.common.collect.Lists;
import com.slfinance.redpack.core.response.ResponseCode;

/**
 * 分页请求对象
 *
 * @author samson
 * @version $Revision:1.0.0, $Date: 2015年12月19日 下午3:13:47 $
 */
@NoArgsConstructor
public class PageRequestVo {

	@Getter
	@Setter
	@Min(value = 0, message = ResponseCode.PAGE_REQUEST_START)
	private int start = 0;
	@Getter
	@Setter
	@Min(value = 0, message = ResponseCode.PAGE_REQUEST_LENGTH)
	private int length = 10;
	@Getter
	@Setter
	private Map<String, Object> params = new HashMap<String, Object>();
	@Getter
	@Setter
	private LoginUser loginUser;
	@Getter
	@Setter
	private Sort sort;

	@Getter
	private List<SearchFilter> searchFilters = new ArrayList<SearchFilter>();
	private Map<String, List<Integer>> searchFilterDiagram = new HashMap<String, List<Integer>>();

	public PageRequestVo(int start, int length, Map<String, Object> params) {
		this.start = start;
		this.length = length;
		this.params = params == null ? new HashMap<String, Object>() : params;
	}

	public PageRequestVo(int start, int length) {
		this.start = start;
		this.length = length;
	}

	/**
	 * {@code SearchFilter} 分页查询专用
	 * 
	 * @param start
	 * @param length
	 * @param searchFilters
	 */
	public PageRequestVo(int start, int length, List<SearchFilter> searchFilters) {
		this(start, length);
		this.searchFilters = searchFilters == null ? this.searchFilters : searchFilters;
		initSearchFilterDiagram();
	}

	/**
	 * {@code SearchFilter} 分页查询专用
	 * 
	 * @param start
	 * @param length
	 * @param searchFilters
	 * @param sort
	 */
	public PageRequestVo(int start, int length, List<SearchFilter> searchFilters, Sort sort) {
		this(start, length, searchFilters);
		this.sort = sort;
	}

	/**
	 * {@code Map}条件专用
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public PageRequestVo addParam(String key, Object value) {
		this.params.put(key, value);
		return this;
	}

	/**
	 * {@code Map}条件专用
	 * 
	 * @param params
	 * @return
	 */
	public PageRequestVo addParams(Map<String, Object> params) {
		this.params.putAll(params);
		return this;
	}

	/**
	 * {@code Map}条件专用
	 * 
	 * @param key
	 * @return
	 */
	public Object getParam(String key) {
		return params.get(key);
	}

	/**
	 * {@code Map}条件专用
	 * 
	 * @param key
	 * @return
	 */
	public boolean containsKey(String key) {
		return params.containsKey(key);
	}

	/**
	 * {@code SearchFilter}条件专用
	 * 
	 * @param searchFilters
	 * @return
	 */
	public PageRequestVo addSearchFilter(SearchFilter... searchFilters) {
		for (SearchFilter searchFilter : searchFilters) {
			if (searchFilter != null) {
				// 顺序不能颠倒
				initSearchFilterDiagram(searchFilter, this.searchFilters.size());
				this.searchFilters.add(searchFilter);
			}
		}
		return this;
	}

	/**
	 * {@code SearchFilter}条件专用
	 * 
	 * @param keys
	 * @return
	 */
	public PageRequestVo removeSearchFilter(String... keys) {
		for (String key : keys) {
			List<Integer> indexs = this.searchFilterDiagram.get(key);
			if (indexs != null) {
				for (Integer index : indexs) {
					this.searchFilters.remove(index);
				}
				this.searchFilterDiagram.remove(key);
			}
		}
		return this;
	}

	/**
	 * {@code SearchFilter}条件专用
	 * 
	 * @param key
	 *            fieldName
	 * @return
	 */
	public boolean containSearchFilter(String key) {
		return this.searchFilterDiagram.containsKey(key);
	}

	/**
	 * {@code SearchFilter}条件专用
	 * 
	 * @param key
	 *            fieldName
	 * @return
	 */
	public List<SearchFilter> getSearchFilter(String key) {
		List<SearchFilter> list = new ArrayList<SearchFilter>();
		List<Integer> indexs = this.searchFilterDiagram.get(key);
		if (indexs != null) {
			for (Integer index : indexs) {
				list.add(this.searchFilters.get(index));
			}
		}
		return list;
	}

	private void initSearchFilterDiagram() {
		for (int i = 0; i < this.searchFilters.size(); i++) {
			SearchFilter searchFilter = this.searchFilters.get(i);
			initSearchFilterDiagram(searchFilter, i);
		}
	}

	private void initSearchFilterDiagram(SearchFilter searchFilter, int index) {
		if (this.searchFilterDiagram.containsKey(searchFilter.fieldName)) {
			this.searchFilterDiagram.get(searchFilter.fieldName).add(index);
		} else {
			List<Integer> indexs = Lists.newArrayList(index);
			this.searchFilterDiagram.put(searchFilter.fieldName, indexs);
		}
	}

	/**
	 * 把当前Page转换为Map条件的分页(前置条件为map条件和searchfilter的参数大小不一致)
	 * 
	 * @return
	 */
	public PageRequestVo convertToMapPage() {
		if (params.size() != searchFilters.size()) {
			for (SearchFilter searchFilter : searchFilters) {
				params.put(searchFilter.fieldName, searchFilter.value);
			}
		}
		return this;
	}

	/**
	 * 把当前对象转换为SearchFilter条件的分页(前置条件为map条件和searchfilter的参数大小不一致),searchOperator
	 * 默认为eq,connectorSymbol 默认为AND,removeEmptySearch默认为false
	 * 
	 * @return
	 */
	public PageRequestVo convertToEqualSearchFilterPage() {
		if (params.size() != searchFilters.size()) {
			for (String key : params.keySet()) {
				addSearchFilter(new SearchFilter(key, SearchOperator.eq, params.get(key)));
			}
		}
		return this;
	}

	/**
	 * 获取当前页数
	 * 
	 * @author samson
	 * @createTime 2016年7月8日 下午1:27:46
	 * @return
	 */
	public int getPageNo() {
		return length < 1 ? 0 : (start / length);
	}
}
