package com.slfinance.redpack.core.controller.crm;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.slfinance.redpack.common.utils.DigestUtil;
import com.slfinance.redpack.common.utils.MapUtil;
import com.slfinance.redpack.core.constants.enums.UserStatus;
import com.slfinance.redpack.core.controller.base.BaseController;
import com.slfinance.redpack.core.entities.User;
import com.slfinance.redpack.core.exception.SLException;
import com.slfinance.redpack.core.extend.fastjson.Serialize;
import com.slfinance.redpack.core.extend.fastjson.SerializeRule;
import com.slfinance.redpack.core.extend.jpa.page.PageRequestVo;
import com.slfinance.redpack.core.extend.validate.annotations.Rule;
import com.slfinance.redpack.core.extend.validate.annotations.Rules;
import com.slfinance.redpack.core.response.ResponseVo;
import com.slfinance.redpack.core.services.UserService;
import com.slfinance.redpack.core.vo.UpdatePasswordUserVo;

@RestController("crmUserController")
@RequestMapping(value = "/crm/user", method = RequestMethod.POST)
public class UserController extends BaseController {

	@Autowired
	private UserService userService;

	/**
	 * 后台管理员修改密码
	 * 
	 * @author taoxm
	 * @createTime 2016年8月16日 下午3:20:12
	 * @param updatePasswordUserVo
	 * @return
	 */
	@RequestMapping("/updatePassowrd")
	public ResponseVo updatePassowrd(@RequestBody @Validated UpdatePasswordUserVo updatePasswordUserVo) {
		updatePasswordUserVo.setId(getLoginUserId());
		// updatePasswordUserVo.setId("fa09a910-ed3c-429d-abec-cf0a8709a6cd");//测试用
		userService.updatePassowrd(updatePasswordUserVo);
		return ResponseVo.success();
	}

	/**
	 * 获取当前登录用户信息
	 * 
	 * @author taoxm
	 * @createTime 2016年8月16日 下午3:20:30
	 * @return
	 */
	@Serialize({ @SerializeRule(clazz = User.class, include = { "loginName", "mobile", "repasswordCount", "status", "name" }) })
	@RequestMapping("/getLoginUserInfo")
	public ResponseVo getLoginUserInfo() {
		String id = getLoginUserId();
		return ResponseVo.success(userService.findById(id));
	}

	/**
	 * 根据主键查询客户信息
	 * @author taoxm
	 * @createTime 2016年9月2日 上午11:26:30
	 * @param params
	 * @return
	 */
	@RequestMapping("/findInfoById")
	@Rules({@Rule(name="id",required=true,requiredMessage="120001")})
	@Serialize({@SerializeRule(clazz=User.class,include={"id", "loginName", "mobile", "name", "email" })})
	public ResponseVo findInfoById(@RequestBody Map<String, Object> params){
		String id = MapUtil.getStringTrim(params, "id");
		return ResponseVo.success(userService.findById(id));
	}
	
	/**
	 * 员工列表查询
	 * @author taoxm
	 * @createTime 2016年9月2日 下午5:39:20
	 * @param pageRequest
	 * @return
	 */
	@RequestMapping("/findAllUser")
	public ResponseVo findAllUser(PageRequestVo pageRequest){
		return ResponseVo.success(userService.findAllUser(pageRequest));
	}
	
	/**
	 * 设置客户状态
	 * @author taoxm
	 * @createTime 2016年8月30日 上午10:56:38
	 * @param params
	 * @return
	 */
	@RequestMapping("/updateStatus")
	@Rules({@Rule(name="id",required=true,requiredMessage="120001"),@Rule(name="status",required=true,requiredMessage="120056")})
	public ResponseVo updateStatus(@RequestBody Map<String, Object> params){
		String id = MapUtil.getStringTrim(params, "id");
		UserStatus userStatus = UserStatus.valueOf(MapUtil.getStringTrim(params, "status"));
		userService.updateStatus(id,userStatus);
		return ResponseVo.success();
	}
	
	/**
	 * 设置用户密码
	 * @author taoxm
	 * @createTime 2016年9月5日 上午10:11:32
	 * @param params
	 * @return
	 */
	@Rules({@Rule(name="id",required=true,requiredMessage="120001"),@Rule(name="password",required=true,requiredMessage="400002")})
	@RequestMapping("/resetPassword")
	public ResponseVo resetPassword(@RequestBody Map<String, Object> params){
		String id = MapUtil.getStringTrim(params, "id");
		String password = MapUtil.getStringTrim(params, "password");
		userService.resetPassword(id,password);
		return ResponseVo.success();
	}
	
	/**
	 * 后管用户保存
	 * @author taoxm
	 * @createTime 2016年9月5日 下午2:40:15
	 * @param params
	 * @return
	 */
	@RequestMapping("/save")
	@Rules({@Rule(name="loginName",required=true,requiredMessage="400011"),@Rule(name="email",required=true,requiredMessage="400012"),@Rule(name="name",required=true,requiredMessage="400013")})
	public ResponseVo save(@RequestBody Map<String, Object> params){
		String id = MapUtil.getString(params, "id");
		String loginName = MapUtil.getString(params, "loginName");
		String email = MapUtil.getString(params, "email");
		String mobile = MapUtil.getString(params, "mobile");
		String name = MapUtil.getString(params, "name");
		User user = null;
		boolean isAdd = false;
		if(StringUtils.isBlank(id)){//新增
			String password = MapUtil.getStringTrim(params, "password");
			if(StringUtils.isBlank(password)){
				throw new SLException("400014");
			}
			user = new User();
			isAdd = true;
			user.setPassword(DigestUtil.encryptPassword(password));
		}else{
			user = userService.findOne(id);
		}
		user.setLoginName(loginName);
		user.setEmail(email);
		user.setMobile(mobile);
		user.setName(name);
		user.setRepasswordCount(0);
		userService.saveUser(user,isAdd);
		return ResponseVo.success();
	}
}
