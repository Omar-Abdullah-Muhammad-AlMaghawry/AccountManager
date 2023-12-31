package com.zfinance.dto.response.report;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReportsCoinDetails {

	private Double amount;
	private Double percentFromTotal;
	private Double roundedAmount;
	private String currency;
	private String symbol;
	private ReportsCoinDetailsCoinEntry reportsCoinDetailsCoinEntry;

}
