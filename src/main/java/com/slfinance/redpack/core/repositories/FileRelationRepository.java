package com.slfinance.redpack.core.repositories;

import java.util.List;

import com.slfinance.redpack.core.entities.FileRelation;
import com.slfinance.redpack.core.repositories.base.BaseRepository;

public interface FileRelationRepository extends BaseRepository<FileRelation>{

	/**
	 * 
	 * @param fileId
	 * @return
	 */
	public List<FileRelation> findByFileId(String fileId);
	
	/**
	 * 
	 * @author SangLy
	 * @createTime 2016年11月2日 下午5:30:43
	 * @param relateTable
	 * @param relatePrimary
	 * @return
	 */
	public List<FileRelation> findByRelateTableAndRelatePrimary(String relateTable,String relatePrimary);
}
