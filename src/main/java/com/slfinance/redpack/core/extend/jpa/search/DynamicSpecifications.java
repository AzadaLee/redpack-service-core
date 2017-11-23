package com.slfinance.redpack.core.extend.jpa.search;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.jpa.criteria.CriteriaBuilderImpl;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;

import com.google.common.collect.Lists;
import com.slfinance.redpack.core.utils.SqlUtil;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class DynamicSpecifications {
	public static final String LIKE_PLACEHOLDER = "%";
	public static final String KEY_CONNECTION_POINT = ".";

	public static <T> Specification<T> fromSearchFilterToSpecification(final Collection<SearchFilter> filters, Class<T> clazz) {
		return new Specification<T>() {
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				CriteriaBuilderImpl builderImpl = (CriteriaBuilderImpl) builder;
				List<Predicate> predicates = new ArrayList<Predicate>();
				if (filters != null && !filters.isEmpty()) {
					for (SearchFilter filter : filters) {
						Predicate predicate = null;
						if (filter.removeEmptySearch && (filter.value == null || StringUtils.isBlank(filter.value.toString()))) {
							continue;
						}
						// nested path translate, 如Task的名为"user.name"的filedName,
						// 转换为Task.user.name属性
						String[] names = StringUtils.split(filter.fieldName, KEY_CONNECTION_POINT);
						Path expression = root.get(names[0]);
						for (int i = 1; i < names.length; i++) {
							expression = expression.get(names[i]);
						}

						switch (filter.searchOperator) {
						case eq:
							predicate = builder.equal(expression, filter.value);
							break;
						case ne:
							predicate = builder.notEqual(expression, (Comparable) filter.value);
							break;
						case gt:
							predicate = builder.greaterThan(expression, (Comparable) filter.value);
							break;
						case gte:
							predicate = builder.greaterThanOrEqualTo(expression, (Comparable) filter.value);
							break;
						case lt:
							predicate = builder.lessThan(expression, (Comparable) filter.value);
							break;
						case lte:
							predicate = builder.lessThanOrEqualTo(expression, (Comparable) filter.value);
							break;
						case prefixLike:
							predicate = builder.like(expression, LIKE_PLACEHOLDER + filter.value);
							break;
						case prefixNotLike:
							predicate = builder.notLike(expression, LIKE_PLACEHOLDER + filter.value);
							break;
						case suffixLike:
							predicate = builder.like(expression, filter.value + LIKE_PLACEHOLDER);
							break;
						case suffixNotLike:
							predicate = builder.notLike(expression, filter.value + LIKE_PLACEHOLDER);
							break;
						case like:
							predicate = builder.like(expression, LIKE_PLACEHOLDER + filter.value + LIKE_PLACEHOLDER);
							break;
						case notLike:
							predicate = builder.notLike(expression, LIKE_PLACEHOLDER + filter.value + LIKE_PLACEHOLDER);
							break;
						case isNull:
							predicate = builder.isNull(expression);
							break;
						case isNotNull:
							predicate = builder.isNotNull(expression);
							break;
						case in:
							if (filter.value != null && Collection.class.isAssignableFrom(filter.value.getClass())) {
								predicate = builderImpl.in(expression, (Collection) filter.value);
							} else {
								predicate = builderImpl.in(expression, filter.value);
							}
							break;
						case notIn:
							if (filter.value != null && Collection.class.isAssignableFrom(filter.value.getClass())) {
								predicate = builderImpl.in(expression, (Collection) filter.value);
							} else {
								predicate = builderImpl.in(expression, filter.value);
							}
							predicate = predicate.not();
							break;
						}

						switch (filter.connectorSymbol) {
						case AND:
							predicate = builder.and(predicate);
							break;
						case OR:
							predicate = builder.or(predicate);
							break;
						}
						predicates.add(predicate);
					}

				}
				return builder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		};
	}

	public static SqlArrayWrapper fromSearchFilterToSQLWrapper(final List<SearchFilter> filters) {
		StringBuilder sb = new StringBuilder();
		List<Object> parameters = Lists.newArrayList();
		if (filters != null && !filters.isEmpty()) {
			for (int i = 0; i < filters.size(); i++) {
				SearchFilter filter = filters.get(i);
				if (filter.removeEmptySearch && (filter.value == null || StringUtils.isBlank(filter.value.toString()))) {
					continue;
				}
				sb.append(SqlUtil.BLANK).append(filter.connectorSymbol).append(SqlUtil.BLANK);
				switch (filter.searchOperator) {
				case prefixLike:
				case prefixNotLike:
					sb.append(filter.fieldName).append(SqlUtil.BLANK).append(filter.searchOperator.getSymbol()).append(" ?").append(parameters.size()).append(SqlUtil.BLANK);
					parameters.add(LIKE_PLACEHOLDER + filter.value);
					break;
				case suffixLike:
				case suffixNotLike:
					sb.append(filter.fieldName).append(SqlUtil.BLANK).append(filter.searchOperator.getSymbol()).append(" ?").append(parameters.size()).append(SqlUtil.BLANK);
					parameters.add(filter.value + LIKE_PLACEHOLDER);
					break;
				case like:
				case notLike:
					sb.append(filter.fieldName).append(SqlUtil.BLANK).append(filter.searchOperator.getSymbol()).append(" ?").append(parameters.size()).append(SqlUtil.BLANK);
					parameters.add(LIKE_PLACEHOLDER + filter.value + LIKE_PLACEHOLDER);
					break;
				case isNull:
				case isNotNull:
					sb.append(filter.fieldName).append(SqlUtil.BLANK).append(filter.searchOperator.getSymbol()).append(SqlUtil.BLANK);
					break;
				default:
					sb.append(filter.fieldName).append(SqlUtil.BLANK).append(filter.searchOperator.getSymbol()).append(" ?").append(parameters.size()).append(SqlUtil.BLANK);
					parameters.add(filter.value);
					break;
				}
			}
		}
		return new SqlArrayWrapper(sb.toString(), parameters.toArray());
	}

	/**
	 * 把Sort 转换为 Order BY sql
	 * 
	 * @param sort
	 * @return
	 */
	public static String fromSortToOrderBySql(Sort sort) {
		StringBuilder sb = new StringBuilder();
		if (sort != null) {
			sb.append(SqlUtil.BLANK).append(SqlUtil.ORDER_BY).append(SqlUtil.BLANK);
			Order order = null;
			for (Iterator<Order> orders = sort.iterator(); orders.hasNext();) {
				order = orders.next();
				sb.append(order.getProperty()).append(SqlUtil.BLANK).append(order.getDirection().name()).append(SqlUtil.BLANK);
				if (orders.hasNext()) {
					sb.append(SqlUtil.COMMA).append(SqlUtil.BLANK);
				}
			}
		}
		return sb.toString();
	}

}