package com.zfinance.dto.request.exchange.rates;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ExchangeRatesCreateBody {
	private String inCoin;
	private String outCoin;
	private Double inAmount;

}
