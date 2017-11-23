/** 
 * @(#)JobsController.java 1.0.0 2016年11月18日 上午11:59:03  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */

package com.slfinance.redpack.core.controller.crm;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.slfinance.redpack.core.controller.base.BaseController;
import com.slfinance.redpack.core.jobs.AbstractJob;
import com.slfinance.redpack.core.utils.SpringUtils;

/**
 * 
 * 
 * @author samson
 * @version $Revision:1.0.0, $Date: 2016年11月18日 上午11:59:03 $
 */
@RestController
@RequestMapping("/crm/jobs")
public class JobsController extends BaseController {

	@RequestMapping("execute")
	@ResponseBody
	public String execute(String beanName) {
		try {
			Object object = SpringUtils.getBean("com.slfinance.redpack.core.jobs." + beanName);
			AbstractJob job = (AbstractJob) object;
			job.invoke();
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "success";
	}

}
