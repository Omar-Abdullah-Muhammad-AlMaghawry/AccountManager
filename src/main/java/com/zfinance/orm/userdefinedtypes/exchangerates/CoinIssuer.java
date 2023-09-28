package com.zfinance.orm.userdefinedtypes.exchangerates;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//TODO: maybe table also

@Setter
@Getter
@NoArgsConstructor
public class CoinIssuer {

	@Field("id")
	private String id;

	@Field("sn")
	private String sn;

	@Field("symbol")
	private String symbol;

	@Field("currency")
	private String currency;

}
