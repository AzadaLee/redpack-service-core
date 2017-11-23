package com.slfinance.redpack.core.entities.base;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.GenericGenerator;

/**
 * 所有PO的基类
 *
 * @author samson
 * @date 2016/3/1 19:40
 */
@MappedSuperclass
@Data
@NoArgsConstructor
public abstract class Entity implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 4935392417745122366L;

	/**
	 * UUID主键
	 */
	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@GeneratedValue(generator = "uuid")
	private String id;

	/**
	 * 版本号
	 */
	@Version
	private Integer version = 0;

	/**
	 * 清空数据
	 * 
	 * @author samson
	 * @createTime 2016年11月11日 下午2:33:00
	 */
	public void clean() {
		this.id = null;
		this.version = null;
	}
}
