/** 
 * @(#)BankRepository.java 1.0.0 2016年11月4日 下午7:28:40  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */

package com.slfinance.redpack.core.repositories;

import com.slfinance.redpack.core.constants.enums.RecordType;
import com.slfinance.redpack.core.entities.Bank;
import com.slfinance.redpack.core.repositories.base.BaseRepository;

/**
 * 
 * 
 * @author SangLy
 * @version $Revision:1.0.0, $Date: 2016年11月4日 下午7:28:40 $
 */
public interface BankRepository extends BaseRepository<Bank> {

	Bank findByCustomerIdAndRecordType(String customerId,RecordType recordType);
}
