package com.zfinance.orm.userdefinedtypes.exchanges;

import org.springframework.data.mongodb.core.mapping.Field;

import com.zfinance.orm.userdefinedtypes.exchange.rates.Issuer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ExchangeRateUdt {

	@Field("id")
	private String id;

	@Field("in_issuer")
	private Issuer inIssuer;

	@Field("out_issuer")
	private Issuer outIssuer;

	@Field("rate")
	private Double rate;

	@Field("organisation_id")
	private String organisationId;

}
