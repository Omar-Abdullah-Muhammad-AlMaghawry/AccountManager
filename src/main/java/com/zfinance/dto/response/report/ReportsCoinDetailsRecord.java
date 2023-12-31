package com.zfinance.dto.response.report;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReportsCoinDetailsRecord {

	private Double totalAmount;
	private Double roundedTotalAmount;
	private String currency;
	private List<ReportsCoinDetails> coinsDetail;
	private String symbol;
}
