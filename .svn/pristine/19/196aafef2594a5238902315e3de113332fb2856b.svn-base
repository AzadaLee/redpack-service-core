package com.slfinance.redpack.core.controller.crm;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.slfinance.redpack.core.constants.enums.UserType;
import com.slfinance.redpack.core.controller.base.BaseController;
import com.slfinance.redpack.core.entities.File;
import com.slfinance.redpack.core.response.ResponseVo;
import com.slfinance.redpack.core.services.FileService;
import com.slfinance.redpack.core.vo.FileUploadVo;

@RestController("crmUploadController")
@RequestMapping(method=RequestMethod.POST,value="/crm/upload")
public class UploadController extends BaseController{
	
	@Autowired
	private FileService fileService;
	
	@RequestMapping("/save")
	public ResponseVo save(@RequestBody @Validated FileUploadVo fileUploadVo){
		Map<String, Object> result = new HashMap<String, Object>();
		File f = fileService.save(fileUploadVo,UserType.员工);
		result.put("path", f.getPath());
		File file = (File) fileService.doPathAndLogo(staticResourceProxyURI, null, f);
		result.put("id", file.getId());
		result.put("pathURL", file.getPath());
		return ResponseVo.success(result);
	}
}
