/** 
 * @(#)FormatPrefixUrl.java 1.0.0 2016年8月24日 下午7:34:59  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */

package com.slfinance.redpack.core.utils;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.slfinance.redpack.common.utils.FormatUtils;
import com.slfinance.redpack.core.extend.jpa.page.PageResponse;

/**
 * 
 * 
 * @author SangLy
 * @version $Revision:1.0.0, $Date: 2016年8月24日 下午7:34:59 $
 */
public class FormatPrefixUrl {

	/**
	 * 
	 * @author SangLy
	 * @createTime 2016年8月23日 下午7:28:50
	 * @param staticResourceProxyURI
	 *            目标前缀
	 * @param targeList
	 *            目标list
	 * @param targetUrl
	 *            需要添加域名域名前缀中的key
	 * 
	 *            <pre>
	 *      		适用于：List<Map<String, Object>> 例如下面结构中修改 为下面targetUrl1 和targetUrl2 统一添加 www.baidu.com
	 *      			result			List<Map<String, Object>>
	 *      			id                String                        
	 *             		targetUrl1        String                      例如：值为，           /sdlfasflj.jpg
	 *            		targetUrl2        String                      例如：值为，           /sdlfasfljsdf.jpg
	 *            </pre>
	 * 
	 * @return
	 */
	public static List<Map<String, Object>> addStaticResourceProxyURI(String staticResourceProxyURI, List<Map<String, Object>> targeList, String targetUrl[]) {
		if (targeList == null) {
			return null;
		}
		Set<String> keySet;
		for (Map<String, Object> map : targeList) {
			keySet = map.keySet();
			for (String key : keySet) {
				for (String changePath : targetUrl) {
					if (changePath.equals(key)) {
						map.put(key, staticResourceProxyURI + map.get(key));
					}
				}
			}
		}
		return targeList;
	}

	/**
	 * 
	 * @author SangLy
	 * @createTime 2016年8月23日 下午7:28:50
	 * @param staticResourceProxyURI
	 *            目标前缀
	 * @param targeList
	 *            目标list
	 * @param findMapKey
	 *            查找需要修改的第一级别的key
	 * @param targetUrl
	 *            需要添加域名域名前缀中的key
	 * 
	 *            <pre>
	 *      		适用于：List<Map<String, Object>> 例如下面结构中修改 为下面targetUrl1 和targetUrl2 统一添加www.baidu.com
	 *      			result			List<Map<String, Object>>
	 *      			id                 String                        
	 *             		findMapKey         List<Map<String, Object>>
	 *             							id           String
	 *             							targetUrl1         String          例如：值为，           /sdlfasflj.jpg
	 *             							targetUrl2         String          例如：值为，           /sdlfasfljsdf.jpg 
	 *             备注：
	 *             		如果返回结果中有多个例如上面结构中的 findMapKey,则多次调用此方法
	 *            </pre>
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Map<String, Object>> addStaticResourceProxyURI(String staticResourceProxyURI, List<Map<String, Object>> targeList, String findMapKey, String targetUrl[]) {
		if (targeList == null) {
			return null;
		}
		Set<String> keySet;
		for (Map<String, Object> map : targeList) {
			keySet = map.keySet();
			for (String key : keySet) {
				if (findMapKey.equals(key)) {
					addStaticResourceProxyURI(staticResourceProxyURI, (List<Map<String, Object>>) map.get(key), targetUrl);
				}
			}
		}
		return targeList;
	}

	/**
	 * 
	 * @author SangLy
	 * @createTime 2016年8月23日 下午8:15:15
	 * @param staticResourceProxyURI
	 *            目标前缀
	 * @param targeMap
	 *            目标Map
	 * @param targetUrl
	 *            需要添加域名域名前缀中的key
	 * 
	 *            <pre>
	 *      		适用于：                       Map<String, Object>       例如下面结构中修改 为下面targetUrl1 和targetUrl2 统一添加www.baidu.com
	 *      			result			Map<String, Object>
	 *         			    id                String
	 *         				targetUrl1         String          例如：值为，           /sdlfasflj.jpg
	 *			    	    targetUrl2         String          例如：值为，           /sdlfasfljsdf.jpg
	 *            </pre>
	 * 
	 * @return
	 */
	public static Map<String, Object> addStaticResourceProxyURI(String staticResourceProxyURI, Map<String, Object> targeMap, String targetUrl[]) {
		if (targeMap == null) {
			return null;
		}
		Set<String> keySet = targeMap.keySet();
		for (String key : keySet) {
			for (String changePath : targetUrl) {
				if (changePath.equals(key)) {
					targeMap.put(key, staticResourceProxyURI + targeMap.get(key));
				}
			}
		}
		return targeMap;
	}

	/**
	 * 
	 * @author SangLy
	 * @createTime 2016年8月24日 下午12:47:40
	 * @param staticResourceProxyURI
	 *            目标前缀
	 * @param pageResponse
	 * @param targetUrl
	 *            需要添加域名域名前缀中的key
	 * 
	 *            <pre>
	 *      		适用于：                       Map<String, Object>       例如下面结构中修改 为下面targetUrl1 和targetUrl2 统一添加www.baidu.com
	 *      			result			Map<String, Object>
	 *      				total             int
	 *         			    data               List<Map<String, Object>>
	 *         				 		id                 String          
	 *			    	   			targetUrl1         String          例如：值为，           /sdlfasfljsdf.jpg 
	 *								targetUrl2         String          例如：值为，           /sdlfasfljsdf.jpg
	 *            </pre>
	 * 
	 * @return
	 */
	public static PageResponse<Map<String, Object>> addStaticResourceProxyURI(String staticResourceProxyURI, PageResponse<Map<String, Object>> pageResponse, String targetUrl[]) {
		addStaticResourceProxyURI(staticResourceProxyURI, pageResponse.getData(), targetUrl);
		return pageResponse;
	}

	/**
	 * 广告连接为： 域名+/app/advertisement/clickRedirect?id=&url=encode(hypelink)
	 * id:广告id
	 * 
	 * 
	 * @author SangLy
	 * @createTime 2016年8月25日 上午9:54:48
	 * @param domainName
	 *            域名
	 * @param advertisementId
	 *            广告id
	 * @param hypelink
	 *            链接
	 * @return
	 */
	public static String addDomainNameAndFormatAppAdvertisementClickRedirectUrl(String domainName, String advertisementId, String hypelink) {
		String clickhypelink;
		try {
			clickhypelink = java.net.URLEncoder.encode(hypelink, "UTF-8");
		} catch (Exception e) {
			clickhypelink = null;
		}
		return domainName + "/app/advertisement/clickRedirect?id=" + advertisementId + "&url=" + clickhypelink;
	}

	/**
	 * 分享页面(红包、广告、任务分享)： 域名+/app/share/ad?id=&rp= id:广告id,rp:红包id
	 * 
	 * 
	 * 
	 * @author SangLy
	 * @createTime 2016年8月25日 上午9:54:48
	 * @param domainName
	 *            域名
	 * @param advertisementId
	 *            广告id
	 * @param redpackId
	 *            红包id
	 * @return
	 */
	public static String addDomainNameAndFormatAppShareAdUrl(String domainName, String advertisementId, String redpackId) {
		return domainName + "/app/share/ad?id=" + advertisementId + "&rp=" + redpackId;
	}
	
	/**
	 * 分享页面(红包、广告、任务分享)： 域名+/app/share/ad?id=&rp= id:广告id,rp:红包id
	 * 
	 * 
	 * 
	 * @author SangLy
	 * @createTime 2016年8月25日 上午9:54:48
	 * @param domainName
	 *            域名
	 * @param advertisementId
	 *            广告id
	 * @param redpackId
	 *            红包id
	 * @return
	 */
	public static String addDomainNameAndFormatAppShareInviteUrl(String domainName, String customerId) {
		return domainName + "/app/share/invite?id="+customerId;
	}
	
	
	/**
	 * 
	 * @author SangLy
	 * @createTime 2016年8月23日 下午7:28:50
	 * @param staticResourceProxyURI
	 *            目标前缀
	 * @param targeList
	 *            目标list
	 * @param targetUrl
	 *            需要添加域名域名前缀中的key
	 * 
	 *            <pre>
	 *      		适用于：List<Map<String, Object>> 例如下面结构中修改 为下面targetKey1 和targetKey2 统一添加 www.baidu.com
	 *      			result			List<Map<String, Object>>
	 *      			id                String                        
	 *             		targetKey1        String                      例如：值为，           /sdlfasflj.jpg
	 *            		targetKey2        String                      例如：值为，           /sdlfasfljsdf.jpg
	 *            </pre>
	 * 
	 * @return
	 */
	public static List<Map<String, Object>> markMobile(List<Map<String, Object>> targeList, String targetKeys[]) {
		if (targeList == null) {
			return null;
		}
		Set<String> keySet;
		for (Map<String, Object> map : targeList) {
			keySet = map.keySet();
			for (String key : keySet) {
				for (String changePath : targetKeys) {
					if (changePath.equals(key)) {
						map.put(key, FormatUtils.markMobile((String)map.get(key)));
					}
				}
			}
		}
		return targeList;
	}
	
	/**
	 * 
	 * @author SangLy
	 * @createTime 2016年8月24日 下午12:47:40
	 * @param staticResourceProxyURI
	 *            目标前缀
	 * @param pageResponse
	 * @param targetUrl
	 *            需要添加域名域名前缀中的key
	 * 
	 *            <pre>
	 *      		适用于：                       Map<String, Object>       例如下面结构中修改 为下面targetKey1 和targetKey2 统一添加www.baidu.com
	 *      			result			Map<String, Object>
	 *      				total             int
	 *         			    data               List<Map<String, Object>>
	 *         				 		id                 String          
	 *			    	   			targetKeys1         String          例如：值为，           /sdlfasfljsdf.jpg 
	 *								targetKeys2         String          例如：值为，           /sdlfasfljsdf.jpg
	 *            </pre>
	 * 
	 * @return
	 */
	public static PageResponse<Map<String, Object>> markMobile(PageResponse<Map<String, Object>> pageResponse, String targetKeys[]) {
		markMobile(pageResponse.getData(), targetKeys);
		return pageResponse;
	}
}
