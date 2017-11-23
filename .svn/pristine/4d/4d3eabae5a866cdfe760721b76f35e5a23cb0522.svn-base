package com.slfinance.redpack.core.extend.jpa.auditor;

import org.springframework.data.domain.AuditorAware;

import com.slfinance.redpack.core.extend.springweb.service.LoginUserContextHolder;
import com.slfinance.redpack.core.vo.LoginUser;

/**
 * jpa domain 的监听器
 *
 * @author samson
 * @date 2016/3/23 16:01
 */
public class LoginUserAuditorAware implements AuditorAware<String> {
	@Override
	public String getCurrentAuditor() {
		LoginUser loginUser = LoginUserContextHolder.getLoginUser();
		return loginUser == null ? null : loginUser.getId();
	}
}
