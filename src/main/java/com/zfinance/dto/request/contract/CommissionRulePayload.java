package com.zfinance.dto.request.contract;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CommissionRulePayload {

	private String beginDate;
	private String endDate;
	private String direction;
	private Boolean active;

}
