/**
 * @(#)RulesUtils.java 1.0.0 2015年12月16日 上午11:05:21
 * <p/>
 * Copyright © 2015 善林金融.  All rights reserved.
 */

package com.slfinance.redpack.core.extend.validate;

import com.slfinance.redpack.core.extend.validate.annotations.Rules;
import com.slfinance.redpack.core.extend.validate.annotations.ValidRules;
import com.slfinance.redpack.core.extend.validate.validator.SLValidator;
import com.slfinance.redpack.core.extend.validate.validator.SLValidatorMapAnnotation;
import com.slfinance.redpack.core.extend.validate.validator.SLValidatorValue;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author samson
 * @version $Revision:1.0.0, $Date: 2015年12月16日 上午11:05:21 $
 */
public class RulesUtils {

	public static SLValidator validateValidRules(Object object, Method method, int index) {
		ValidRules validRules = RulesCache.getAnnotationValidRulesByMethod(method);
		if (validRules != null) {
			for (Rules rules : validRules.value()) {
				if (rules.argsIndex() == index) {
					return validateRuels(object, rules);
				}
			}
		}
		return new SLValidatorValue();
	}

	@SuppressWarnings("unchecked")
	public static SLValidator validateRuels(Object object, Rules rules) {
		SLValidator slValidator = null;
		if (object instanceof Map) {
			slValidator = new SLValidatorMapAnnotation((Map<String, Object>) object, rules);
		} else {
			throw new RuntimeException("不支持的数据检验");
		}
		return slValidator;
	}

}
