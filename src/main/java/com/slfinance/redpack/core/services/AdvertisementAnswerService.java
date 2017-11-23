package com.slfinance.redpack.core.services;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.slfinance.redpack.core.entities.AdvertisementAnswer;
import com.slfinance.redpack.core.repositories.AdvertisementAnswerRepository;
import com.slfinance.redpack.core.services.base.BaseService;

@Service
public class AdvertisementAnswerService extends BaseService<AdvertisementAnswer, AdvertisementAnswerRepository> {

	/**
	 * 根据广告编号获取问题
	 * @param advertisementId
	 * @return
	 */
	public List<Map<String, Object>> findByAdvertisementId(String advertisementId){
		List<Map<String, Object>> result = repository.findByAdvertisementIdForMap(advertisementId);
		return result;
	}
	
	/**
	 * 根据广告id获取答案list
	 * @author SangLy
	 * @createTime 2016年11月3日 上午10:34:28
	 * @param advertisementId
	 * @return
	 */
	public List<AdvertisementAnswer> findAnswerListByAdvertisementId(String advertisementId){
		return repository.findByAdvertisementId(advertisementId);
	}
	
	/**
	 * 复制答案
	 * 
	 * @author SangLy
	 * @createTime 2016年11月10日 下午2:26:55
	 * @param advertisementAnswer
	 * @param advertisementId
	 * @return
	 */
	@Transactional
	public AdvertisementAnswer copyAdvertisementAnswerByAdvertisement(AdvertisementAnswer advertisementAnswer, String advertisementId) {
		AdvertisementAnswer newAdvertisementAnswer = new AdvertisementAnswer();
		newAdvertisementAnswer.setAdvertisementId(advertisementId);
		newAdvertisementAnswer.setAnswerContent(advertisementAnswer.getAnswerContent());
		return repository.save(newAdvertisementAnswer);
	}
}
