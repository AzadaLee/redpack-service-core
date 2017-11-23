/** 
 * @(#)JpushService.java 1.0.0 2016年8月18日 下午2:06:59  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */

package com.slfinance.redpack.core.services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.slfinance.redpack.common.utils.DigestUtil;
import com.slfinance.redpack.core.configs.AppPushConfig;
import com.slfinance.redpack.core.constants.enums.LogType;
import com.slfinance.redpack.core.constants.enums.PushType;
import com.slfinance.redpack.core.constants.enums.UserType;
import com.slfinance.redpack.core.entities.AppPush;
import com.slfinance.redpack.core.entities.OperateLog;
import com.slfinance.redpack.core.entities.RedPack;
import com.slfinance.redpack.core.repositories.AppPushRepository;
import com.slfinance.redpack.core.services.base.BaseService;

/**
 * 
 * 极光推送
 * <p>
 * 使用参考:http://blog.csdn.net/gebitan505/article/details/46812879
 * </p>
 * 
 * @author samson
 * @version $Revision:1.0.0, $Date: 2016年8月18日 下午2:06:59 $
 */
@Service
public class AppPushService extends BaseService<AppPush, AppPushRepository> implements InitializingBean {

	@Autowired
	AppPushConfig appPushConfig;
	@Autowired
	OperateLogService operateLogService;

	private JPushClient jPushClient;

	/**
	 * 根据
	 * 
	 * @author samson
	 * @createTime 2016年8月31日 下午1:50:48
	 * @param target
	 * @return
	 */
	public List<AppPush> findAllByTarget(String target) {
		return repository.findAllByTarget(target);
	}

	/**
	 * 推送红包预约
	 * 
	 * @author samson
	 * @createTime 2016年8月18日 下午2:31:13
	 * @param redpack
	 *            红包
	 */
	@Transactional
	public void pushRedpackAppointment(RedPack redpack) {
		Map<String, String> redpackAppointment_notification = appPushConfig.getNotification().get("redpackAppointment");
		Calendar timePoint = Calendar.getInstance();
		timePoint.setTime(redpack.getTimePoint());
		String alert = String.format(redpackAppointment_notification.get("alert"), timePoint.get(Calendar.HOUR_OF_DAY), timePoint.get(Calendar.MINUTE));
		String title = String.format(redpackAppointment_notification.get("title"));
		Map<String, String> data = Maps.newHashMap();
		data.put("_iosPage", "SLRedPackDetailViewController");// ios相应页面
		data.put("_androidPage", "com.slfinance.redpack.ui.activity.redpackdetail.RedPackDetailActivity");// android相应页面
		data.put("id", redpack.getId());
		Map<String, String> extra = getExtra(data);
		String tags = "RA_" + redpack.getId();
		PushPayload pushPayload = PushPayload.newBuilder().setPlatform(Platform.android_ios()).setAudience(Audience.tag(tags)).setNotification(Notification.newBuilder().addPlatformNotification(AndroidNotification.newBuilder().setAlert(alert).setTitle(title).addExtras(extra).build()).addPlatformNotification(IosNotification.newBuilder().setAlert(alert).addExtras(extra).build()).build()).setOptions(Options.newBuilder().setApnsProduction(appPushConfig.isApnsProduction()).build()).build();
		Date now = new Date();
		try {
			// 成功保存到推送表
			PushResult pr = jPushClient.sendPush(pushPayload);
			repository.save(new AppPush(redpack.getId(), PushType.红包预约, pushPayload.toString(), pr.toString(), pr.msg_id + "", pr.getResponseCode() + "", now, now));
		} catch (APIRequestException e) {
			repository.save(new AppPush(redpack.getId(), PushType.红包预约, pushPayload.toString(), e.getMessage(), e.getMsgId() + "", e.getErrorCode() + "", now, now));
		} catch (Exception e) {
			// 失败保存到日志表
			operateLogService.save(new OperateLog(UserType.robot, LogType.APP推送失败, pushPayload.toString(), redpack.getId(), new Date(), e.getMessage()));
			logger.error("推送红包预约失败:{}", e.getMessage());
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		jPushClient = new JPushClient(appPushConfig.getMasterSecret(), appPushConfig.getAppKey());
	}

	private Map<String, String> getExtra(Map<String, String> data) {
		Map<String, String> extra = Maps.newHashMap();
		extra.put("data", DigestUtil.aesEncode(JSON.toJSONString(data)));
		return extra;
	}
}
