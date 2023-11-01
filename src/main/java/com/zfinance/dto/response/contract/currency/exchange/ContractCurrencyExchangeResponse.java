package com.zfinance.dto.response.contract.currency.exchange;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ContractCurrencyExchangeResponse {
	private List<ContractCurrencyExchangeRecord> currencyExchanges;
}
