package com.slfinance.redpack.core.extend.jpa.search;

public class SearchFilter {

	/**
	 * 字段名称
	 */
	public String fieldName;
	/**
	 * 操作符
	 */
	public SearchOperator searchOperator;
	/**
	 * 值
	 */
	public Object value;
	/**
	 * 连接符{@code ConnectorSymbol} AND | OR
	 */
	public ConnectorSymbol connectorSymbol = ConnectorSymbol.AND;
	/**
	 * 去掉空参的条件
	 */
	public boolean removeEmptySearch;

	public SearchFilter(String fieldName, SearchOperator searchOperator) {
		this.fieldName = fieldName;
		this.searchOperator = searchOperator;
	}

	public SearchFilter(String fieldName, SearchOperator searchOperator, Object value) {
		this(fieldName, searchOperator);
		this.value = value;
	}

	public SearchFilter(String fieldName, SearchOperator searchOperator, Object value, ConnectorSymbol connectorSymbol) {
		this(fieldName, searchOperator, value);
		this.connectorSymbol = connectorSymbol;

	}

	public SearchFilter(String fieldName, SearchOperator searchOperator, Object value, boolean removeEmptySearch) {
		this(fieldName, searchOperator, value);
		this.removeEmptySearch = removeEmptySearch;

	}

	public SearchFilter(String fieldName, SearchOperator searchOperator, Object value, ConnectorSymbol connectorSymbol, boolean removeEmptySearch) {
		this(fieldName, searchOperator, value);
		this.removeEmptySearch = removeEmptySearch;

	}
}
