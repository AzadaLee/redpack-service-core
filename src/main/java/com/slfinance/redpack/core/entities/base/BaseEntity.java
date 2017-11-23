package com.slfinance.redpack.core.entities.base;

import java.util.Date;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author samson
 * @date 2016/3/23 13:56
 */
@MappedSuperclass
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity extends Entity {

	/**
	 *
	 */
	private static final long serialVersionUID = -1593605261420209808L;
	/**
	 * 创建人
	 */
	@CreatedBy
	private String createdUser;
	/**
	 * 创建时间
	 */
	@CreatedDate
	private Date createdDate;
	/**
	 * 最后修改人
	 */
	@LastModifiedBy
	private String lastModifiedUser;
	/**
	 * 最后修改时间
	 */
	@LastModifiedDate
	private Date lastModifiedDate;

	/**
	 * 备注
	 */
	private String memo;

	/**
	 * 创建副本
	 * 
	 * @author samson
	 * @createTime 2016年11月11日 下午2:54:45
	 * @return
	 */
	public <T extends BaseEntity> T createDuplicate(Class<T> clazz) {
		T t;
		try {
			t = clazz.newInstance();
			BeanUtils.copyProperties(t, this);
			t.clean();
			return t;
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * 清空字段值
	 */
	public void clean() {
		super.clean();
		this.createdDate = null;
		this.createdUser = null;
		this.lastModifiedDate = null;
		this.lastModifiedUser = null;
		this.memo = null;
	}
}
