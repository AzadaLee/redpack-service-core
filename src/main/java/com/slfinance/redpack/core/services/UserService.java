/** 
 * @(#)UserService.java 1.0.0 2016年7月26日 上午10:20:13  
 *  
 * Copyright © 2016 善林金融.  All rights reserved.  
 */

package com.slfinance.redpack.core.services;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.slfinance.redpack.common.utils.DigestUtil;
import com.slfinance.redpack.core.constants.enums.UserStatus;
import com.slfinance.redpack.core.entities.User;
import com.slfinance.redpack.core.exception.SLException;
import com.slfinance.redpack.core.extend.jpa.page.PageRequestVo;
import com.slfinance.redpack.core.extend.jpa.page.PageResponse;
import com.slfinance.redpack.core.repositories.UserRepository;
import com.slfinance.redpack.core.services.base.BaseService;
import com.slfinance.redpack.core.vo.UpdatePasswordUserVo;

/**
 * 后台用户
 * 
 * @author SangLy
 * @version $Revision:1.0.0, $Date: 2016年7月26日 上午10:20:13 $
 */
@Service
public class UserService extends BaseService<User, UserRepository> {

	/**
	 * 员工修改密码
	 * 
	 * @author taoxm
	 * @createTime 2016年8月17日 下午1:55:16
	 * @param updatePasswordUserVo
	 */
	@Transactional
	public void updatePassowrd(UpdatePasswordUserVo updatePasswordUserVo) {
		String id = updatePasswordUserVo.getId();
		String oldPassword = DigestUtil.encryptPassword(updatePasswordUserVo.getOldPassword());
		String password = DigestUtil.encryptPassword(updatePasswordUserVo.getPassword());
		User user = findOne(id);
		// 1、用户是否存在
		if (user == null) {
			logger.info("用户修改密码失败，客户id{}，失败原因：用户不存在！", id);
			throw new SLException("400003");
		}
		// 2、用户的状态是否正常
		if (user.getStatus() != UserStatus.正常) {
			logger.info("用户修改密码失败，客户id{}，失败原因：用户为{}状态！", id, user.getStatus());
			throw new SLException("400004");
		}
		// 3、新密码和旧密码是否一样
		if (user.getPassword().equals(password)) {
			throw new SLException("400005");
		}
		// 4、旧密码是正确
		if (!user.getPassword().equals(oldPassword)) {
			logger.info("用户修改密码失败，客户id{}，失败原因：输入密码{}与旧密码{}不一致", id, oldPassword, user.getPassword());
			throw new SLException("400006");
		}
		// 5、TODO 判断新密码是否满足规则
		// 6、修改密码
		user.setPassword(password);
		// 7、设置修改密码次数
		user.setRepasswordCount(user.getRepasswordCount() + 1);
		save(user);
	}

	/**
	 * 查询单个员工信息
	 * 
	 * @author taoxm
	 * @createTime 2016年8月17日 下午1:55:44
	 * @param id
	 * @return
	 */
	public User findById(String id) {
		return findOne(id);
	}

	/**
	 * 员工列表查询
	 * @author taoxm
	 * @createTime 2016年9月2日 下午5:39:20
	 * @param pageRequest
	 * @return
	 */
	public PageResponse<Map<String, Object>> findAllUser(PageRequestVo pageRequest) {
		return repository.findAllUser(pageRequest);
	}

	/**
	 * 修改用户状态
	 * @author taoxm
	 * @createTime 2016年9月5日 上午9:28:47
	 * @param id
	 * @param userStatus
	 * @return
	 */
	public User updateStatus(String id, UserStatus userStatus) {
		User user = findById(id);
		if(user == null){
			throw new SLException("400010");
		}
		user.setStatus(userStatus);
		return save(user);
	}

	/**
	 * 设置用户状态
	 * @author taoxm
	 * @createTime 2016年9月5日 上午10:19:17
	 * @param id
	 * @param password
	 * @return
	 */
	public User resetPassword(String id, String password) {
		User user = findById(id);
		if(user == null){
			throw new SLException("400010");
		}
		password = DigestUtil.encryptPassword(password);
		user.setPassword(password);
		return save(user);
	}

	/**
	 * 后管用户保存
	 * @author taoxm
	 * @createTime 2016年9月5日 下午2:39:40
	 * @param user
	 * @return
	 */
	@Transactional
	public User saveUser(User user,boolean isAdd) {
		if(isAdd){
			List<User> users = findByLoginName(user.getLoginName());
			if(null != users && users.size()>0){
				throw new SLException("400015");
			}
			users = findByEmail(user.getEmail());
			if(null != users && users.size()>0){
				throw new SLException("400016");
			}
			users = findByMobile(user.getMobile());
			if(null != users && users.size()>0){
				throw new SLException("400017");
			}
		}else{
			List<User> users = findByLoginName(user.getLoginName());
			if(null != users && users.size()>0){
				for(User u : users){
					if(!u.getId().equals(user.getId())){
						throw new SLException("400015");
					}
				}
			}
			users = findByEmail(user.getEmail());
			if(null != users && users.size()>0){
				for(User u : users){
					if(!u.getId().equals(user.getId())){
						throw new SLException("400015");
					}
				}
				
			}
			users = findByMobile(user.getMobile());
			if(null != users && users.size()>0){
				for(User u : users){
					if(!u.getId().equals(user.getId())){
						throw new SLException("400017");
					}
				}
			}
		}
		return save(user);
	}
	
	public List<User> findByLoginName(String loginName){
		return repository.findByLoginName(loginName);
	}
	
	public List<User> findByEmail(String email){
		return repository.findByEmail(email);
	}

	public List<User> findByMobile(String mobile){
		return repository.findByMobile(mobile);
	}
}
