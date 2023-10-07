package com.zfinance.orm.userdefinedtypes.contract;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CommissionIssuer {

	@Field("id")
	private String id;

	@Field("sn")
	private String sn;

	@Field("currency")
	private String currency;
}
