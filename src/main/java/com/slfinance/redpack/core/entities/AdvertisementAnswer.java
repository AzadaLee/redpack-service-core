package com.slfinance.redpack.core.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "RP_T_ADVERTISEMENT_ANSWER")
@Data
@NoArgsConstructor
public class AdvertisementAnswer extends com.slfinance.redpack.core.entities.base.Entity{
	private static final long serialVersionUID = -1493879797420992912L;
	
	private String advertisementId;
	private String answerContent;
}
