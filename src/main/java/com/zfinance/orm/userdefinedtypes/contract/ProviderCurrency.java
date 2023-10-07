package com.zfinance.orm.userdefinedtypes.contract;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ProviderCurrency {

	@Field("code")
	private String code;

	@Field("digital_code")
	private String digitalCode;

	@Field("symbol")
	private String symbol;

	@Field("name")
	private String name;
}
