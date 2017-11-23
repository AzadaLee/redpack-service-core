package com.slfinance.redpack.core.extend.springweb.method;

import com.slfinance.redpack.core.extend.springweb.service.LoginUserContextHolder;
import com.slfinance.redpack.core.vo.LoginUser;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 用于对springmvc中controller方法的参数有{@code CurrentUser} 注解的参数进行自动注入当前操作用户ID
 *
 * @author samson
 *
 */
public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

	public CurrentUserMethodArgumentResolver() {
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(CurrentUserId.class) || parameter.hasParameterAnnotation(CurrentUser.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		LoginUser loginUser = LoginUserContextHolder.getLoginUser();
		if (parameter.hasParameterAnnotation(CurrentUserId.class)) {
			return loginUser == null ? null : loginUser.getId();
		}
		return loginUser;
	}
}
