/** 
 * @(#)CodeGeneratorRepositoryCustomer.java 1.0.0 2016年7月26日 下午5:09:36  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */

package com.slfinance.redpack.core.repositories.custom.impl;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Repository;

import com.slfinance.redpack.core.repositories.base.BaseRepositoryCustImpl;
import com.slfinance.redpack.core.repositories.custom.CodeGeneratorRepositoryCust;

/**
 * 
 * 
 * @author samson
 * @version $Revision:1.0.0, $Date: 2016年7月26日 下午5:09:36 $
 */
@Repository
public class CodeGeneratorRepositoryCustImpl extends BaseRepositoryCustImpl implements CodeGeneratorRepositoryCust {

	public int getNextVal(String sequenceName) {
		return NumberUtils.toInt(entityManager.createNativeQuery("SELECT " + sequenceName + ".NEXTVAL FROM DUAL").getSingleResult().toString());
	}

	@Override
	public int getCurrentVal(String sequenceName) {
		return NumberUtils.toInt(entityManager.createNativeQuery("SELECT " + sequenceName + ".CURRVAL FROM DUAL").getSingleResult().toString());
	}

}
