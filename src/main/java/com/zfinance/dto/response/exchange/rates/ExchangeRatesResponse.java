package com.zfinance.dto.response.exchange.rates;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeRatesResponse {

	private List<ExchangeRateRecord> records;
}
