/** 
 * @(#)RedPackUtils.java 1.0.0 2016年8月26日 上午9:09:05  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */

package com.slfinance.redpack.core.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.slfinance.redpack.common.utils.DigestUtil;
import com.slfinance.redpack.core.constants.RedPackConstant;
import com.slfinance.redpack.core.constants.enums.BusinessType;
import com.slfinance.redpack.core.constants.enums.RedpackType;
import com.slfinance.redpack.core.exception.SLException;
import com.slfinance.redpack.core.extend.validate.RuleUtils;

/**
 * 
 * 
 * @author SangLy
 * @version $Revision:1.0.0, $Date: 2016年8月26日 上午9:09:05 $
 */
public class RedPackUtils {

	/**
	 * 项目密码加密
	 * 
	 * @author SangLy
	 * @createTime 2016年8月26日 上午9:15:17
	 * @param password
	 * @return
	 * @throws SLException
	 */
	public static String encryptionPassword(String password,BusinessType businessType) throws SLException {
		String decodePassword = DigestUtil.aesDecode(password); // 先解码
		//校验密码规则
		if (RuleUtils.isPassword(decodePassword, businessType)) {
			return DigestUtil.encryptPassword(decodePassword); // 在加密
		} else {
			throw new SLException("400008", "password wrong");
		}
	}
	
	/**
	 * 生成红包开启时间
	 * @author work
	 * @createTime 2016年11月11日 下午5:04:26
	 * @param today
	 * @param timePoint
	 * @return
	 */
	public static Date getTimePoint(Date today,String timePoint){
		SimpleDateFormat sdf  =   new  SimpleDateFormat( "yyyy-MM-dd" ); 
		SimpleDateFormat sdf1  =   new  SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" ); 
		String newPoint = sdf.format(today)+timePoint; 
		try {
		     return sdf1.parse(newPoint);
		} catch (Exception e) {
			throw new SLException("600052","开启时间点转换成日期失败");
		}
		
	}
	
	/**
	 * 获取传入时间点下一个红包时间点，例如：传入时间点为10:10分，则传出。红包开启时间点为11点
	 * 
	 * @author SangLy
	 * @createTime 2016年11月12日 下午5:22:42
	 * @return
	 */
	public static Date getNextTimePoint(Date date) throws Exception {
		try {
			SimpleDateFormat myFmt = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat myFmt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			List<Date> timePointList = new ArrayList<Date>();
			Date sysTime = new Date();
			Date result = null;
			if (!myFmt.format(sysTime).equals(myFmt.format(date))) {
				return result;
			}
			for (String t : RedPackConstant.REDPACK_TIME_POINT) {
				timePointList.add(myFmt1.parse(myFmt.format(sysTime) + t));
			}
			for (Date timepoint : timePointList) {
				if (date.getTime() < timepoint.getTime()) {
					result = timepoint;
					break;
				}
			}
			return result;
		} catch (Exception e) {
			throw new SLException("600053", " 获取当前时间距离最近的下一个最近红包时间点失败");
		}
	}
	
	// 红包创建时未开启设置order(任务_100、土豪_110、经济_120)
	// 红包开启设置order(任务_50、土豪_60、经济_70)
	// 红包抢光后或者距下一个红包时点开启前10分钟设置order(任务_200、土豪_210、经济_220),
	public static int setRedPackOrderedValueOfNotOpen(RedpackType redpackType) {
		if (RedpackType.分享红包.equals(redpackType) || RedpackType.邀请红包.equals(redpackType)) {
			return 100;
		} else if (RedpackType.土豪红包.equals(redpackType)) {
			return 110;
		} else if (RedpackType.经济红包.equals(redpackType)) {
			return 120;
		} else {
			return Integer.MAX_VALUE;
		}
	}

	// 红包创建时未开启设置order(任务_100、土豪_110、经济_120)
	// 红包开启设置order(任务_50、土豪_60、经济_70)
	// 红包抢光后或者距下一个红包时点开启前10分钟设置order(任务_200、土豪_210、经济_220),
	public static int setRedPackOrderedValueOfOpening(RedpackType redpackType) {
		if (RedpackType.分享红包.equals(redpackType) || RedpackType.邀请红包.equals(redpackType)) {
			return 50;
		} else if (RedpackType.土豪红包.equals(redpackType)) {
			return 60;
		} else if (RedpackType.经济红包.equals(redpackType)) {
			return 70;
		} else {
			return Integer.MAX_VALUE;
		}
	}

	// 红包创建时未开启设置order(任务_100、土豪_110、经济_120)
	// 红包开启设置order(任务_50、土豪_60、经济_70)
	// 红包抢光后或者距下一个红包时点开启前10分钟设置order(任务_200、土豪_210、经济_220),
	public static int setRedPackOrderedValueOfOpened(RedpackType redpackType) {
		if (RedpackType.分享红包.equals(redpackType) || RedpackType.邀请红包.equals(redpackType)) {
			return 200;
		} else if (RedpackType.土豪红包.equals(redpackType)) {
			return 210;
		} else if (RedpackType.经济红包.equals(redpackType)) {
			return 220;
		} else {
			return Integer.MAX_VALUE;
		}
	}
	
}
