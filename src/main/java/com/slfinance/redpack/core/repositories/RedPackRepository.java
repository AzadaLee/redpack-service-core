/** 
 * @(#)RedpackRepository.java 1.0.0 2016年7月25日 下午4:51:38  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */

package com.slfinance.redpack.core.repositories;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.slfinance.redpack.core.constants.RedpackRecordStatus;
import com.slfinance.redpack.core.constants.enums.RedpackStatus;
import com.slfinance.redpack.core.constants.enums.RedpackType;
import com.slfinance.redpack.core.entities.AdvertisementAnswer;
import com.slfinance.redpack.core.entities.RedPack;
import com.slfinance.redpack.core.extend.jpa.page.PageRequestVo;
import com.slfinance.redpack.core.extend.jpa.page.PageResponse;
import com.slfinance.redpack.core.extend.jpa.page.QueryExtend;
import com.slfinance.redpack.core.repositories.base.BaseRepository;
import com.slfinance.redpack.core.vo.RedPackExportExcelVo;

/**
 * 
 * 
 * @author SangLy
 * @version $Revision:1.0.0, $Date: 2016年7月25日 下午4:51:38 $
 */
public interface RedPackRepository extends BaseRepository<RedPack> {

	/**
	 * 红包列表查询
	 * @author taoxm
	 * @createTime 2016年8月17日 下午1:47:41
	 * @param pageRequest
	 * @return
	 * 
	 */
	@QueryExtend()
	@Query(nativeQuery=true,value="select r.id,r.redpack_code,r.user_type,r.redpack_type,r.status,r.time_point,r.amount,a.advertiser_name from rp_t_redpack r left join rp_t_advertisement a on a.id = r.advertisement_id where ?(a.advertiser_name like :advertiserName || '%') ? and ?(r.redpack_type = :redpackType)? and ?(r.user_type = :userType)? and ?(r.status = :status)? and ?(a.advertisement_code = :advertisementCode)? and ?(r.amount >= :minAmount) ? and ?(trunc(r.time_point)) >= to_date( :startTimePoint , 'yyyy-mm-dd')? and ?(trunc(r.time_point)) <= to_date( :endTimePoint , 'yyyy-mm-dd')? order by r.created_date desc, r.time_point desc ")
	public PageResponse<Map<String, Object>> findAllPage(PageRequestVo pageRequest);

	/**
	 * 查询单个红包详情
	 * @author taoxm
	 * @createTime 2016年8月17日 下午1:47:55
	 * @param id
	 * @return
	 * 
	 */
	@QueryExtend()
	@Query(nativeQuery=true , value="select r.id,r.advertisement_id,r.redpack_code,r.redpack_type,r.status,r.big_amount,r.big_count,r.small_amount,r.small_count,r.amount,a.title,to_char(r.time_point, 'yyyy-mm-dd') time_point_day,to_char(r.time_point, 'hh24:mi') time_point_time,a.hyperlink,a.advertisement_code,a.a.content,a.created_date,nvl(a.hits_count, 0) hits_count,nvl(a.shared_count, 0) shared_count,a.advertiser_name,a.logo from rp_t_redpack r left join rp_t_advertisement a on a.id = r.advertisement_id where r.id = :id ")
	public Map<String, Object> findRedpackAndAdvertisementInfo(@Param("id")String id);
	
	/**
	 * 统计红包个数
	 * 
	 *当天红包总数状态是：未开启, 正开启, 已开启
	 *当天已经发送红包总数状态是： 正开启, 已开启
	 * 
	 * @author SangLy
	 * @createTime 2016年8月16日 下午2:53:41
	 * @param day
	 * 		  日期
	 * @return
	 */
	@QueryExtend
	@Query(nativeQuery = true, value = "select sum(big_count+small_count) as sumcount from rp_t_redpack where to_date ( to_char (time_point, 'yyyy-mm-dd'),'yyyy-mm-dd') = :day and status in (:status) and record_status = :recordStatus")
	Long todayRedpackCount(@Param("day")Date day,@Param("status")List<String> status,@Param("recordStatus")String recordStatus);
	
	/**
	 * 距离系统时间最近的正开启的红包
	 * 
	 * @author SangLy
	 * @createTime 2016年8月16日 下午5:26:29
	 * @param day 查询的日期
	 * 			格式：2016-8-16 00:00:00
	 * @param systemTime
	 * 			 查询的时间
	 * 			格式：2016-8-16 17:27:33
	 * @param status
	 * @return
	 */
	@QueryExtend
	@Query(nativeQuery = true, value = "select * from (select re.* ,rownum as rn from rp_t_redpack re where to_date(to_char(re.time_point,'yyyy-MM-dd'),'yyyy-MM-dd') = :day and re.status = :status and re.time_point <= :systemTime order by re.time_point desc) where rn =1")
	RedPack findCloselyHaveOpening(@Param("day")Date day, @Param("systemTime")Date systemTime, @Param("status")String status);
	
	/**
	 * 距离当天时间最近“正开启”的红包，例如系统时间11点。9点场次的红包由于某种原因是“正开启”状态没有变成“已开启”，则查询出的是9点场次的红包
	 * 
	 * @author SangLy
	 * @createTime 2016年8月16日 下午5:26:29
	 * @param day 查询的日期
	 * 			格式：2016-8-16 00:00:00
	 * @param systemTime
	 * 			 查询的时间
	 * 			格式：2016-8-16 17:27:33
	 * @param status
	 * @return
	 */
	@QueryExtend
	@Query(nativeQuery = true, value = "select * from (select re.* ,rownum as rn from rp_t_redpack re where to_date(to_char(re.time_point,'yyyy-MM-dd'),'yyyy-MM-dd') = :day and re.status = :status  and re.record_status = :recordStatus order by re.time_point asc) where rn =1")
	RedPack findCloselyDayHaveOpening(@Param("day")Date day, @Param("status")String status,@Param("recordStatus")String recordStatus);

	/**
	 * 距离系统时间最近的当天还没有开启的红包
	 * 
	 * @author SangLy
	 * @createTime 2016年8月16日 下午5:26:29
	 * @param day 查询的日期
	 * 			格式：2016-8-16 00:00:00
	 * @param systemTime
	 * 			 查询的时间
	 * 			格式：2016-8-16 17:27:33
	 * @param status
	 * @return
	 */
	@QueryExtend
	@Query(nativeQuery = true, value = "select * from (select rr.*,rownum as rn from (select * from rp_t_redpack re where to_date (to_char (re.time_point, 'yyyy-mm-dd'),'yyyy-mm-dd') = :day and re.status = :status and re.time_point > :systemTime order by re.time_point asc) rr) where rn = 1")
	RedPack findCloselyHaveNotOpen(@Param("day")Date day, @Param("systemTime")Date systemTime, @Param("status")String status);

	/**
	 * 距离当天时间最近的还“未开启”的红包，例如系统时间11点。9点场次的红包由于某种原因是“未开启”状态，则查询出的是9点场次的红包
	 * 
	 * @author SangLy
	 * @createTime 2016年8月16日 下午5:26:29
	 * @param day 查询的日期
	 * 			格式：2016-8-16 00:00:00
	 * @param systemTime
	 * 			 查询的时间
	 * 			格式：2016-8-16 17:27:33
	 * @param status
	 * @return
	 */
	@QueryExtend
	@Query(nativeQuery = true, value = "select * from (select rr.*,rownum as rn from (select * from rp_t_redpack re where to_date (to_char (re.time_point, 'yyyy-mm-dd'),'yyyy-mm-dd') = :day and re.status = :status and re.record_status = :recordStatus order by re.time_point asc) rr) where rn = 1")
	RedPack findCloselyDayHaveNotOpen(@Param("day")Date day, @Param("status")String status,@Param("recordStatus")String recordStatus);
	
	/**
	 * 导出红包
	 * @author taoxm
	 * @createTime 2016年8月17日 下午1:47:16
	 * @param redPackExportExcelVo
	 * @return
	 */
	@QueryExtend()
	@Query(nativeQuery=true,value="select r.id,r.redpack_code,r.user_type,r.redpack_type,r.status,r.time_point,r.amount,to_char(r.time_point, 'yyyy-mm-dd') time_point_day,to_char(r.time_point, 'hh24:mi') time_point_time,r.big_amount,r.big_count,r.small_amount,r.small_count,a.advertisement_code,a.advertiser_name,a.hyperlink from rp_t_redpack r left join rp_t_advertisement a on a.id = r.advertisement_id where ?(a.advertiser_name like :advertiserName || '%') ? and ?(r.redpack_type = :redpackType)? and ?(r.user_type = :userType)? and ?(r.status = :status)? and ?(a.advertisement_code = :advertisementCode)? and ?(r.amount >= :minAmount) ? and ?(trunc(r.time_point)) >= to_date(:startTimePoint, 'yyyy-mm-dd')? and ?(trunc(r.time_point)) <= to_date(:endTimePoint, 'yyyy-mm-dd') ? order by r.time_point desc")
	public List<Map<String, Object>> exportExcel(RedPackExportExcelVo redPackExportExcelVo);

	
	/**
	 * app-红包里列表
	 * 
	 * 1.全部，无金额排序 a.正在开启，未开启时间正排序，b.未开启，时间正序，c.已开启，时间倒序
	 * @param day
	 * 			当天的红包
	 * @param startTimePoint
	 * 		    起始时间
	 * @param endTimePoint
	 * 			结束时间
	 * @param startAmount
	 * 			起始金额
	 * @param endAmount
	 * 	                  结束金额
	 * @return
	 */
	@QueryExtend
	@Query(nativeQuery=true , value="with re as(select re.id,ad.logo,ad.title,to_char(re.time_point,'hh24:mi')time_point,re.status,re.redpack_type,re.amount,re.ordered,re.created_date from RP_T_REDPACK re join RP_T_ADVERTISEMENT ad on re.advertisement_id=ad.id and to_date(to_char(re.time_point,'yyyy-MM-dd'),'yyyy-MM-dd') = :day and ?(re.time_point >= :startTimePoint )? and ?(re.time_point <= :endTimePoint )? and ?(re.amount >= :startAmount )? and ?(re.amount <= :endAmount )? and re.status in ('正开启', '未开启','已开启','已抢完') and record_status = '正常' ) select r1.id,r1.logo,r1.title,r1.time_point,r1.status,r1.redpack_type,r1.amount from ( select * from re where re.status in ('正开启','未开启') order by re.time_point asc ,re.ordered asc ,re.created_date asc) r1 union all select r2.id,r2.logo,r2.title,r2.time_point,case r2.status when '已抢完' then '已开启' else r2.status end as status,r2.redpack_type,r2.amount from ( select * from re where re.status in ( '已开启','已抢完') order by re.time_point desc,re.ordered asc ,re.created_date asc) r2")
	PageResponse<Map<String, Object>> appRedpackAllDefaultSort(PageRequestVo pageRequest);
	
	/**
	 * app,红包里列表，查询全部，金额正序
	 */
	@QueryExtend
	@Query(nativeQuery=true , value="with re as(select re.id,ad.logo,ad.title,to_char(re.time_point,'hh24:mi')time_point,re.status,re.redpack_type,re.amount,re.ordered,re.created_date from RP_T_REDPACK re join RP_T_ADVERTISEMENT ad on re.advertisement_id=ad.id and to_date(to_char(re.time_point,'yyyy-MM-dd'),'yyyy-MM-dd') = :day and ?(re.time_point >= :startTimePoint )? and ?(re.time_point <= :endTimePoint )? and ?(re.amount >= :startAmount )? and ?(re.amount <= :endAmount )? and re.status in ('正开启', '未开启','已开启','已抢完') and record_status = '正常' )  select r2.id,r2.logo,r2.title,r2.time_point,case r2.status when '已抢完' then '已开启' else r2.status end as status,r2.redpack_type,r2.amount from ( select * from re  order by re.amount asc,re.time_point asc,re.ordered asc ,re.created_date asc) r2")
	PageResponse<Map<String, Object>> appRedpackAllByAmountOrderAsc(PageRequestVo pageRequest);
	
	/**
	 * app,红包里列表，查询全部，金额倒序
	 */
	@QueryExtend
	@Query(nativeQuery=true , value="with re as(select re.id,ad.logo,ad.title,to_char(re.time_point,'hh24:mi')time_point,re.status,re.redpack_type,re.amount,re.ordered,re.created_date from RP_T_REDPACK re join RP_T_ADVERTISEMENT ad on re.advertisement_id=ad.id and to_date(to_char(re.time_point,'yyyy-MM-dd'),'yyyy-MM-dd') = :day and ?(re.time_point >= :startTimePoint )? and ?(re.time_point <= :endTimePoint )? and ?(re.amount >= :startAmount )? and ?(re.amount <= :endAmount )? and re.status in ('正开启', '未开启','已开启','已抢完') and record_status = '正常' )  select r2.id,r2.logo,r2.title,r2.time_point,case r2.status when '已抢完' then '已开启' else r2.status end as status,r2.redpack_type,r2.amount from ( select * from re  order by re.amount desc,re.time_point asc,re.ordered asc ,re.created_date asc) r2")
	PageResponse<Map<String, Object>> appRedpackAllByAmountOrderDsc(PageRequestVo pageRequest);

	/**
	 * app,任务，默认排序
	 */
	@QueryExtend
	@Query(nativeQuery=true , value="with re as(select re.id,ad.logo,ad.title,to_char(re.time_point,'hh24:mi')time_point,re.status,re.redpack_type,re.amount,re.ordered,re.created_date from RP_T_REDPACK re join RP_T_ADVERTISEMENT ad on re.advertisement_id=ad.id and to_date(to_char(re.time_point,'yyyy-MM-dd'),'yyyy-MM-dd') = :day and ?(re.time_point >= :startTimePoint )? and ?(re.time_point <= :endTimePoint )? and ?(re.amount >= :startAmount )? and ?(re.amount <= :endAmount )? and re.status in ('正开启', '未开启','已开启','已抢完')  and record_status = '正常'  and re.redpack_type in ('分享红包','邀请红包')) select r1.id,r1.logo,r1.title,r1.time_point,r1.status,r1.redpack_type,r1.amount from ( select * from re where re.status in ('正开启','未开启') order by re.time_point asc ,re.ordered asc ,re.created_date asc) r1 union all select r2.id,r2.logo,r2.title,r2.time_point,case r2.status when '已抢完' then '已开启' else r2.status end as status,r2.redpack_type,r2.amount from ( select * from re where re.status in ( '已开启','已抢完') order by re.time_point desc,re.ordered asc ,re.created_date asc) r2")
	PageResponse<Map<String, Object>> appRedpackRenWuByDefaultSort(PageRequestVo pageRequest);
	
	/**
	 * app,任务，金额正序
	 */
	@QueryExtend
	@Query(nativeQuery=true , value="with re as(select re.id,ad.logo,ad.title,to_char(re.time_point,'hh24:mi')time_point,re.status,re.redpack_type,re.amount,re.ordered,re.created_date from RP_T_REDPACK re join RP_T_ADVERTISEMENT ad on re.advertisement_id=ad.id and to_date(to_char(re.time_point,'yyyy-MM-dd'),'yyyy-MM-dd') = :day and ?(re.time_point >= :startTimePoint )? and ?(re.time_point <= :endTimePoint )? and ?(re.amount >= :startAmount )? and ?(re.amount <= :endAmount )? and re.status in ('正开启', '未开启','已开启','已抢完')  and record_status = '正常'  and re.redpack_type in ('分享红包','邀请红包'))  select r2.id,r2.logo,r2.title,r2.time_point,case r2.status when '已抢完' then '已开启' else r2.status end as status,r2.redpack_type,r2.amount from ( select * from re  order by re.amount asc,re.time_point asc,re.ordered asc ,re.created_date asc) r2")
	PageResponse<Map<String, Object>> appRedpackRenWuByAmountOrderAsc(PageRequestVo pageRequest);
	
	/**
	 * app,任务 ，金额倒序
	 */
	@QueryExtend
	@Query(nativeQuery=true , value="with re as(select re.id,ad.logo,ad.title,to_char(re.time_point,'hh24:mi')time_point,re.status,re.redpack_type,re.amount,re.ordered,re.created_date from RP_T_REDPACK re join RP_T_ADVERTISEMENT ad on re.advertisement_id=ad.id and to_date(to_char(re.time_point,'yyyy-MM-dd'),'yyyy-MM-dd') = :day and ?(re.time_point >= :startTimePoint )? and ?(re.time_point <= :endTimePoint )? and ?(re.amount >= :startAmount )? and ?(re.amount <= :endAmount )? and re.status in ('正开启', '未开启','已开启','已抢完') and record_status = '正常' and re.redpack_type in ('分享红包','邀请红包'))  select r2.id,r2.logo,r2.title,r2.time_point,case r2.status when '已抢完' then '已开启' else r2.status end as status,r2.redpack_type,r2.amount from ( select * from re  order by re.amount desc,re.time_point asc,re.ordered asc ,re.created_date asc) r2")
	PageResponse<Map<String, Object>> appRedpackRenWuByAmountOrderDsc(PageRequestVo pageRequest);

	/**
	 * app,红包里列表，根据红包类型，默认排序，开启时间倒序
	 */
	@QueryExtend
	@Query(nativeQuery=true , value="with re as(select re.id,ad.logo,ad.title,to_char(re.time_point,'hh24:mi')time_point,re.status,re.redpack_type,re.amount,re.ordered,re.created_date from RP_T_REDPACK re join RP_T_ADVERTISEMENT ad on re.advertisement_id=ad.id and to_date(to_char(re.time_point,'yyyy-MM-dd'),'yyyy-MM-dd') = :day and ?(re.time_point >= :startTimePoint )? and ?(re.time_point <= :endTimePoint )? and ?(re.amount >= :startAmount )? and ?(re.amount <= :endAmount )? and re.status in ('正开启', '未开启','已开启','已抢完') and record_status = '正常' and re.redpack_type = :redpackType) select r1.id,r1.logo,r1.title,r1.time_point,r1.status,r1.redpack_type,r1.amount from ( select * from re where re.status in ('正开启','未开启') order by re.time_point asc ,re.ordered asc ,re.created_date asc) r1 union all select r2.id,r2.logo,r2.title,r2.time_point,case r2.status when '已抢完' then '已开启' else r2.status end as status,r2.redpack_type,r2.amount from ( select * from re where re.status in ( '已开启','已抢完') order by re.time_point desc,re.ordered asc ,re.created_date asc) r2")
	PageResponse<Map<String, Object>> appRedpackRedPackTypeByDefaultSort(PageRequestVo pageRequest);
	
	/**
	 * app,红包里列表，根据红包类型，金额正序
	 */
	@QueryExtend
	@Query(nativeQuery=true , value="with re as(select re.id,ad.logo,ad.title,to_char(re.time_point,'hh24:mi')time_point,re.status,re.redpack_type,re.amount,re.ordered,re.created_date from RP_T_REDPACK re join RP_T_ADVERTISEMENT ad on re.advertisement_id=ad.id and to_date(to_char(re.time_point,'yyyy-MM-dd'),'yyyy-MM-dd') = :day and ?(re.time_point >= :startTimePoint )? and ?(re.time_point <= :endTimePoint )? and ?(re.amount >= :startAmount )? and ?(re.amount <= :endAmount )? and re.status in ('正开启', '未开启','已开启','已抢完') and record_status = '正常' and re.redpack_type = :redpackType)  select r2.id,r2.logo,r2.title,r2.time_point,case r2.status when '已抢完' then '已开启' else r2.status end as status,r2.redpack_type,r2.amount from ( select * from re  order by re.amount asc,re.time_point asc,re.ordered asc ,re.created_date asc) r2")
	PageResponse<Map<String, Object>> appRedpackRedPackTypeByAmountOrderAsc(PageRequestVo pageRequest);
	
	/**
	 * app,红包里列表，根据红包类型，金额倒序
	 */
	@QueryExtend
	@Query(nativeQuery=true , value="with re as(select re.id,ad.logo,ad.title,to_char(re.time_point,'hh24:mi')time_point,re.status,re.redpack_type,re.amount,re.ordered,re.created_date from RP_T_REDPACK re join RP_T_ADVERTISEMENT ad on re.advertisement_id=ad.id and to_date(to_char(re.time_point,'yyyy-MM-dd'),'yyyy-MM-dd') = :day and ?(re.time_point >= :startTimePoint )? and ?(re.time_point <= :endTimePoint )? and ?(re.amount >= :startAmount )? and ?(re.amount <= :endAmount )? and re.status in ('正开启', '未开启','已开启','已抢完') and record_status = '正常' and re.redpack_type = :redpackType)  select r2.id,r2.logo,r2.title,r2.time_point,case r2.status when '已抢完' then '已开启' else r2.status end as status,r2.redpack_type,r2.amount from ( select * from re  order by re.amount desc,re.time_point asc,re.ordered asc ,re.created_date asc) r2")
	PageResponse<Map<String, Object>> appRedpackRedPackTypeByAmountOrderDsc(PageRequestVo pageRequest);
	
	/**
	 * app-我的红包预约列表
	 * @author SangLy
	 * @createTime 2016年8月19日 下午4:55:28
	 * @param pageRequest
	 * @return
	 */
	@QueryExtend
	@Query(nativeQuery = true, value = "select re.id, re.redpack_type ,ad.logo,ad.title, to_char(re.time_point,'hh24:mi')time_point ,re.status,re.amount from RP_T_CUSTOMER_RELATION cr join RP_T_REDPACK re on cr.relate_primary = re.id join  RP_T_ADVERTISEMENT ad on re.advertisement_id = ad.id  and to_date(to_char(cr.created_date,'yyyy-MM-dd'),'yyyy-MM-dd') = :day and cr.customer_id = :customerId and cr.relate_table = :relateTable and cr.type = :type order by cr.created_date desc")
	PageResponse<Map<String, Object>> appGetSubscription(PageRequestVo pageRequest);
	
	/**
	 * 根据红包id获取答案列表
	 * @author SangLy
	 * @createTime 2016年11月2日 下午2:02:27
	 * @return
	 */
	@QueryExtend
	@Query(nativeQuery = true, value = "select an.id, an.ANSWER_CONTENT from RP_T_REDPACK r join RP_T_ADVERTISEMENT_ANSWER an on r.ADVERTISEMENT_ID = an.ADVERTISEMENT_ID and r.id = :redpackId")
	public List<AdvertisementAnswer> findAnswerListByRedpackId(@Param("redpackId")String redpackId);

	/**
	 * app-首页突出红包展示
	 * 
	 * @author SangLy
	 * @createTime 2016年11月4日 上午10:28:39
	 * @param redpackType
	 * @return
	 */
	@QueryExtend
	@Query(nativeQuery = true, value = "select * from ( select rr.*, rownum as rn from ( select	* from rp_t_redpack re where to_date ( to_char (re.time_point, 'yyyy-mm-dd'), 'yyyy-mm-dd' ) = :day and re.status = :redpackStatus and re.record_status = :recordStatus and re.redpack_type = :redpackType order by re.time_point asc, re. ordered asc, re.created_date asc ) rr ) where rn = 1")
	public RedPack findNextRedpack(@Param("day")Date day,@Param("redpackStatus")String redpackStatus,@Param("recordStatus")String recordStatus, @Param("redpackType")String redpackType);
	
	/**
	 * app-首页突出红包展示(全部的时候)
	 * 
	 * @author SangLy
	 * @createTime 2016年11月4日 上午10:28:39
	 * @param redpackType
	 * @return
	 */
	@QueryExtend
	@Query(nativeQuery = true, value = "select * from ( select rr.*, rownum as rn from ( select	* from rp_t_redpack re where to_date ( to_char (re.time_point, 'yyyy-mm-dd'), 'yyyy-mm-dd' ) = :day and re.status = :redpackStatus and re.record_status = :recordStatus order by re.time_point asc, re. ordered asc, re.created_date asc ) rr ) where rn = 1")
	public RedPack findNextRedpackForAll(@Param("day")Date day,@Param("redpackStatus")String redpackStatus,@Param("recordStatus")String recordStatus);
	
	/**
	 * app-中奖名单
	 * @author SangLy
	 * @createTime 2016年11月4日 下午4:18:53
	 * @param pageRequest
	 * @return
	 */
	@QueryExtend
	@Query(nativeQuery = true, value = "select cu.mobile, d.amount from rp_t_distribute d join rp_t_customer cu on d.assignee = cu.id  and d.redpack_id =:id order by d.distribute_time desc")
	PageResponse<Map<String, Object>> winnersRedpackList(PageRequestVo pageRequest);
	
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
	@QueryExtend
	@Query(nativeQuery = true, value = "select rp.id, ad.title, rp.created_date as order_date, 30 as effective_duration ,rp.big_count+rp.small_count total_count, rp.amount, rp.time_point, rp.status , rp.memo from rp_t_redpack rp join rp_t_advertisement ad on rp.advertisement_id = ad.id and rp.created_user = :customerId and rp.status in ( :status) and record_Status = :recordStatus order by rp.time_point desc ,rp.created_date desc")
	PageResponse<Map<String, Object>> orderListByCustomerId(PageRequestVo pageRequest);

	/**
	 * 根据红包类型查询当天红包数
	 * @param redpackType
	 * @return
	 */
	@QueryExtend
	@Query(nativeQuery = true,value="select count(1) from rp_t_redpack t where t.redpack_type = :redpackType and trunc(t.created_date) = trunc(sysdate)")
	int findByRedpackTypeWithCreateDateIsToday(@Param("redpackType")String redpackType);


	/**
	 * 查询当天需要生成红分配记录表的红包
	 * 
	 * @author work
	 * @createTime 2016年11月11日 下午10:10:30
	 * @param timePoint时间点=今天的红包
	 * @param recordStatus记录状态=正常
	 * @param status红包状态=审核通过
	 * @param redpackType红类型不包括（分享红包，邀请红包，因为这两种红包固定排在晚上8点和晚上9点的
	 * @return
	 */
	List<RedPack> findByTimePointAndRecordStatusAndStatusAndRedpackTypeNotInOrderByCreatedDateAsc(Date timePoint,RedpackRecordStatus recordStatus,RedpackStatus status,List<RedpackType> redpackType);
	
	/**
	 * 查询当天需要生成红分配记录表的红包（邀请红包，或者是任务红包）
	 * 
	 * @author work
	 * @createTime 2016年11月11日 下午10:10:30
	 * @param timePoint时间点=今天的红包
	 * @param recordStatus记录状态=正常
	 * @param status红包状态=审核通过
	 * @param redpackType红类型（邀请红包，或者是任务红包）
	 * @return
	 */
	List<RedPack> findByTimePointAndRecordStatusAndStatusAndRedpackTypeOrderByCreatedDateAsc(Date timePoint,RedpackRecordStatus recordStatus,RedpackStatus status,RedpackType redpackType);

	/**
	 * 查询最早一条某种状态的订单，例如最早一条没有支付的订单（例如现在时间是11点9点钟和10点钟都有一条"待付款"订单则查询出9点的记录）
	 * 
	 * @author SangLy
	 * @createTime 2016年11月18日 上午10:18:58
	 * @param redpackStatus
	 * @return
	 */
	@QueryExtend
	@Query(nativeQuery = true,value="select * from( select rr.*, rownum as rn from( select re.* from rp_t_redpack re join rp_t_order t_order on re.id = t_order.relate_primary and re.status = :redpackStatus and re.record_status = :recordStatus order by re.created_date asc) rr) where rn = 1")
	RedPack findCloselyHaveNotPayOfCustomerOrder(@Param("redpackStatus")String redpackStatus,@Param("recordStatus")String recordStatus);
	
	/**
	 * 查询最近一条没有发送支付提醒的订单
	 * @author SangLy
	 * @createTime 2016年11月18日 下午2:40:43
	 * @param redpackStatus
	 * @return
	 */
	@QueryExtend
	@Query(nativeQuery = true,value="select * from( select rr.*, rownum as rn from( select re.* from rp_t_redpack re join rp_t_order t_order on re. id = t_order.relate_primary join rp_t_message me on me.relate_primary = re.id join rp_t_message_template te on te.id = me.message_template_id and re.status = :redpackStatus and re.record_status = :recordStatus order by re.created_date asc) rr) where rn = 1")
	RedPack findCloselyHaveNotRemindMessageOfCustomerOrderOutOfTime(@Param("redpackStatus")String redpackStatus,@Param("recordStatus")String recordStatus);
	
	/**
	 * 根据红包时点和状态集合查询红包信息
	 * 
	 * @author samson
	 * @createTime 2016年11月21日 下午2:19:41
	 * @param startDate
	 * @param endDate
	 * @param status
	 * @return
	 */
	public List<RedPack> findAllByTimePointBetweenAndStatusIn(Date startDate, Date endDate, List<RedpackStatus> status);
	
	/**
	 * 找出最近一条，客户没有抢完的红包，结构为，红包id，需要返的钱是多少
	 * 
	 * @author SangLy
	 * @createTime 2016年11月21日 上午10:35:00
	 * @return
	 */
	@QueryExtend
	@Query(nativeQuery = true,value="select di.redpack_id,sum (di.amount) amount from rp_t_distribute di where di.assignee is null and di.created_date < :today group by di.redpack_id order by di.redpack_id asc")
	public List<Map<String, Object>> findCloselyCustomerNotRobRedpackMoney(@Param("today")Date today);
}
