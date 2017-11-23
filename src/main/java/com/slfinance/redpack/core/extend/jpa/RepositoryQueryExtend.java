/**
 * @(#)RepositoryQueryExtend.java 1.0.0 2016年1月6日 下午4:39:48
 * <p/>
 * Copyright © 2016 善林金融.  All rights reserved.
 */

package com.slfinance.redpack.core.extend.jpa;

import com.slfinance.redpack.common.utils.StringUtil;
import com.slfinance.redpack.core.extend.jpa.page.PageRequestVo;
import com.slfinance.redpack.core.extend.jpa.page.PageResponse;
import com.slfinance.redpack.core.extend.jpa.page.QueryExtend;
import com.slfinance.redpack.core.extend.jpa.page.ReturnMapping;
import com.slfinance.redpack.core.utils.SqlUtil;
import lombok.Getter;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.JpaParameters;
import org.springframework.data.repository.query.Parameter;
import org.springframework.data.repository.query.RepositoryQuery;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * @author samson
 * @version $Revision:1.0.0, $Date: 2016年1月6日 下午4:39:48 $
 */
@Getter
public class RepositoryQueryExtend {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private Method method;
	/**
	 * spring jpa的repositoryQuery
	 */
	private RepositoryQuery repositoryQuery;

	private JpaParameters jpaParameters;

	/**
	 * 解析{@code QueryExtend}中的 {@code ReturnMapping}对应的map
	 */
	private Map<String, String> keyMappings = new HashMap<String, String>();

	/**
	 * 返回值类型
	 */
	private Class<?> returnType;
	/**
	 * 返回值类型的第一个泛型类型
	 */
	private Class<?> returnGenericType;

	/**
	 * 返回值标识符(1:pageResponse,2:List,3:单个对象)
	 */
	private int returnSymbol = 3;

	/**
	 * 原始sql
	 */
	private String originSql;
	/**
	 * 需做校验替换的sql匹配(以?() 封装)
	 */
	private String[] matchers;

	private QueryExtend queryExtend;

	private RowMapper<?> rowMapper;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public RepositoryQueryExtend(Method method, RepositoryQuery repositoryQuery) {
		queryExtend = AnnotationUtils.getAnnotation(method, QueryExtend.class);
		if (queryExtend == null) {
			throw new RuntimeException(String.format("%s方法不支持自定义扩展", method));
		}

		this.method = method;
		this.repositoryQuery = repositoryQuery;
		this.jpaParameters = (JpaParameters) repositoryQuery.getQueryMethod().getParameters();

		this.originSql = AnnotationUtils.getAnnotation(method, Query.class).value();
		this.matchers = SqlUtil.weedSqlArgs(originSql);

		for (ReturnMapping returnMapping : queryExtend.value()) {
			keyMappings.put(returnMapping.from(), returnMapping.to());
		}

		returnType = method.getReturnType();
		Type type = method.getGenericReturnType();
		if (ParameterizedType.class.isAssignableFrom(type.getClass())) {
			try {
				type = ((ParameterizedType) type).getActualTypeArguments()[0];
				if (ParameterizedType.class.isAssignableFrom(type.getClass())) {
					returnGenericType = (Class<?>) ((ParameterizedType) type).getRawType();
				} else {
					returnGenericType = (Class<?>) type;
				}
			} catch (Exception e) {
				logger.error("泛型解析有误", e);
			}
		} else {
			type = returnType.getGenericSuperclass();
			if (type != null && ParameterizedType.class.isAssignableFrom(type.getClass())) {
				ParameterizedType returnGeneric = (ParameterizedType) type;
				type = returnGeneric.getActualTypeArguments()[0];
				if (ParameterizedType.class.isAssignableFrom(type.getClass())) {
					returnGenericType = (Class<?>) ((ParameterizedType) type).getRawType();
				}
			}
		}

		Class<?> return2RowMapper = returnType;
		if (PageResponse.class.isAssignableFrom(returnType)) {
			returnSymbol = 1;
			return2RowMapper = returnGenericType;
		} else if (List.class.isAssignableFrom(returnType)) {
			returnSymbol = 2;
			return2RowMapper = returnGenericType;
		}
		if (Map.class.isAssignableFrom(return2RowMapper)) {
			rowMapper = new MapRowMapper(keyMappings, queryExtend.toCamel());
		} else if (BeanUtils.isSimpleValueType(return2RowMapper)) {
			rowMapper = new SingleColumnRowMapper(return2RowMapper);
		} else {
			rowMapper = new BeanPropertyRowMapper(return2RowMapper);
		}
	}

	/**
	 * 获取优化sql对象
	 *
	 * @param args
	 * @return
	 */
	public OptimizeSql getOptimizeSql(Object[] args) {
		return new DefaultOptimizeSqlImpl(args);
	}

	/**
	 * 执行sql并返回结果
	 *
	 * @param namedParameterJdbcTemplate
	 * @param optimizeSql
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object execute(NamedParameterJdbcTemplate namedParameterJdbcTemplate, OptimizeSql optimizeSql) {
		Object result = null;
		switch (returnSymbol) {
		case 1:
			PageRequestVo pageRequest = optimizeSql.getPageArg();
			if (pageRequest == null) {
				List list = namedParameterJdbcTemplate.query(optimizeSql.getSql(), optimizeSql.getParameters(), rowMapper);
				result = new PageResponse(list.size(), list);
			} else {
				int total = namedParameterJdbcTemplate.queryForObject(SqlUtil.getTotalSql(optimizeSql.getSql()), optimizeSql.getParameters(), Integer.class);
				result = new PageResponse(total, total < 1 ? new ArrayList(0) : namedParameterJdbcTemplate.query(SqlUtil.getPageSql(optimizeSql.getSql(), pageRequest.getStart(), pageRequest.getLength()), optimizeSql.getParameters(), rowMapper));
			}
			break;
		case 2:
			result = namedParameterJdbcTemplate.query(optimizeSql.getSql(), optimizeSql.getParameters(), rowMapper);
			break;
		case 3:
			try {
				result = namedParameterJdbcTemplate.queryForObject(optimizeSql.getSql(), optimizeSql.getParameters(), rowMapper);
			} catch (EmptyResultDataAccessException e) {
				result = null;
			}
			break;
		}
		return result;
	}

	/**
	 * sql优化默认实现
	 *
	 * @author samson
	 */
	private final class DefaultOptimizeSqlImpl implements OptimizeSql {

		private PageRequestVo pageArg = null;
		/**
		 * 原始入参
		 */
		private Object[] args;

		/**
		 * 优化后的sql
		 */
		private StringBuilder sqlBuilder;

		/**
		 * 优化后的参数map
		 */
		private Map<String, Object> parameters = new LinkedHashMap<String, Object>();

		public DefaultOptimizeSqlImpl(Object[] args) {
			this.args = args;
			this.sqlBuilder = new StringBuilder(originSql.length() + 10);
			initParameters();
			optimize();
		}

		@SuppressWarnings({ "unchecked", "rawtypes" })
		protected void initParameters() {
			for (int i = 0; i < jpaParameters.getNumberOfParameters(); i++) {
				Parameter pp = jpaParameters.getParameter(i);
				if (PageRequestVo.class.isAssignableFrom(pp.getType())) {
					pageArg = (PageRequestVo) args[i];
					parameters.putAll(pageArg.getParams());
				} else if (Map.class.isAssignableFrom(pp.getType())) {
					parameters.putAll((Map) args[pp.getIndex()]);
				} else if (StringUtils.isNotBlank(pp.getName())) {
					parameters.put(pp.getName(), args[pp.getIndex()]);
				}
			}
		}

		protected void optimize() {
			int start = 0;
			int end = originSql.length();
			for (String argName : matchers) {
				int index = originSql.indexOf(argName, start);
				sqlBuilder.append(originSql, start, index);
				String placeholderName = SqlUtil.getPlaceholderName(argName);
				Object arg = parameters.get(placeholderName);
				if (StringUtils.isBlank(ObjectUtils.toString(arg, null))) {
					String previewWord = StringUtil.previewWord(originSql, index - 1);
					if (previewWord.equalsIgnoreCase(SqlUtil.OR)) {
						sqlBuilder.append(SqlUtil.NEVER_SET_UP);
					} else {
						sqlBuilder.append(SqlUtil.ALWAYS_SET_UP);
					}
				} else {
					sqlBuilder.append(argName.substring(1, argName.length() - 1));
				}

				start = index + argName.length();
				end = originSql.length();
			}
			sqlBuilder.append(originSql, start, end);
		}

		@SuppressWarnings("unused")
		private Object getParamster(String key) {
			Object result = null;
			for (int i = 0; i < jpaParameters.getNumberOfParameters(); i++) {
				Parameter pp = jpaParameters.getParameter(i);
				if (BeanUtils.isSimpleValueType(pp.getType()) || pp.getType().isArray()) {
					if (pp.getName() != null && pp.getName().equals(key)) {
						result = args[i];
					}
				} else if (PageRequestVo.class.isAssignableFrom(pp.getType())) {
					result = ((PageRequestVo) args[i]).getParam(key);
				} else {
					try {
						result = PropertyUtils.getProperty(args[i], key);
					} catch (Exception e) {
						logger.error("数据提取有误", e);
					}
				}
				if (result != null) {
					break;
				}
			}
			return result;
		}

		@Override
		public String getSql() {
			return sqlBuilder.toString();
		}

		@Override
		public Map<String, Object> getParameters() {
			return parameters;
		}

		@Override
		public Object[] getArgs() {
			return args;
		}

		@Override
		public PageRequestVo getPageArg() {
			return pageArg;
		}
	}
}
