package com.zfinance.dto.response.exchange.rates;

import com.zfinance.orm.userdefinedtypes.exchange.rates.ExchangeRatesExchanger;
import com.zfinance.orm.userdefinedtypes.exchange.rates.Issuer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ExchangeRateRecord {
	private String id;
	private Issuer inIssuer;
	private Issuer outIssuer;
	private Double rate;
	private String direction;
	private ExchangeRatesExchanger exchanger;
	private Double reserve;
	private Boolean active;
}
