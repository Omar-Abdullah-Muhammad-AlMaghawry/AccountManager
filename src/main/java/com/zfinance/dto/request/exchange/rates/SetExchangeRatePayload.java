package com.zfinance.dto.request.exchange.rates;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class SetExchangeRatePayload {
	private String id;
	private Double rate;
	private String inIssuerId;
	private String outIssuerId;
	private String direction;
	private Boolean active;
}
