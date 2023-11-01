package com.zfinance.dto.response.contract.currency.exchange;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ContractCurrencyExchangeRecord {

	private String id;
	private String type;
	private Double percent;
	private Double earned;
	private Double medianEarning;
	private Double medianTransactions;
	private String startDate;
	private String endDate;
	private String status;
}
