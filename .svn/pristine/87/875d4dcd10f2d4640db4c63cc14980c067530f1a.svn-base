package com.slfinance.redpack.core.repositories.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import com.slfinance.redpack.core.entities.base.Entity;

/**
 * 数据仓库基类
 *
 * @author samson
 */
@NoRepositoryBean
public interface BaseRepository<E extends Entity> extends JpaRepository<E, String>, JpaSpecificationExecutor<E>, SearchFilterRepository<E> {

}
