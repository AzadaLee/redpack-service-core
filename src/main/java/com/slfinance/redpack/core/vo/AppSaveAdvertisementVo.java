/** 
 * @(#)AdvertisementAppVo.java 1.0.0 2016年11月3日 下午1:57:25  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */ 

package com.slfinance.redpack.core.vo;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**   
 * 
 *  
 * @author  SangLy
 * @version $Revision:1.0.0, $Date: 2016年11月3日 下午1:57:25 $ 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppSaveAdvertisementVo {
	
	private String id;
	
	@NotBlank(message="500015")
	private String title;
	
	@NotBlank(message="500014")
	private String logo;
	
	@NotBlank(message="500002")
	private String content;
	
	private String hyperlink;
	
	@NotNull(message="500003")
	private List<Map<String, Object>> files;
	
	@NotNull(message="500003")
	private List<Map<String, Object>> answers;
	
	@NotBlank(message="500016")
	private String correctAnswer;

}
