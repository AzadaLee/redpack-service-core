package com.slfinance.redpack.core.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.validator.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileUploadVo {

	@NotBlank(message="900003")
	private String fileType;
	
	@NotBlank(message="900004")
	private String fileName;
	
	@NotBlank(message="900005")
	private String file;
	
	@NotBlank(message="900006")
	private String extensionName;
}
