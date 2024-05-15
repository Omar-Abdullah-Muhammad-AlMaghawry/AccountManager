package com.zfinance.dto.response.transactionBrief;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RunningBalanceDto {

	private Date date;
	private Double balance;
}
