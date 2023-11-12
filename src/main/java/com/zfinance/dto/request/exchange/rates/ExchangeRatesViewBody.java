package com.zfinance.dto.request.exchange.rates;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeRatesViewBody {
	private String inIssuerId;
	private String outIssuerId;
}
