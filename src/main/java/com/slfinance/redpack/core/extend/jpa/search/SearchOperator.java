/** 
 * @(#)SearchOperator.java 1.0.0 2016年6月28日 上午11:16:05  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */

package com.slfinance.redpack.core.extend.jpa.search;

/**
 * 搜索符
 * 
 * @author samson
 * @version $Revision:1.0.0, $Date: 2016年6月28日 上午11:16:05 $
 */
public enum SearchOperator {
	eq("等于", "="), ne("不等于", "!="), gt("大于", ">"), gte("大于等于", ">="), lt("小于", "<"), lte("小于等于", "<="), prefixLike("前缀模糊匹配", "like"), prefixNotLike("前缀模糊不匹配", "not like"), suffixLike("后缀模糊匹配", "like"), suffixNotLike("后缀模糊不匹配", "not like"), like("模糊匹配", "like"), notLike("不匹配", "not like"), isNull("空", "is null"), isNotNull("非空", "is not null"), in("包含", "in"), notIn("不包含", "not in");

	/**
	 * 描述
	 */
	private String info;
	/**
	 * 符号
	 */
	private String symbol;

	SearchOperator(String info, String symbol) {
		this.info = info;
		this.symbol = symbol;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

}
