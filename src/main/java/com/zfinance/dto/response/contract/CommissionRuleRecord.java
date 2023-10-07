package com.zfinance.dto.response.contract;

import java.util.List;

import com.zfinance.orm.userdefinedtypes.contract.CommissionRuleCondition;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CommissionRuleRecord {

	private String id;
	private String beginDate;
	private String endDate;
	private String feeDirection;
	private Boolean active;
	private List<CommissionRuleCondition> conditions;
}
