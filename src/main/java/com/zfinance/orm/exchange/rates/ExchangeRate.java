package com.zfinance.orm.exchange.rates;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.zfinance.orm.userdefinedtypes.exchange.rates.ExchangeRatesExchanger;
import com.zfinance.orm.userdefinedtypes.exchange.rates.Issuer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Document("zfin_exchange_rate")
public class ExchangeRate {

	@Id
	@Field("id")
	private String id;

	@Field("in_issuer")
	private Issuer inIssuer;

	@Field("out_issuer")
	private Issuer outIssuer;

	@Field("rate")
	private Double rate;

	@Field("direction")
	private String direction;

	@Field("exchanger")
	private ExchangeRatesExchanger exchanger;

	@Field("reserve")
	private Double reserve;

	@Field("active")
	private Boolean active;

	@Transient
	public static final String SEQUENCE_NAME = "exchange_rate_sequence";

}
