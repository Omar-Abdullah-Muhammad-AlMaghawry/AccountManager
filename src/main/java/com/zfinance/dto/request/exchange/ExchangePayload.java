package com.zfinance.dto.request.exchange;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ExchangePayload {

	private String inCoinSerial;
	private String outCoinSerial;
	private String exchangeType;
	private Double amount;

}
