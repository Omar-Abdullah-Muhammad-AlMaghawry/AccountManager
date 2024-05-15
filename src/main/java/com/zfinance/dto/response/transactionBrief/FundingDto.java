package com.zfinance.dto.response.transactionBrief;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FundingDto {
	
	private String to;
	private Double amount;

}
