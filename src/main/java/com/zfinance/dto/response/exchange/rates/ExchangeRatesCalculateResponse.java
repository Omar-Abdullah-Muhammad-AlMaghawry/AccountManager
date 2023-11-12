package com.zfinance.dto.response.exchange.rates;

import com.zfinance.orm.userdefinedtypes.exchange.rates.ExchangeRatesCalculateData;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ExchangeRatesCalculateResponse {

	private String status;
	private String message;
	private ExchangeRatesCalculateData in;
	private ExchangeRatesCalculateData out;

}
