/** 
 * @(#)UpdatePasswordUser.java 1.0.0 2016年8月16日 上午10:38:40  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */ 

package com.slfinance.redpack.core.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.validator.constraints.NotBlank;

/**   
 * 后台用户修改密码
 *  
 * @author  taoxm
 * @version $Revision:1.0.0, $Date: 2016年8月16日 上午10:38:40 $ 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePasswordUserVo {
	
	private String id;
	
	@NotBlank(message="400002")
	private String password;
	
	@NotBlank(message="400001")
	private String oldPassword;
}
