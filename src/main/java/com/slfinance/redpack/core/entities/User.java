/**
 * @(#)User.java 1.0.0 2016年7月25日 下午2:18:49
 * <p/>
 * Copyright © 2016 善林金融.  All rights reserved.
 */

package com.slfinance.redpack.core.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import com.slfinance.redpack.core.constants.enums.UserStatus;
import com.slfinance.redpack.core.entities.base.BaseEntity;

/**
 * 后台用户表
 *
 * @author SangLy
 * @version $Revision:1.0.0, $Date: 2016年7月25日 下午2:18:49 $
 */
@Entity
@Table(name = "RP_T_USER")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class User extends BaseEntity {

    private static final long serialVersionUID = -5000000000000000013L;

    /**
     * 登录名
     */
    private String loginName;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 姓名
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 密码修改次数(目前主要用于第一次登录强制修改密码)
     */
    private Integer repasswordCount;
	
    @Enumerated(EnumType.STRING)
	private UserStatus status = UserStatus.正常;

}
