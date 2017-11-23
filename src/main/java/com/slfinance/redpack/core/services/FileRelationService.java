package com.slfinance.redpack.core.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.slfinance.redpack.core.constants.TableConstant;
import com.slfinance.redpack.core.entities.FileRelation;
import com.slfinance.redpack.core.repositories.FileRelationRepository;
import com.slfinance.redpack.core.services.base.BaseService;

@Service
public class FileRelationService extends BaseService<FileRelation, FileRelationRepository>{
	
	public List<FileRelation> findByFileId(String fileId){
		return repository.findByFileId(fileId);
	}
	
	/**
	 * 根据广告id查询广告文件关联表
	 * @author SangLy
	 * @createTime 2016年11月2日 下午4:43:29
	 * @param advertisementId
	 * @return
	 */
	public List<FileRelation> findByAdvertisementAndRelatePrimary(String advertisementId) {
		return repository.findByRelateTableAndRelatePrimary(TableConstant.T_ADVERTISEMENT, advertisementId);
	}
}
