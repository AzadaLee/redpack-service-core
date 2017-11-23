/**
 * @(#)AnnotationValidateAspect.java 1.0.0 2014年12月23日 下午1:00:00
 * <p/>
 * Copyright © 2014 善林金融.  All rights reserved.
 */

package com.slfinance.redpack.core.extend.validate;

import com.slfinance.redpack.core.exception.SLException;
import com.slfinance.redpack.core.extend.jpa.page.PageRequestVo;
import com.slfinance.redpack.core.extend.validate.annotations.Rules;
import com.slfinance.redpack.core.extend.validate.validator.SLValidator;
import com.slfinance.redpack.core.extend.validate.validator.SLValidatorMapAnnotation;
import com.slfinance.redpack.core.extend.validation.CustomerizeLocalValidatorFactoryBean;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolation;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

/**
 * 注解验证切面 目前只针对一个参数的如果有多个参数则默认以第一个为准
 *
 * @author 孟山
 * @version $Revision:1.0.0, $Date: 2014年12月23日 下午1:00:00 $
 */
@Aspect
public class AnnotationValidateAspect {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CustomerizeLocalValidatorFactoryBean validatorFactoryBean;

	public AnnotationValidateAspect(CustomerizeLocalValidatorFactoryBean customerizeLocalValidatorFactoryBean) {
		this.validatorFactoryBean = customerizeLocalValidatorFactoryBean;
	}

	/**
	 * 环绕通知 验证不通过则直接抛出异常信息
	 *
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */
	@Around("com.slfinance.redpack.core.SystemArchitecture.controller()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		Method method = methodSignature.getMethod();
		Object[] args = joinPoint.getArgs();
		Rules rules = RulesCache.getAnnotationRulesByMethod(method);
		if (args.length > 0 && (rules != null || PageRequestVo.class.equals(method.getParameterTypes()[0]))) {
			Object args0 = args[0];
			SLValidator slValidator = validate(args0, rules);
			if (slValidator != null && slValidator.hasErrors()) {
				String messages = slValidator.getMessages().iterator().next().getMessage();
				logger.warn("{} 注解数据校验不通过:入参为{},校验结果为:{}", method.toString(), args0.toString(), messages);
				throw new SLException(messages);
			}
		}
		return joinPoint.proceed();
	}

	/**
	 * 支持验证类型
	 * <p>
	 * Map
	 * </p>
	 * <p>
	 * <p/>
	 * </p>
	 *
	 * @param parameters
	 * @param rules
	 * @return
	 * @throws SLException
	 */
	@SuppressWarnings("unchecked")
	private SLValidator validate(Object parameters, Rules rules) throws SLException {
		SLValidator slValidator = null;
		if (parameters instanceof Map) {
			slValidator = new SLValidatorMapAnnotation((Map<String, Object>) parameters, rules);
		} else if (parameters instanceof PageRequestVo) {
			Set<ConstraintViolation<Object>> set = validatorFactoryBean.validate(parameters);
			if (set.size() > 0) {
				throw new SLException(set.iterator().next().getMessage());
			}
			if (rules != null) {
				PageRequestVo pageRequestVo = (PageRequestVo) parameters;
				slValidator = new SLValidatorMapAnnotation(pageRequestVo.getParams(), rules);
			}
		} else {
			throw new SLException("数据验证失败");
		}
		return slValidator;
	}
}
