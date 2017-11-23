package com.slfinance.redpack.core.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import com.slfinance.redpack.core.constants.enums.FileType;
import com.slfinance.redpack.core.constants.enums.UserType;
import com.slfinance.redpack.core.entities.base.BaseEntity;

@Entity
@Table(name = "RP_T_FILE")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class File extends BaseEntity{

	private static final long serialVersionUID = 6905637542774982806L;
	
	@Enumerated(EnumType.STRING)
	private FileType fileType;
	
	private String path;
	
	private String fileName;

	@Enumerated(EnumType.STRING)
	private UserType userType = UserType.客户;
}
