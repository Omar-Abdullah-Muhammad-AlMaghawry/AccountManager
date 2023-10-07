package com.zfinance.dto.request.contract;

import com.zfinance.orm.userdefinedtypes.contract.CommissionRuleType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CommissionRuleConditionPayload {

	private Double beginAmount;
	private Double endAmount;
	private CommissionRuleType commission;
}
