package com.zfinance.dto.request.contract.currrency.exchange;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ContractCurrencyExchangeFilter {

	private String type;
	private String startDate;
	private String endDate;
	private String status;

}
