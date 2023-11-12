package com.zfinance.dto.response.exchange;

import com.zfinance.orm.userdefinedtypes.exchanges.ExchangeRateUdt;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ExchangeCalculateResponse {

	private Double topUpAmount;
	private Double withdrawAmount;
	private ExchangeRateUdt exchangeRate;
}
