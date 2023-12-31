package com.zfinance.dto.response.report;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReportsCoinDetailsCoinEntry {

	private String currency;
	private Double amount;
	private String issuerId;
	private String symbol;

}
