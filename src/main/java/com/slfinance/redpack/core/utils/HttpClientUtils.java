/** 
 * @(#)HttpClientUntils.java 1.0.0 2016年4月1日 上午11:08:41  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */

package com.slfinance.redpack.core.utils;

import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 * 使用apache httpclient 发送请求
 * 
 * @author LiYing
 * @version $Revision:1.0.0, $Date: 2016年4月1日 上午11:08:41 $
 */
public class HttpClientUtils {

	private static final String ENCODING = "UTF-8";

	/**
	 * post请求
	 * 
	 * @param url
	 *            请求地址
	 * @param params
	 *            参数
	 * @return 请求返回值
	 * @throws Exception
	 *             抛出异常
	 */
	public static String postRequest(String url, List<NameValuePair> params) throws Exception {

		String result;
		HttpClient httpclient = HttpClientBuilder.create().build();
		HttpPost httppost = new HttpPost(url);
		UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(params, ENCODING);
		httppost.setEntity(uefEntity);
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(60000).setConnectTimeout(20000).build();
		httppost.setConfig(requestConfig);

		HttpResponse response = httpclient.execute(httppost);
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			result = EntityUtils.toString(entity, ENCODING);
		} else {
			httppost.releaseConnection();
			throw new Exception();
		}
		httppost.releaseConnection();
		return result;
	}

	/**
	 * get请求
	 * 
	 * @param url
	 * 
	 *            <pre>
	 *            请求地址
	 *            范例：http://nqxt.hl95001.com/SHHL95/qxtinterface.aspx?phone=18616258737&message=您的验证码为：3254&user=usertest1&pwd=test123123
	 *            </pre>
	 * 
	 * @param params
	 *            参数
	 * @return 请求返回值
	 * @throws Exception
	 *             抛出异常
	 */
	public static String getRequest(String url) throws Exception {

		String result;
		HttpClient httpclient = HttpClientBuilder.create().build();
		HttpGet httpGet = new HttpGet(url);
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(60000).setConnectTimeout(20000).build();
		httpGet.setConfig(requestConfig);
		HttpResponse response = httpclient.execute(httpGet);
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			result = EntityUtils.toString(entity, ENCODING);
		} else {
			httpGet.releaseConnection();
			throw new Exception();
		}
		httpGet.releaseConnection();
		return result;
	}
}
