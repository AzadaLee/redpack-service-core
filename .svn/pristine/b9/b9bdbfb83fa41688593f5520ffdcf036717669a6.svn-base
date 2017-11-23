/** 
 * @(#)ModifyRedPackStatusOpeningToOpendThread.java 1.0.0 2016年8月22日 下午8:45:47  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */

package com.slfinance.redpack.core.thread;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.slfinance.redpack.common.utils.DateUtil;
import com.slfinance.redpack.core.constants.RedPackConstant;
import com.slfinance.redpack.core.constants.enums.LogType;
import com.slfinance.redpack.core.constants.enums.RedpackStatus;
import com.slfinance.redpack.core.constants.enums.UserType;
import com.slfinance.redpack.core.entities.OperateLog;
import com.slfinance.redpack.core.entities.RedPack;
import com.slfinance.redpack.core.services.OperateLogService;
import com.slfinance.redpack.core.services.RedPackService;
import com.slfinance.redpack.core.utils.RedPackUtils;

/**
 * 定时扫描，最近一条红包"正开启"状态的红包并且修改此红包状态为"已开启"
 * 
 * @author SangLy
 * @version $Revision:1.0.0, $Date: 2016年8月22日 下午8:45:47 $
 */
public class ModifyRedPackStatusOpeningToOpendThread extends Thread {

	private final Log logger = LogFactory.getLog(ModifyRedPackStatusOpeningToOpendThread.class);

	private RedPackService redpackService;

	private OperateLogService operateLogService;
	
	private Date systemTime = new Date();

	private RedPack redpack = null;
	
	public ModifyRedPackStatusOpeningToOpendThread(RedPackService redpackService, OperateLogService operateLogService) {
		this.operateLogService = operateLogService;
		this.redpackService = redpackService;
		this.start();
	}

	@Override
	public void run() {
		while (true) {
			try {
				redpack = redpackService.findCloselyDayHaveOpening(systemTime); // 距离当天时间最近“正开启”的红包，例如系统时间11点。9点场次的红包由于某种原因是“正开启”状态没有变成“已开启”，则查询出的是9点场次的红包
				systemTime = new Date();
				Date nextTimePointBysystemTime = RedPackUtils.getNextTimePoint(systemTime);
				Long sleepTime = 0L;
				if (redpack != null) {
					Date redpackTimePoint = redpack.getTimePoint();
					Date nextTimePointByRedpackTimePoint = RedPackUtils.getNextTimePoint(redpack.getTimePoint());
					if ((nextTimePointBysystemTime != null) && (redpackTimePoint != null) && (nextTimePointByRedpackTimePoint !=null)) {
						// 正常情况
					   if (nextTimePointByRedpackTimePoint.getTime() < nextTimePointBysystemTime.getTime()) {
							// 由于某种原因倒点未开启的红包而没有变成正开启则直接变成已开启
							redpack.setStatus(RedpackStatus.已开启);
							redpack.setOrdered(RedPackUtils.setRedPackOrderedValueOfOpened(redpack.getRedpackType()));
							redpackService.update(redpack);
							operateLogService.saveAsync(new OperateLog(UserType.robot, LogType.自动更改红包状态, redpack.getId(), null, new Date(), "ModifyRedPackStatusOpeningToOpendThread 由于某种原因倒点未开启的红包而没有变成正开启则直接变成已开启"));
						} else {
							// 距离下一个红包小于10分钟，红包状态为，已开启
							if ((nextTimePointBysystemTime.getTime() - systemTime.getTime() !=0) && ((nextTimePointBysystemTime.getTime() - systemTime.getTime() <= 10 * 60000))) {
								redpack.setStatus(RedpackStatus.已开启);
								redpack.setOrdered(RedPackUtils.setRedPackOrderedValueOfOpened(redpack.getRedpackType()));
								redpackService.update(redpack);
								operateLogService.saveAsync(new OperateLog(UserType.robot, LogType.自动更改红包状态, redpack.getId(), null, new Date(), "ModifyRedPackStatusOpeningToOpendThread 距离下一个红包小于10分钟，红包状态为，已开启"+(nextTimePointBysystemTime.getTime()+"---"+systemTime.getTime())));
							} 
						}
					} else {
						if ((redpackTimePoint == null) && (nextTimePointBysystemTime == null)) {
							// 当天已经没有红包了,休眠一个小时做刷新
							Thread.sleep(1000 * 60 * 60);
						} else if ((redpackTimePoint != null) && (nextTimePointByRedpackTimePoint != null)) {
							// 当前时间下已经没有下一场了。并且查询到的红包场次不 是最后一场，直接修改成，已开启
							redpack.setStatus(RedpackStatus.已开启);
							redpack.setOrdered(RedPackUtils.setRedPackOrderedValueOfOpened(redpack.getRedpackType()));
							redpackService.update(redpack);
							operateLogService.saveAsync(new OperateLog(UserType.robot, LogType.自动更改红包状态, redpack.getId(), null, new Date(), "ModifyRedPackStatusOpeningToOpendThread 当前时间下已经没有下一场了。并且查询到的红包场次不 是最后一场，改为，已开启"));
						} else if ((redpackTimePoint == null) && (nextTimePointBysystemTime != null)) {
							//红包已经发放完,到下一个红包发放时间点做刷新操作
							sleepTime = nextTimePointBysystemTime.getTime() - systemTime.getTime();
							if (sleepTime > 0) {
								Thread.sleep(sleepTime);
							}
						} else if ((redpackTimePoint != null) && (nextTimePointBysystemTime == null) && (nextTimePointByRedpackTimePoint == null) ){
							//最后一场红包（红包状态保持正开启状态到凌晨,休眠3个小时做刷新），红包状态修改结束
							Thread.sleep(3*60*60*1000);
						}
					}
				} else {
					// 休眠4分钟后做刷新
					sleep(RedPackConstant.REDPACK_OPENPOINT_BEFORE_NOW_FRESH * DateUtil.MINUTE_MILISECONDS);
				}

			} catch (Exception ex) {
				try {
					if (redpack != null) {
						redpackService.update(redpack);
						operateLogService.saveAsync(new OperateLog(UserType.robot, LogType.自动更改红包状态, redpack.getId(), null, new Date(), "ModifyRedPackStatusOpeningToOpendThread 由正开启状态更改为已开启状态"));
					}
				} catch (Exception e) {
					logger.error("redpack update error", e);
				}
			}
			
			
		}
	}

}
