package com.zfinance.dto.response.contract.provider;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ContractProviderRecord {

	private String id;
	private String name;
	private Double percent;
	private Double earned;
	private Double medianEarning;
	private Double medianTransactions;
	private String startDate;
	private String endDate;
	private String status;
}
