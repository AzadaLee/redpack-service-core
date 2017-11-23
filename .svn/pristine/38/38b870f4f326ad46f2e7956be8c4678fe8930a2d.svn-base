/*
 * Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.slfinance.redpack.core.configs;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.entity.ContentType;
import org.hibernate.validator.HibernateValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.SynthesizingMethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.util.StreamUtils;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ServletModelAttributeMethodProcessor;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.annotation.JsonView;
import com.slfinance.redpack.core.extend.fastjson.Serialize;
import com.slfinance.redpack.core.extend.springweb.method.CurrentUserMethodArgumentResolver;
import com.slfinance.redpack.core.extend.springweb.method.CustomerizeServletModelAttributeMethodProcessor;
import com.slfinance.redpack.core.extend.springweb.method.PageRequestMethodProcessor;
import com.slfinance.redpack.core.extend.springweb.service.LoginUserContextHolder;
import com.slfinance.redpack.core.extend.validation.CustomerizeLocalValidatorFactoryBean;
import com.slfinance.redpack.core.utils.JSONUtils;
import com.slfinance.redpack.core.vo.LoginUser;

/**
 * web配置,请不要使用{@code EnableWebMvc},此处需要自定义{@code RequestMappingHandlerAdapter}
 *
 * @author samson
 */
@EnableWebMvc
@Configuration
public class WebConfig extends WebMvcAutoConfigurationAdapter {

	private @Autowired HttpMessageConverters converters;

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(currentUserMethodArgumentResolver());
		argumentResolvers.add(new PageRequestMethodProcessor(converters.getConverters()));
	}

	@Override
	public Validator getValidator() {
		return localValidatorFactoryBean();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginUserHandlerInterceptor());
	}

	/**
	 * 参数中添加{@code @CurrentUserId}注解的自动注入当前登录用户id,并且可以做登录操作和权限控制
	 *
	 * @return
	 */
	@Bean
	public HandlerMethodArgumentResolver currentUserMethodArgumentResolver() {
		return new CurrentUserMethodArgumentResolver();
	}

	@Bean
	public LoginUserHandlerInterceptor loginUserHandlerInterceptor() {
		return new LoginUserHandlerInterceptor();
	}

	/**
	 * 重定义校验,添加自定义校验方法
	 *
	 * @return
	 */
	@Bean
	public CustomerizeLocalValidatorFactoryBean localValidatorFactoryBean() {
		CustomerizeLocalValidatorFactoryBean localValidatorFactoryBean = new CustomerizeLocalValidatorFactoryBean();
		localValidatorFactoryBean.setProviderClass(HibernateValidator.class);
		return localValidatorFactoryBean;
	}

	@Bean
	public MultipartResolver commonsMultipartResolver(RequestMappingHandlerAdapter requestMappingHandlerAdapter) throws Exception {
		List<HandlerMethodArgumentResolver> argumentResolvers = new ArrayList<HandlerMethodArgumentResolver>(requestMappingHandlerAdapter.getArgumentResolvers().size());
		List<HandlerMethodArgumentResolver> initBinderArgumentResolvers = new ArrayList<HandlerMethodArgumentResolver>(requestMappingHandlerAdapter.getInitBinderArgumentResolvers().size());
		// 当前方法
		Method currentMethod = this.getClass().getMethod(Thread.currentThread().getStackTrace()[1].getMethodName(), RequestMappingHandlerAdapter.class);
		// 测试methodParameter主要获取ServletModelAttributeMethodProcessor中的annotationNotRequired值
		MethodParameter testMethodParameter = new SynthesizingMethodParameter(currentMethod, 0);
		for (HandlerMethodArgumentResolver hmar : requestMappingHandlerAdapter.getArgumentResolvers()) {
			if (ServletModelAttributeMethodProcessor.class.isAssignableFrom(hmar.getClass())) {
				hmar = new CustomerizeServletModelAttributeMethodProcessor(hmar.supportsParameter(testMethodParameter));
			}
			argumentResolvers.add(hmar);
		}
		for (HandlerMethodArgumentResolver hmar : requestMappingHandlerAdapter.getInitBinderArgumentResolvers()) {
			if (ServletModelAttributeMethodProcessor.class.isAssignableFrom(hmar.getClass())) {
				hmar = new CustomerizeServletModelAttributeMethodProcessor(hmar.supportsParameter(testMethodParameter));
			}
			initBinderArgumentResolvers.add(hmar);
		}
		requestMappingHandlerAdapter.setArgumentResolvers(argumentResolvers);
		requestMappingHandlerAdapter.setInitBinderArgumentResolvers(initBinderArgumentResolvers);

		return new CommonsMultipartResolver();
	}

	/**
	 * @author samson
	 */
	@ControllerAdvice
	static class SerializeResponseBodyAdvice implements ResponseBodyAdvice<Object> {
		private Logger logger = LoggerFactory.getLogger(this.getClass());

		@Override
		public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
			return returnType.getMethodAnnotation(JsonView.class) == null && returnType.getMethodAnnotation(Serialize.class) != null;
		}

		@Override
		public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
			try {
				ServletServerHttpResponse servletServerHttpResponse = (ServletServerHttpResponse) response;
				servletServerHttpResponse.getServletResponse().setHeader("Content-Type", ContentType.APPLICATION_JSON.toString());
				StreamUtils.copy(JSONUtils.toJSONString(body, returnType.getMethod(), SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullBooleanAsFalse), Charset.defaultCharset(), response.getBody());
			} catch (IOException e) {
				logger.error("Serialize 序列化失败", e);
			}
			return null;
		}
	}

	/**
	 * 把当前登录用户信息放进入参中
	 * 
	 * @author samson
	 *
	 */
	@ControllerAdvice
	static class SerializeRequestBodyAdvice extends RequestBodyAdviceAdapter {
		@Override
		public boolean supports(MethodParameter arg0, Type arg1, Class<? extends HttpMessageConverter<?>> arg2) {
			return true;
		}

		@SuppressWarnings({ "unchecked", "rawtypes" })
		@Override
		public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
			Object object = super.afterBodyRead(body, inputMessage, parameter, targetType, converterType);
			if (Map.class.isAssignableFrom(object.getClass())) {
				((Map) object).put(LoginUserContextHolder.LOGIN_USER_KEY, LoginUserContextHolder.getLoginUser());
			}
			return object;
		}
	}

	/**
	 * 拦截器获取当前登录用户
	 *
	 * @author samson
	 */
	static class LoginUserHandlerInterceptor implements HandlerInterceptor {

		@Override
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
			String userId = request.getHeader("me");
			if (StringUtils.isNotBlank(userId)) {
				LoginUserContextHolder.setLoginUser(new LoginUser(userId));
			} else {
				LoginUserContextHolder.setLoginUser(null);
			}
			return true;
		}

		@Override
		public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

		}

		@Override
		public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

		}

	}
}
