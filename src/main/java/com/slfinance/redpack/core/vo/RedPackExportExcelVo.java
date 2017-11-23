/** 
 * @(#)RedPackExportExcelVo.java 1.0.0 2016年8月17日 上午10:29:12  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */ 

package com.slfinance.redpack.core.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**   
 * 
 *  
 * @author  taoxm
 * @version $Revision:1.0.0, $Date: 2016年8月17日 上午10:29:12 $ 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RedPackExportExcelVo {
	
	private String redpackType;
	
	private String status;
	
	private Double minAmount;
	
	private Double maxAmount;
	
	private String shibboleth;
	
	private String advertiserName;
	
	private String advertisementCode;
	
	private String startTimePoint;
	
	private String endTimePoint;
}
