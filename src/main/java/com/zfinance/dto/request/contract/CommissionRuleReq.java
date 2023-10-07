package com.zfinance.dto.request.contract;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CommissionRuleReq {

	private String date;
	private String filterCriteria; // from date
}
