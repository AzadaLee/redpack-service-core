/** 
 * @(#)RedpackController.java 1.0.0 2016年8月17日 上午9:33:55  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */

package com.slfinance.redpack.core.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.slfinance.redpack.common.utils.DateUtil;
import com.slfinance.redpack.common.utils.FormatUtils;
import com.slfinance.redpack.common.utils.MapUtil;
import com.slfinance.redpack.core.constants.TableConstant;
import com.slfinance.redpack.core.constants.enums.CustomerRelationType;
import com.slfinance.redpack.core.constants.enums.OrderDetailCategory;
import com.slfinance.redpack.core.constants.enums.RedpackStatus;
import com.slfinance.redpack.core.constants.enums.RedpackType;
import com.slfinance.redpack.core.controller.base.BaseController;
import com.slfinance.redpack.core.entities.Advertisement;
import com.slfinance.redpack.core.entities.Order;
import com.slfinance.redpack.core.entities.RedPack;
import com.slfinance.redpack.core.exception.SLException;
import com.slfinance.redpack.core.extend.fastjson.Serialize;
import com.slfinance.redpack.core.extend.fastjson.SerializeRule;
import com.slfinance.redpack.core.extend.jpa.page.PageRequestVo;
import com.slfinance.redpack.core.extend.jpa.page.PageResponse;
import com.slfinance.redpack.core.extend.validate.annotations.Rule;
import com.slfinance.redpack.core.extend.validate.annotations.Rules;
import com.slfinance.redpack.core.response.ResponseCode;
import com.slfinance.redpack.core.response.ResponseVo;
import com.slfinance.redpack.core.services.AdvertisementService;
import com.slfinance.redpack.core.services.CustomerRelationService;
import com.slfinance.redpack.core.services.OrderDetailService;
import com.slfinance.redpack.core.services.OrderService;
import com.slfinance.redpack.core.services.RedPackService;
import com.slfinance.redpack.core.utils.FormatPrefixUrl;

/**
 * 
 * 
 * @author SangLy
 * @version $Revision:1.0.0, $Date: 2016年8月17日 上午9:33:55 $
 */
@RestController
@RequestMapping(value = "redpack", method = RequestMethod.POST)
public class RedPackController extends BaseController {

	@Autowired
	private RedPackService redpackService;

	@Autowired
	private CustomerRelationService customerRelationService;
	
	@Autowired
	private AdvertisementService advertisementService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderDetailService orderDetailService;

	/**
	 * app-获取红包口令
	 * 
	 * @author SangLy
	 * @createTime 2016年8月17日 下午7:46:11
	 * @return
	 */
	@RequestMapping("getShibboleth")
	@Rules({ @Rule(name = "id", required = true, requiredMessage = "600001"),@Rule(name = "correctAnswer", required = true, requiredMessage = "600045")})
	public ResponseVo appGetShibboleth(@RequestBody Map<String, Object> params) {
		return ResponseVo.success(redpackService.appGetShibboleth(MapUtils.getString(params, "id"), MapUtils.getString(params, "correctAnswer"), getLoginUserId()));
	}

	/**
	 * app-红包列表
	 * 
	 * @author SangLy
	 * @createTime 2016年8月17日 上午11:21:10
	 * @param pageRequest
	 * @return
	 * @throws SLException
	 */
	@RequestMapping("list")
	public ResponseVo appList(PageRequestVo pageRequest) throws SLException {
		return ResponseVo.success(FormatPrefixUrl.addStaticResourceProxyURI(staticResourceProxyURI, redpackService.appRedpackListSort(pageRequest), new String[] { "logo" }));
	}

	/**
	 * app-我的红包预约列表
	 * 
	 * @author SangLy
	 * @createTime 2016年8月19日 下午3:27:19
	 * @param pageRequest
	 * @return
	 */
	@RequestMapping("getSubscription")
	public ResponseVo appGetSubscription(PageRequestVo pageRequest) {
		Date systemTime = new Date();
		Date day = DateUtil.weedDayBelow(systemTime); // 当天
		pageRequest.addParam("day", day);
		pageRequest.addParam("customerId", getLoginUserId()); //
		pageRequest.addParam("relateTable", TableConstant.T_REDPACK);
		pageRequest.addParam("type", CustomerRelationType.红包订阅.toString());
		return ResponseVo.success(FormatPrefixUrl.addStaticResourceProxyURI(staticResourceProxyURI, redpackService.appGetSubscription(pageRequest), new String[] { "logo" }));
	}

	/**
	 * app-预约红包
	 * 
	 * @author SangLy
	 * @createTime 2016年8月19日 下午3:27:19
	 * @param pageRequest
	 * @return
	 */
	@RequestMapping("appointment")
	@Rules({ @Rule(name = "id", required = true, requiredMessage = "600001") })
	public ResponseVo appointment(@RequestBody Map<String, Object> params) throws SLException {
		redpackService.appointment(MapUtils.getString(params, "id"), getLoginUserId());
		return new ResponseVo(ResponseCode.SUCCESS, "红包开启前三分钟会收到通知哦！", null);
	}

	/**
	 * app-红包取消预约
	 * 
	 * @author SangLy
	 * @createTime 2016年8月19日 下午3:27:19
	 * @param pageRequest
	 * @return
	 */
	@RequestMapping("cancelAppointment")
	@Rules({ @Rule(name = "id", required = true, requiredMessage = "600001") })
	public ResponseVo appCancelAppointment(@RequestBody Map<String, Object> params) {
		customerRelationService.appCancelAppointment(MapUtils.getString(params, "id"), getLoginUserId());
		return ResponseVo.success();
	}
	
	/**
	 * app-是否已预约红包
	 * 
	 * @author SangLy
	 * @createTime 2016年11月2日20:26:48
	 * @param pageRequest
	 * @return
	 */
	@RequestMapping("hasAppointment")
	@Rules({ @Rule(name = "id", required = true, requiredMessage = "600001") })
	public ResponseVo appHasAppointment(@RequestBody Map<String, Object> params) {
		Map<String, Object> result = new HashMap<String,Object>();
		result.put("hasAppointment", redpackService.hasAppointment(MapUtils.getString(params, "id"), getLoginUserId()));
		return ResponseVo.success(result);
	}
	
	/**
	 * app-发红包
	 * 
	 * @author SangLy
	 * @createTime 2016年11月3日16:32:33
	 * @param params
	 * @return
	 */
	@RequestMapping("/save")
	@Rules({ @Rule(name = "id", required = true, requiredMessage = "500008"), @Rule(name = "redpackType", required = true, requiredMessage = "600038"), @Rule(name = "bigAmount", required = true, requiredMessage = "600040"), @Rule(name = "bigCount", required = true, requiredMessage = "600036", number = true, numberMessage = "600037"), @Rule(name = "smallAmount", required = true, requiredMessage = "600039"), @Rule(name = "smallCount", required = true, requiredMessage = "600034", number = true, numberMessage = "600035") })
	public ResponseVo save(@RequestBody Map<String, Object> params) throws SLException{
		Map<String, Object> result = new HashMap<String, Object>();
		RedPack newRedPack = redpackService.appSaveRedPacket(params, getLoginUserId());
		if(newRedPack != null){
			getOrderDetail(result,newRedPack);
		}
		return ResponseVo.success(result);
	}
	
	/**
	 * app-获取红包订单的订单信息
	 * 
	 * @author SangLy
	 * @createTime 2016年11月3日16:32:33
	 * @param params
	 * @return
	 */
	@RequestMapping("/orderDetail")
	@Rules({ @Rule(name = "id", required = true, requiredMessage = "600001")})
	public ResponseVo orderDetail(@RequestBody Map<String, Object> params) {
		Map<String, Object> result = new HashMap<String, Object>();
		RedPack redPack = redpackService.findOne(MapUtil.getStringTrim(params, "id"));
		if(redPack != null){
			getOrderDetail(result,redPack);
		}
		return ResponseVo.success(result);
	}
	
	private Map<String, Object> getOrderDetail(Map<String, Object> result,RedPack redPack){
		Advertisement advertisement = advertisementService.findOne((redPack.getAdvertisementId()));
		Order newOrder = orderService.findByRelatePrimary(redPack.getId());
		result.put("id", newOrder.getId()); // 订单id
		result.put("title", advertisement.getTitle()); // 广告标题
		result.put("orderDate", newOrder.getCreatedDate());// 订单时间
		result.put("systemTime", new Date());// 系统当前时间
		result.put("amount", String.format("%.2f", redPack.getAmount()));// 红包金额
		result.put("totalAmount", String.format("%.2f", newOrder.getOrderAmount()));// 支付总金额
		result.put("factorageAmount", String.format("%.2f", orderDetailService.findByOrderIdAndCategory(newOrder.getId(), OrderDetailCategory.发红包手续费).getAmount()));// 手续费
		return result;
	}
	
	/**
	 * app-再发一个红包信息获取
	 * 
	 * @author SangLy
	 * @createTime 2016年11月2日20:26:48
	 * @param pageRequest
	 * @return
	 */
	@RequestMapping("detail")
	@Rules({ @Rule(name = "id", required = true, requiredMessage = "600001") })
	public ResponseVo appRedPackDetailById(@RequestBody Map<String, Object> params) throws SLException{
		RedPack redpack = redpackService.findOne(MapUtils.getString(params, "id"));
		Map<String,Object> result = new HashMap<String,Object>();
		if(redpack != null){
			if (!(RedpackStatus.待付款.equals(redpack.getStatus())) && !(RedpackStatus.待审核.equals(redpack.getStatus()))) {
				Advertisement zhengbenAdvertisement = advertisementService.findOne(advertisementService.findOne(redpack.getAdvertisementId()).getParentId());
				result = new HashMap<String, Object>();
				result.put("advertisementId", zhengbenAdvertisement.getId());
				result.put("redpackType", redpack.getRedpackType());
				result.put("bigAmount", redpack.getBigAmount());
				result.put("bigCount", redpack.getBigCount());
				result.put("smallAmount", redpack.getSmallAmount());
				result.put("smallCount", redpack.getSmallCount());
				result.put("amount", redpack.getAmount());
				result.put("advertisementTitle", zhengbenAdvertisement.getTitle());
				return ResponseVo.success(result);
			}
		}
		return ResponseVo.success(result);
	}
	
	/**
	 * app-移除红包订单
	 * 
	 * @author SangLy
	 * @createTime 2016年11月3日 下午8:13:26
	 * @param advertisementId
	 * @param customerId
	 */
	@RequestMapping("remove")
	@Rules({ @Rule(name = "id", required = true, requiredMessage = "600001") })
	public ResponseVo appRemoveRedPack(@RequestBody Map<String, Object> params) {
		redpackService.removeRedPackById(MapUtils.getString(params, "id"), getLoginUserId());
		return ResponseVo.success();
	}
	
	/**
	 * app-取消红包订单
	 * 
	 * @author SangLy
	 * @createTime 2016年11月3日 下午8:13:26
	 * @param advertisementId
	 * @param customerId
	 */
	@RequestMapping("cancelOrder")
	@Rules({ @Rule(name = "id", required = true, requiredMessage = "600001") })
	public ResponseVo appCancelOrderRedPackById(@RequestBody Map<String, Object> params) {
		redpackService.cancelOrderRedPackById(MapUtils.getString(params, "id"), getLoginUserId());
		return ResponseVo.success();
	}
	
	/**
	 * app-首页突出红包展示
	 * 
	 * @author SangLy
	 * @createTime 2016年11月4日 上午10:28:39
	 * @param redpackType
	 * @return
	 */
	@RequestMapping("nextRedpack")
	@Serialize({ @SerializeRule(clazz = RedPack.class, include = { "id", "title" }) })
	public ResponseVo appNextRedpack(@RequestBody Map<String, Object> params) throws SLException{
		String redpackType = MapUtils.getString(params, "redpackType");
		Map<String, Object> result = new HashMap<String,Object>();
		//相应类型传递的数据对应如下: 全部：空串 任务红包：任务 土豪红包：土豪 经济红包: 经济 
		if(StringUtils.isBlank(redpackType)){
			redpackType = "全部";
		}else if("任务".equals(redpackType)){
			Date sysTime = new Date();
			Date today = DateUtil.weedDayBelow(sysTime);
			if(DateUtils.addHours(today, 20).compareTo(sysTime) > 0){
				redpackType = RedpackType.分享红包.toString();
			}else if(DateUtils.addHours(today, 21).compareTo(sysTime) > 0){
				redpackType = RedpackType.邀请红包.toString();
			}else{
				return ResponseVo.success(result);
			}
			redpackType = RedpackType.邀请红包.toString();
		}else if ("土豪".equals(redpackType)){
			redpackType = RedpackType.土豪红包.toString();
		}else if ("经济".equals(redpackType)){
			redpackType = RedpackType.经济红包.toString();
		}else {
			throw new SLException("600046","redpackType not find");
		}
		RedPack redpack = null;
		if("全部".equals(redpackType)){
			redpack = redpackService.findNextRedpackForAll();
		}else{
			redpack = redpackService.findNextRedpack(RedpackType.valueOf(redpackType));
		}
		if (redpack != null) {
			result.put("id", redpack.getId());
			result.put("systemTime", new Date());
			result.put("timePoint", redpack.getTimePoint());
		}
		return ResponseVo.success(result);
	}
	
	/**
	 * app-中奖名单
	 * 
	 * @author SangLy
	 * @createTime 2016年11月4日 下午4:05:10
	 * @param params
	 * @return
	 * @throws SLException
	 */
	@RequestMapping("winners")
	@Rules({ @Rule(name = "id", required = true, requiredMessage = "600001") })
	public ResponseVo appWinnersRedpackList(PageRequestVo pageRequest) throws SLException{
		PageResponse<Map<String,Object>> pageResponse = redpackService.winnersRedpackList(pageRequest);
		for(Map<String,Object> map : pageResponse.getData()){
			map.put("mobile", FormatUtils.markMobile(map.get("mobile").toString()));
		}
		return ResponseVo.success(pageResponse);
	}
	
	
	/**
	 * app-红包订单列表
	 *
	<pre>
	  	待付款-待付款
		已失效-已失效
		待审核-待审核
		已通过-审核通过、未开启、正开启、已开启、已抢完、已过期
		被驳回-审核驳回
	</pre>
	 * 
	 * @author SangLy
	 * @createTime 2016年11月3日 下午8:13:26
	 * @param advertisementId
	 * @param customerId
	 */
	@RequestMapping("orderList")
	@Rules({ @Rule(name = "redpackOrderType", required = true, requiredMessage = "600048") })
	public ResponseVo appOrderList(PageRequestVo pageRequest) throws SLException{
		return ResponseVo.success(redpackService.orderListByCustomerId(pageRequest,getLoginUserId()));
	}
	
}
