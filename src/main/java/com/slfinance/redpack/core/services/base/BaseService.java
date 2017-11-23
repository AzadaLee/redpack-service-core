/**
 * @(#)BaseService.java 1.0.0 2015年12月12日 上午10:32:27
 * <p/>
 * Copyright © 2015 善林金融.  All rights reserved.
 */

package com.slfinance.redpack.core.services.base;

import com.slfinance.redpack.core.entities.base.Entity;
import com.slfinance.redpack.core.repositories.base.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 基于PO 的service基类
 *
 * @author samson
 * @version $Revision:1.0.0, $Date: 2015年12月12日 上午10:32:27 $
 */
public abstract class BaseService<E extends Entity, R extends BaseRepository<E>> extends AbstractService {

	@Autowired
	protected R repository;

	/**
	 * 保存单个实体
	 *
	 * @param m
	 *            实体
	 * @return 返回保存的实体
	 */
	@Transactional
	public E save(E m) {
		return repository.save(m);
	}

	@Transactional
	public E saveAndFlush(E m) {
		m = save(m);
		repository.flush();
		return m;
	}

	/**
	 * 更新单个实体
	 *
	 * @param m
	 *            实体
	 * @return 返回更新的实体
	 */
	@Transactional
	public E update(E m) {
		return repository.save(m);
	}

	/**
	 * 根据主键删除相应实体
	 *
	 * @param id
	 *            主键
	 */
	@Transactional
	public void delete(String id) {
		repository.delete(id);
	}

	/**
	 * 删除实体
	 *
	 * @param m
	 *            实体
	 */
	@Transactional
	public void delete(E m) {
		repository.delete(m);
	}

	/**
	 * 按照主键查询
	 *
	 * @param id
	 *            主键
	 * @return 返回id对应的实体
	 */
	public E findOne(String id) {
		return repository.findOne(id);
	}

	/**
	 * 实体是否存在
	 *
	 * @param id
	 *            主键
	 * @return 存在 返回true，否则false
	 */
	public boolean exists(String id) {
		return repository.exists(id);
	}

	/**
	 * 统计实体总数
	 *
	 * @return 实体总数
	 */
	public long count() {
		return repository.count();
	}

	/**
	 * 查询所有实体
	 *
	 * @return
	 */
	public List<E> findAll() {
		return repository.findAll();
	}
}
